package objects4.utils;

import objects5.arraylist.arraylist.LinkedList;
import objects5.arraylist.arraylist.List;

/**
 * Created by evami on 31.10.17.
 */
public class MainViewUtils {
    public static void main(String[] args) {
        List list5 = new LinkedList();
        list5.add(1);
        list5.add(2);
        list5.add(3);
        list5.add(4);
        list5.add(5);
        list5.add(6);
        list5.add(7);
        list5.add(8);
        list5.add(9);

        List list6 = new LinkedList();
        list6.add(10);
        list6.add(20);
        list6.add(30);
        list6.add(40);
        list6.add(50);
        list6.add(60);
        list6.add(70);
        list6.add(80);
        list6.add(90);

        Iterable iterable = Utils.view(Utils.filterView(list5, new Utils.Predicate() {
            @Override
            public boolean apply(Object object) {
                return (Integer)object % 2 == 0;
            }
        }), Utils.transformView(list6, new Utils.Transformer() {
            @Override
            public Object trans(Object object) {
                return (Integer)object / 5;
            }
        }));
        for (Object i: iterable){
            System.out.println(i);
        }
    }
}
