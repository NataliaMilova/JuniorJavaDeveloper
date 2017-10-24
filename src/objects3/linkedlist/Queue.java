package objects3.linkedlist;

/**
 * Created by evami on 23.10.17.
 */
public interface Queue {

    void add(Object object);
    Object poll();
    Object peek();
    int sizeOf();
}
