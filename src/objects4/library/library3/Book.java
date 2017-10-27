package objects4.library.library3;

/**
 * Created by evami on 26.10.17.
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Book book = (Book) object;
        if (pagesNum != book.pagesNum) return false;
        if (author != null ? !author.equals(book.author) : book.author != null) return false;
        return name != null ? name.equals(book.name) : book.name == null;
    }

    @Override
    public int hashCode() {
        int result = author != null ? author.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + pagesNum;
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", pagesNum=" + pagesNum +
                '}';
    }
}

