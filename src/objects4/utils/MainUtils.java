package objects4.utils;

import objects5.arraylist.arraylist.LinkedList;
import objects5.arraylist.arraylist.List;

import java.util.Iterator;

/**
 * Created by evami on 29.10.17.
 */
public class MainUtils {

    public static void main(String[] args) {
        List list = new LinkedList();
        Object o = Utils.find(new Utils.Predicate(){
            public boolean apply(Object o) {
                return "a".equals(o);
            }
        }, list);

        List result = Utils.filter(new Utils.Predicate(){
            public boolean apply(Object object) {
                return (object.toString().length() == 1);
            }
        }, list);

        List result2 = Utils.transform(new Utils.Transformer(){
            public Object trans(Object object) {
                return object.toString() + object.toString();
            }
        }, list);
    }
}
