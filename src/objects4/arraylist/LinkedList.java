package objects4.arraylist;

import java.util.Iterator;

/**
 * Created by evami on 23.10.17.
 */
public class LinkedList implements List, Stack, Queue{
    private ListItem head;
    private int size;

    public LinkedList(){
    }

    public LinkedList(Object value){
        this.head = new ListItem(value);
        this.size++;
    }

    public static class ListItem{
        public Object value;
        public ListItem next;

        public ListItem(Object value){
            this.value = value;
        }

        public ListItem(Object value, ListItem item){
            this.value = value;
            this.next = item;
        }
    }

    private class ListIterator implements Iterator{
        private ListItem ptr;

        public ListIterator(){
            this.ptr = LinkedList.this.head;
        }

        @Override
        public boolean hasNext(){
            return (this.ptr != null);
        }

        @Override
        public Object next(){
            Object tmp = this.ptr.value;
            this.ptr = this.ptr.next;
            return tmp;
        }
    }

    @Override
    public Iterator iterator(){
        return new ListIterator();
    }

    @Override
    public void add(Object value, int index){
        if (this.size == 0){
            this.head = new ListItem(value);
            this.size++;
        }
        else
        if ((index < 0) || (index > this.size))
            System.out.println("Index error");
        else {
            if (index == 0) {
                this.head = new ListItem(value, this.head);
                this.size++;
            }
            else {
                ListItem tmp = this.head;
                for (int i = 0; i < index - 1; ++i){
                    tmp = tmp.next;
                }
                tmp.next = new ListItem(value, tmp.next);
                this.size++;
            }
        }
    }

    @Override
    public void add(Object object){
        this.add(object, this.size);
    }

    @Override
    public Object getValue(int index){
        ListItem tmp;
        if ((index >= this.size) || (index < 0)){
            System.out.println("Index error");
            return -1;
        }
        tmp = this.head;
        for (int i = 0; i < index; ++i){
            tmp = tmp.next;
        }
        return tmp.value;
    }

    @Override
    public Object remove(int index){
        ListItem tmp, tmp2;
        if (this.size == 0){
            System.out.println("List is empty");
            return null;
        }
        if ((index >= this.size ) || (index < 0)){
            System.out.println("Index error");
            return null;
        }
        if (index == 0){
            tmp = this.head;
            this.head = this.head.next;
            this.size--;
            return tmp.value;
        }
        if (index == this.size - 1){
            tmp = this.head;
            for (int i = 0; i < this.size -1; ++i){
                tmp = tmp.next;
            }
            tmp2 = tmp.next;
            tmp.next = null;
            this.size--;
            return tmp2.value;
        }
        tmp = this.head;
        for (int i = 0; i < index - 1; ++i){
            tmp = tmp.next;
        }
        tmp2 = tmp.next;
        tmp.next = tmp.next.next;
        this.size--;
        return tmp2.value;
    }

    @Override
    public int sizeOf() {
        return this.size;
    }

    @Override
    public void push(Object object){
        this.add(object, 0);
    }

    @Override
    public Object pop(){
        return remove(0);
    }

    @Override
    public Object peek(){
        return getValue(0);
    }

    @Override
    public Object poll(){
        return remove(0);
    }

}
