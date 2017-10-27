package objects4.library.library2;

import java.util.Iterator;
/**
 * Created by evami on 26.10.17.
 */
public class Library implements Iterable{
    int capacity;
    int booksNums;
    public Node[] booksInLibrary;

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

    public class LibraryIterator implements Iterator {
        private Node ptr;
        private int index;

        public LibraryIterator() {
            this.movePtr();
        }

        @Override
        public boolean hasNext() {
            return (this.ptr != null);
        }

        @Override
        public Object next() {
            Object tmp = this.ptr;
            this.movePtr();
            return tmp;
        }

        private void movePtr() {
           if (this.ptr != null)
               this.ptr = this.ptr.next;
           if (this.ptr == null){
               for (; this.index < Library.this.booksInLibrary.length; this.index++){
                   if (Library.this.booksInLibrary[this.index] != null){
                       this.ptr = Library.this.booksInLibrary[this.index];
                       this.index++;
                       break;
                   }
               }
           }
        }
    }

    @Override
    public Iterator iterator(){
        return new LibraryIterator();
    }

    private Node[] redistribution(){
        int bookHash;
        Node tmp, tmp2;
        this.booksNums = 0;
        this.capacity *= 2;
        Node[] nodes = new Node[this.capacity];
        for(Object i: this){
            tmp2 = (Node)i;
            tmp2.next = null;
            bookHash = Math.abs(tmp2.value.hashCode()) % this.capacity;
            tmp = nodes[bookHash];
            if (tmp == null){
                nodes[bookHash] = tmp2;
                booksNums++;
            }
            else{
                while (tmp != null){
                    tmp = tmp.next;
                }
                tmp = tmp2;
            }
        }
        return nodes;
    }

    public void put (Book book, int quantity){
        Node tmp;
        Boolean success = false;
        if (this.booksNums >= 0.75 * this.capacity)
            this.booksInLibrary = this.redistribution();
        int bookHash = Math.abs(book.hashCode()) % this.booksInLibrary.length;
        tmp = this.booksInLibrary[bookHash];
        if(tmp == null) {
            this.booksInLibrary[bookHash] = new Node(book, quantity);
            this.booksNums++;
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
            this.booksNums++;
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
