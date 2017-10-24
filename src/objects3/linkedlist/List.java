package objects3.linkedlist;

/**
 * Created by evami on 23.10.17.
 */
public interface List {

    void add(Object object);
    void add(Object object, int index);
    Object getValue(int index);
    Object remove(int index);
    int sizeOf();
}
