package objects4.utils;

import java.util.Iterator;

/**
 * Created by evami on 30.10.17.
 */
public class FilterViewIterator implements Iterator {
    private Iterator iter;
    private Utils.Predicate pred;
    private Object ptr;

    public FilterViewIterator(Iterator iter, Utils.Predicate pred) {
        this.iter = iter;
        this.pred = pred;
        this.movePtr();
    }

    private void movePtr(){
        this.ptr = null;
        while (this.iter.hasNext()){
            Object tmp = this.iter.next();
            if (this.pred.apply(tmp)){
                this.ptr = tmp;
                break;
            }
        }
    }

    @Override
    public boolean hasNext() {
        return this.ptr != null;
    }

    @Override
    public Object next() {
        Object tmp = this.ptr;
        this.movePtr();
        return tmp;
    }
}
