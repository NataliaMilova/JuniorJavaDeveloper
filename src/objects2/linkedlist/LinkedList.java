package objects2.linkedlist;
/**
 * Created by evami on 22.10.17.
 */
public class LinkedList {
    private ListItem head;
    public int length;

    public LinkedList(){
    }

    public LinkedList(Object value){
        this.head = new ListItem(value);
        this.length++;
    }

    public void add(Object value, int index){
        if (this.length == 0){
            this.head = new ListItem(value);
            this.length++;
        }
        else
        if ((index < 0) || (index > this.length))
            System.out.println("Index error");
        else {
            if (index == 0) {
                this.head = new ListItem(value, this.head);
                this.length++;
            }
            else {
                ListItem tmp = this.head;
                for (int i = 0; i < index - 1; ++i){
                    tmp = tmp.next;
                }
                tmp.next = new ListItem(value, tmp.next);
                this.length++;
            }
        }
    }

    public void add(Object value){
        this.add(value, this.length);
    }

    public Object get(int index){
        ListItem tmp;
        if ((index >= this.length) || (index < 0)){
            System.out.println("Index error");
            return -1;
        }
        tmp = this.head;
        for (int i = 0; i < index; ++i){
            tmp = tmp.next;
        }
        return tmp.value;
    }

    public Object remove(int index){
        ListItem tmp, tmp2;
        if (this.length == 0){
            System.out.println("List is empty");
            return null;
        }
        if ((index >= this.length ) || (index < 0)){
            System.out.println("Index error");
            return null;
        }
        if (index == 0){
            tmp = this.head;
            this.head = this.head.next;
            this.length--;
            return tmp.value;
        }
        if (index == this.length - 1){
            tmp = this.head;
            for (int i = 0; i < this.length -1; ++i){
                tmp = tmp.next;
            }
            tmp2 = tmp.next;
            tmp.next = null;
            this.length--;
            return tmp2.value;
        }
        tmp = this.head;
        for (int i = 0; i < index - 1; ++i){
            tmp = tmp.next;
        }
        tmp2 = tmp.next;
        tmp.next = tmp.next.next;
        this.length--;
        return tmp2.value;
    }

    public void printList(){
        ListItem tmp;
        if (this.length == 0 )
            System.out.println("List is empty");
        else {
            tmp = this.head;
            for (int i = 0; i < this.length; ++i){
                System.out.println(tmp.value);
                tmp = tmp.next;
            }
        }
        System.out.print("\n");
    }
}
