package objects1;

/**
 * Created by evami on 19.10.17.
 */
public class Book {
    String author;
    String name;
    int pagesNum;

    public Book(String author, String name, int pagesNum){
        this.author = author;
        this.name = name;
        this.pagesNum = pagesNum;
    }

    public boolean equals(Book book) {
        return (this.author.equals(book.author)) && (this.name.equals(book.name))
                && (this.pagesNum == book.pagesNum);
    }
}
