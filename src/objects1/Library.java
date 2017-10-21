package objects1;

/**
 * Created by evami on 19.10.17.
 */
public class Library {
    int capacity;
    int booksNum;
    int[] quantityOfBooks;
    Book[] booksInLibrary;

    public Library(int capacity){
        this.capacity = capacity;
        this.quantityOfBooks = new int[booksNum];
        this.booksInLibrary = new Book[booksNum];
    }

    public void put (Book book, int quantity){
        boolean success = false;
        if (this.booksNum >= this.capacity - 1)
            System.out.println("objects1.Library is full");
        else {
            for (int i = 0; i < booksNum; ++i){
                if (this.booksInLibrary[i].equals(book)){
                    this.quantityOfBooks[i] += quantity;
                    success = true;
                    break;
                }
            }
            if (!success) {
                this.booksInLibrary[booksNum] = book;
                this.quantityOfBooks[booksNum] = quantity;
                booksNum++;
            }
            System.out.println("Success put " + quantity + " of" + book.name);
        }
    }

    public int get(Book book, int quantity){
        if (this.booksNum != 0){
            for (int i = 0; i < this.booksNum; ++i){
                if (this.booksInLibrary[i].equals(book)){
                    if (quantity > this.quantityOfBooks[i]){
                        System.out.println("Can not get " + quantity + " of" + this.quantityOfBooks[i]);
                        return -3;

                    }
                    else {
                        this.quantityOfBooks[i] -= quantity;
                        System.out.println("Success get " + quantity + " books");
                        return 1;
                    }
                }
            }
        }
        else {
            System.out.println("objects1.Library is empty");
            return -1;
        }
        System.out.println("objects1.Library has no this book");
        return -2;
    }

    public static void main(String[] args) {
        Library library = new Library(56);
        System.out.println(library.quantityOfBooks.length);
    }
}
