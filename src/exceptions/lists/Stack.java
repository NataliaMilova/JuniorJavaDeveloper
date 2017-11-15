package exceptions.lists;

/**
 * Created by evami on 23.10.17.
 */
public interface Stack<T> {

    void push(T object);
    T pop();
    T peek();
    int sizeOf();
    Stack<T> clone();
}
