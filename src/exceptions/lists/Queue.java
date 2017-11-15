package exceptions.lists;

/**
 * Created by evami on 23.10.17.
 */
public interface Queue<T> {

    void add(T object);
    T poll();
    T peek();
    int sizeOf();
    Queue<T> clone();
}
