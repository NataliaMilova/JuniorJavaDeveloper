package objects4.utils;
import objects4.arraylist.*;
/**
 * Created by evami on 26.10.17.
 */
public class Utils {
    public interface Predicate {
        boolean apply(Object object);
    }

    public interface Transformer {
        Object trans(Object object);
    }

    public static Object find(Predicate pred, List list){
       for(Object i: list){
           if (pred.apply(i))
               return i;
           break;
       }
       return null;
    }

    public static List filter(Predicate pred, List list){
        List tmp = new LinkedList();
        for(Object i: list){
            if (pred.apply(i))
                tmp.add(i);
        }
        return tmp;
    }

    public static List transform(Transformer trans, List list){
        List tmp = new LinkedList();
        for(Object i: list){
            tmp.add(trans.trans(i));
        }
        return tmp;
    }

    public static void main(String[] args) {
        List list = new LinkedList();
        Object o = Utils.find(new Predicate(){
            public boolean apply(Object o) {
                return "a".equals(o);
            }
        }, list);

        List result = Utils.filter(new Predicate(){
            public boolean apply(Object object) {
                return (object.toString().length() == 1);
            }
        }, list);

        List result2 = Utils.transform(new Transformer(){
            public Object trans(Object object) {
                return object.toString() + object.toString();
            }
        }, list);
    }
}
