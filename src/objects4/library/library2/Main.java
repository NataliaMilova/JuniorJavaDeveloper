package objects4.library.library2;

import java.util.Arrays;

/**
 * Created by evami on 26.10.17.
 */
public class Main {
    public static void main(String[] args) {
        Book book1 = new Book("1", "a", 21);
        Book book2 = new Book("1", "a", 21);
        Book book3 = new Book("2", "b", 21);
        Book book4 = new Book("2", "bb", 21);
        Book book5 = new Book("3", "aa", 21);
        Book book6 = new Book("4", "c", 21);
        Book book7 = new Book("5", "d", 21);
        Book book8 = new Book("6", "dd", 21);
        Book book9 = new Book("7", "aaff", 21);
        Book book10 = new Book("8", "c", 21);
        Book book11 = new Book("9", "c", 21);
        Book book12 = new Book("9", "c", 21);
        Library library = new Library(3);
        library.put(book1, 5);
        System.out.println(library.booksInLibrary.length);
        library.put(book1, 5);
        library.put(book2, 6);
        library.put(book3, 7);
        library.put(book4, 6);
        library.put(book5, 3);
        library.put(book6, 6);
        System.out.println(library.booksInLibrary.length);
        library.put(book7, 33);
        System.out.println(library.booksInLibrary.length);
        library.put(book8, 60);
        library.put(book9, 6);
        library.put(book10, 25);
        library.put(book11, 20);
        library.put(book12, 10);
        System.out.println(library.booksInLibrary.length);
        library.put(book12, 5);
        for (Object i: library){
            System.out.println(((Library.Node)i).value);
        }
        System.out.println(library.get(book1, 7));
        System.out.println(library.get(book2, 10));
        System.out.println(library.get(book3, 10));
    }
}
