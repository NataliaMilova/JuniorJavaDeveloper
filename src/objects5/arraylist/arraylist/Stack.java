package objects5.arraylist.arraylist;

/**
 * Created by evami on 23.10.17.
 */
public interface Stack {

    void push(Object object);
    Object pop();
    Object peek();
    int sizeOf();
    Stack clone();
}
