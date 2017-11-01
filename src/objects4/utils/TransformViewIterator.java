package objects4.utils;

import java.util.Iterator;

/**
 * Created by evami on 31.10.17.
 */
public class TransformViewIterator implements Iterator {
    private Iterator iter;
    private Utils.Transformer transformer;

    public TransformViewIterator(Iterator iter, Utils.Transformer transformer) {
        this.iter = iter;
        this.transformer = transformer;
    }

    @Override
    public boolean hasNext() {
        return this.iter.hasNext();
    }

    @Override
    public Object next() {
        return transformer.trans(this.iter.next());
    }
}
