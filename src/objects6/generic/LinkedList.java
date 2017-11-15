package objects6.generic;

import java.util.Iterator;

/**
 * Created by evami on 23.10.17.
 */
public class LinkedList<T> implements List<T>, Stack<T>, Queue<T>{
    private ListItem<T> head;
    private int size;

    private LinkedList(ListItem<T> head, int size){
        this.head = new ListItem<>(head);
        this.size = size;
    }

    public LinkedList(){
    }

    public LinkedList(T value){
        this.head = new ListItem<>(value);
        this.size++;
    }

    public static class ListItem<T> {
        private T value;
        private ListItem<T> next;

        public ListItem(T value){
            this.value = value;
        }

        public ListItem(T value, ListItem<T> item){
            this.value = value;
            this.next = item;
        }

        protected ListItem(ListItem<T> obj){
            this.value = obj.value;
            if (obj.next != null)
                this.next = new ListItem<>(obj.next);
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

    private class ListIterator implements Iterator<T>{
        private ListItem<T> ptr;

        public ListIterator(ListItem<T> head){
            this.ptr = head;
        }

        @Override
        public boolean hasNext(){
            return (this.ptr != null);
        }

        @Override
        public T next(){
            T tmp = this.ptr.value;
            this.ptr = this.ptr.next;
            return tmp;
        }
    }

    @Override
    public LinkedList<T> clone(){
        return new LinkedList<>(this.head, this.size);
    }

    @Override
    public Iterator<T> iterator(){
        return new ListIterator(this.head);
    }

    @Override
    public void add(T object, int index){
        if (this.size == 0){
            this.head = new ListItem<>(object);
            this.size++;
        }
        else
        if ((index < 0) || (index > this.size))
            System.out.println("Index error");
        else {
            if (index == 0) {
                this.head = new ListItem<>(object, this.head);
                this.size++;
            }
            else {
                ListItem tmp = this.head;
                for (int i = 0; i < index - 1; ++i){
                    tmp = tmp.next;
                }
                tmp.next = new ListItem<>(object, tmp.next);
                this.size++;
            }
        }
    }

    @Override
    public void add(T object){
        this.add(object, this.size);
    }

    @Override
    public T getValue(int index){
        ListItem<T> tmp;
        if ((index >= this.size) || (index < 0)){
            System.out.println("Index error");
            return null;
        }
        tmp = this.head;
        for (int i = 0; i < index; ++i){
            tmp = tmp.next;
        }
        return tmp.value;
    }

    @Override
    public T remove(int index){
        ListItem<T> tmp, tmp2;
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
    public void push(T object){
        this.add(object, 0);
    }

    @Override
    public T pop(){
        return remove(0);
    }

    @Override
    public T peek(){
        return getValue(0);
    }

    @Override
    public T poll(){
        return remove(0);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        LinkedList<?> that = (LinkedList<?>) object;

        if (size != that.size) return false;
        return head != null ? head.equals(that.head) : that.head == null;
    }

    @Override
    public int hashCode() {
        int result = head != null ? head.hashCode() : 0;
        result = 31 * result + size;
        return result;
    }

}
