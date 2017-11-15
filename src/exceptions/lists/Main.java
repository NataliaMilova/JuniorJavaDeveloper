package exceptions.lists;

/**
 * Created by evami on 02.11.17.
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 1; i < 9; ++i){
            arrayList.add(i);
        }
        System.out.println(arrayList.getValue(55));
        //System.out.println(arrayList.sizeOf());
        /*for (int i: arrayList){
            //arrayList.remove(3);
            System.out.println(i);
        }*/

        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 10; i < 20; ++i){
            linkedList.add(i);
        }
        System.out.println(linkedList.getValue(67));
        //System.out.println(arrayList.sizeOf());
        //for (int i: linkedList){
            //linkedList.add(3);
          //  System.out.println(i);
        //}

    }
}
