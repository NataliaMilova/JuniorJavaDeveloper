package objects5.arraylist.arraylist;


import java.util.Iterator;

/**
 * Created by evami on 23.10.17.
 */
public class LinkedList implements List, Stack, Queue, Cloneable {
    private ListItem head;
    private int size;

    private LinkedList(ListItem head, int size){
        this.head = new ListItem(head);
        this.size = size;
    }

    public LinkedList(){
    }

    public LinkedList(Object value){
        this.head = new ListItem(value);
        this.size++;
    }

    public static class ListItem implements Cloneable{
        public final Object value;
        public ListItem next;

        public ListItem(Object value){
            this.value = value;
        }

        public ListItem(Object value, ListItem item){
            this.value = value;
            this.next = item;
        }

        private ListItem(ListItem obj){
            this.value = obj.value;
            if (obj.next != null)
                this.next = new ListItem(obj.next);
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;

            ListItem listItem = (ListItem) object;

            if (value != null ? !value.equals(listItem.value) : listItem.value != null) return false;
            return next != null ? next.equals(listItem.next) : listItem.next == null;
        }

        @Override
        public int hashCode() {
            int result = value != null ? value.hashCode() : 0;
            result = 31 * (19 * result + (next != null ? next.hashCode() : 0));
            return result;
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
    public LinkedList clone(){
        return new LinkedList(this.head, this.size);
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

    @Override
    public boolean equals(Object object) {
        int size = 0;
        boolean success;
        if (this == object)
            return true;
        if (object == null){
            return false;
        }
        if (!object.getClass().equals(this.getClass()))
            return false;

        if (this.size != ((LinkedList)object).size)
            return false;

        Iterator it1 = this.iterator();
        Iterator it2 = ((LinkedList)object).iterator();

        while (it1.hasNext() && it2.hasNext()){
            success = it1.next().equals(it2.hasNext());
            if (!success)
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = head != null ? head.hashCode() : 0;
        result = 31 * result + size;
        return result;
    }
}
