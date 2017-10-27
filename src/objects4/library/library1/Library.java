package objects4.library.library1;


/**
 * Created by evami on 26.10.17.
 */
public class Library {
    int capacity;
    int booksNum;
    Node[] booksInLibrary;

    public Library(int capacity){
        this.capacity = capacity;
        this.booksInLibrary = new Node[capacity];
    }

    public static class Node{
        Book value;
        Node next;
        int quantity;

        public Node(Book value, int quantity){
            this.value = value;
            this.quantity = quantity;
        }
    }

    public void put (Book book, int quantity){
        Node tmp;
        Boolean success = false;
        int bookHash = Math.abs(book.hashCode()) % this.booksInLibrary.length;
        tmp = this.booksInLibrary[bookHash];
        if(tmp == null) {
            this.booksInLibrary[bookHash] = new Node(book, quantity);
            this.booksNum++;
            return;
        }
        if (tmp.value.equals(book)){
            tmp.quantity += quantity;
            return;
        }
        while (tmp.next != null){
            if (tmp.next.value.equals(book)){
                tmp.next.quantity += quantity;
                success = true;
                break;
            }
            tmp = tmp.next;
        }
        if (!success){
            tmp.next = new Node(book, quantity);
            this.booksNum++;
        }
    }

    public int get(Book book, int quantity){
        Node tmp;
        boolean success = false;
        int factQ = 0;
        int bookHash = Math.abs(book.hashCode()) % this.booksInLibrary.length;
        tmp = this.booksInLibrary[bookHash];
        while (tmp != null){
            if (tmp.value.equals(book)){
                if (tmp.quantity >= quantity){
                    tmp.quantity -= quantity;
                    factQ = quantity;
                }
                else{
                    factQ = tmp.quantity;
                    tmp.quantity = 0;
                }
                success = true;
                break;
            }
            tmp = tmp.next;
        }
        if (!success){
            System.out.println("Library has not got this book");
            return 0;
        }
        return factQ;
    }
}
