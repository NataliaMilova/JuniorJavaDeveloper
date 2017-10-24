package objects3.linkedlist;
import objects2.linkedlist.ListItem;

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
                System.out.printf("Success\n");
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
            System.out.println("Success");
            return tmp;
        }
        if (index == this.size - 1){
            tmp = this.head;
            for (int i = 0; i < this.size -1; ++i){
                tmp = tmp.next;
            }
            tmp2 = tmp.next;
            tmp.next = null;
            this.size--;
            System.out.println("Success delete num with index " + index);
            return tmp2;
        }
        tmp = this.head;
        for (int i = 0; i < index - 1; ++i){
            tmp = tmp.next;
        }
        tmp2 = tmp.next;
        tmp.next = tmp.next.next;
        this.size--;
        System.out.println("Success delete num with index " + index);
        return tmp2;
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
