package objects6.generic;

/**
 * Created by evami on 24.10.17.
 */
public interface List<T> extends Iterable<T> {
    void add(T object);
    void add(T object, int index);
    T remove(int index);
    T getValue(int index);
    int sizeOf();
    List clone();
}
