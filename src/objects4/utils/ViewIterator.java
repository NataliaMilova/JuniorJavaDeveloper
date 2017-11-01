package objects4.utils;

import java.util.Iterator;

/**
 * Created by evami on 30.10.17.
 */
public class ViewIterator implements Iterator {
    private Iterator[] iters;
    private int index;

    public ViewIterator(Iterator[] iters) {
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
    public Object next() {
        return iters[index].next();
    }
}
