package objects5.arraylist.arraylist;

/**
 * Created by evami on 24.10.17.
 */
public interface List extends Iterable {
    void add(Object object);
    void add(Object object, int index);
    Object remove(int index);
    Object getValue(int index);
    int sizeOf();
    List clone();
}
