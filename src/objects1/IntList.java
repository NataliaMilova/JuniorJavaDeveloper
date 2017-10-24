package objects1;

/**
 * Created by evami on 18.10.17.
 */
public class IntList {
    ListItem head;
    int length;

    public IntList(){
    }

    public IntList(int value){
        this.head = new ListItem(value);
        this.length++;
    }


    public void add(int value, int index){
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
                    System.out.printf("Success add %d in index %d\n", value, index);
                }
            }
    }

    public void add(int value){
        this.add(value, this.length);
    }

    public int get(int index){
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

    public int remove(int index){
        ListItem tmp, tmp2;
        if (this.length == 0){
            System.out.println("List is empty");
            return -2;
        }
        if ((index >= this.length ) || (index < 0)){
            System.out.println("Index error");
            return -1;
        }
        if (index == 0){
            tmp2 = this.head;
            this.head = this.head.next;
            this.length--;
            System.out.println("Success delete num with index " + index);
            return tmp2.value;
        }
        if (index == this.length - 1){
            tmp = this.head;
            for (int i = 0; i < this.length -1; ++i){
                tmp = tmp.next;
            }
            tmp2 = tmp.next;
            tmp.next = null;
            this.length--;
            System.out.println("Success delete num with index " + index);
            return tmp2.value;
        }
        tmp = this.head;
        for (int i = 0; i < index - 1; ++i){
            tmp = tmp.next;
        }
        tmp2 = tmp.next;
        tmp.next = tmp.next.next;
        this.length--;
        System.out.println("Success delete num with index " + index);
        return tmp2.value;
    }

    public void printList(){
        ListItem tmp;
        if (this.length == 0 )
            System.out.println("List is empty");
        else {
            tmp = this.head;
            for (int i = 0; i < this.length; ++i){
                System.out.print(tmp.value + " ");
                tmp = tmp.next;
            }
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {
        IntList intList = new IntList();
        intList.add(5);
        intList.add(12);
        intList.add(23);
        intList.add(33,intList.length);
        intList.add(6,2);
        intList.printList();
        intList.remove(0);
        intList.printList();
        System.out.println(intList.get(3));
        intList.remove(5);
        intList.printList();
        intList.remove(1);
        intList.printList();
        intList.add(5, 0);
        intList.printList();
    }
}
