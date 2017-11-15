package mycollections;

import java.util.Iterator;

/**
 * Created by evami on 08.11.17.
 */
public class ViewIterator<T> implements Iterator<T> {
    private Iterator<T>[] iters;
    private int index;

    public ViewIterator(Iterator<T>[] iters) {
        this.iters = iters;
    }


    @Override
    public boolean hasNext() {
        if (this.index == this.iters.length)
            return false;
        if (iters[index].hasNext())
            return true;
        this.index++;
        return this.hasNext();
    }

    @Override
    public T next() {
        return iters[index].next();
    }
}

