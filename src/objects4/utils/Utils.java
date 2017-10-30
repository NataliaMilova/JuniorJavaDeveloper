package objects4.utils;

import objects5.arraylist.arraylist.*;

import java.util.Iterator;

/**
 * Created by evami on 26.10.17.
 */
public final class Utils {

    private Utils(){
    }

    public interface Predicate2{
        boolean rule(Object o1, Object o2);
    }

    public interface Predicate{
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

    public static List toList(Object[] arr){
        List list = new LinkedList();
        if (arr == null)
            return null;
        for (int i = 0; i < arr.length; ++i){
            list.add(arr[i]);
        }
        return list;
    }

    public static List intersect (List list1, List list2, Predicate2 pred){
        List result = new LinkedList();
        Object tmp1, tmp2;
        if (pred == null)
            pred = new Predicate2() {
                @Override
                public boolean rule(Object o1, Object o2) {
                    return o1.equals(o2);
                }
            };
        Iterator it1 = list1.iterator();
        while (it1.hasNext()){
            tmp1 = it1.next();
            Iterator it2 = list2.iterator();
            while (it2.hasNext()){
                tmp2 = it2.next();
                if (pred.rule(tmp1, tmp2)){
                    result.add(tmp1);
                    break;
                }
            }
        }
        return result;
    }

    public static List difference (List list1, List list2, Predicate2 pred){
        List result = new LinkedList();
        Object tmp1;
        int tmp2 = 0;
        if (pred == null)
            pred = new Predicate2() {
                @Override
                public boolean rule(Object o1, Object o2) {
                    return o1.equals(o2);
                }
            };
        Iterator it1 = list1.iterator();
        while (it1.hasNext()){
            tmp1 = it1.next();
            Iterator it2 = list2.iterator();
            while (it2.hasNext()){
                if (!pred.rule(tmp1, it2.next()))
                    tmp2++;
            }
            if (tmp2 == list2.sizeOf())
                result.add(tmp1);
        }
        return result;
    }
}
