package objects3.arraylist;

/**
 * Created by evami on 24.10.17.
 */
public interface List {
    void add(Object object);
    void add(Object object, int index);
    Object remove(int index);
    Object getValue(int index);
    int sizeOf();
}
