package objects1;

/**
 * Created by evami on 19.10.17.
 */
public class SortIntList {
        ListItem head;
        int length;

        public SortIntList(){
        }

        public SortIntList(int value){
            this.head = new ListItem(value);
            this.length++;
        }

        public void add(int value){
            if (this.length == 0){
                this.head = new ListItem(value);
                this.length++;
                System.out.printf("Success add %d in index %d\n", value, 0);
            }
            else {
                ListItem tmp = this.head;
                int index = 0;
                for (int i = 0; i < this.length; i++){
                    if (tmp.value <= value)
                        index = i;
                    tmp = tmp.next;
                }
                if (index == 0) {
                    this.head = new ListItem(value, this.head);
                    this.length++;
                    System.out.printf("Success add %d in index %d\n", value, index);
                }
                else {
                    tmp = this.head;
                    for (int i = 0; i < index; ++i){
                        tmp = tmp.next;
                    }
                    tmp.next = new ListItem(value, tmp.next);
                    this.length++;
                    System.out.printf("Success add %d in index %d\n", value, index);
                }
            }
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
            for (int i = 0; i < index -1; ++i){
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
            SortIntList sortIntList = new SortIntList();
            sortIntList.add(5);
            sortIntList.add(4);
            sortIntList.add(100);
            sortIntList.add(62);
            sortIntList.printList();
            sortIntList.remove(0);
            sortIntList.printList();
            System.out.println(sortIntList.get(3));
            sortIntList.remove(5);
            sortIntList.printList();
            sortIntList.remove(1);
            sortIntList.printList();
        }
    }

