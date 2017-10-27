package objects4.library.library3;

import java.util.Iterator;

/**
 * Created by evami on 26.10.17.
 */
public class Library{
    int capacity;
    int booksNums;
    public Node[] booksInLibrary;

    public Library(int capacity){
        this.capacity = capacity;
        this.booksInLibrary = new Node[capacity];
    }

    public static class Node{
        Book value;
        int quantity;

        public Node(Book value, int quantity){
            this.value = value;
            this.quantity = quantity;
        }
    }

    private Node[] redistribution(){
        int bookHash;
        Node tmp2;
        int i;
        this.booksNums = 0;
        this.capacity *= 2;
        Node[] nodes = new Node[this.capacity];
        for(Object j: this.booksInLibrary){
            tmp2 = (Node)j;
            i = 0;
            while (i != this.capacity && tmp2 != null){
                bookHash = Math.abs(tmp2.value.hashCode() + i * i) % this.capacity;
                if (nodes[bookHash] == null){
                    nodes[bookHash] = tmp2;
                    this.booksNums++;
                    break;
                }
                else{
                    ++i;
                }
            }
        }
        return nodes;
    }

    public void put (Book book, int quantity){
        int i = 0;
        int bookHash;
        boolean success = false;
        /*if (this.booksNums >= 0.75 * this.capacity)
            this.booksInLibrary = this.redistribution();*/
        while (i != this.capacity ){
            bookHash = (Math.abs(book.hashCode()) + i * i) % this.capacity;
            if (this.booksInLibrary[bookHash] == null){
                this.booksInLibrary[bookHash] = new Node(book, quantity);
                booksNums++;
                success = true;
                break;
            }
            else{
                if (this.booksInLibrary[bookHash].value.equals(book)){
                    this.booksInLibrary[bookHash].quantity += quantity;
                    success = true;
                    break;
                }
                else
                    i++;
            }
        }
        if (!success){
            this.booksInLibrary = this.redistribution();
            this.put (book, quantity);
        }
    }

    public int get(Book book, int quantity) {
        int i = 0;
        int bookHash = (Math.abs(book.hashCode())) % this.capacity;
        int tmp = 0;
        boolean success = false;
        while (i != this.capacity || this.booksInLibrary[bookHash] == null){
            if (this.booksInLibrary[bookHash].value.equals(book)){
                if (this.booksInLibrary[bookHash].quantity >= quantity){
                    tmp = quantity;
                    this.booksInLibrary[bookHash].quantity -= quantity;
                }
                else{
                    tmp = this.booksInLibrary[bookHash].quantity;
                    this.booksInLibrary[bookHash].quantity = 0;
                }
                success = true;
                break;
            }
            else
                i++;
            bookHash = (Math.abs(book.hashCode()) + i * i) % this.capacity;
        }
        if (!success){
            System.out.println("Library has not this book");
        }
        return tmp;
    }
}
