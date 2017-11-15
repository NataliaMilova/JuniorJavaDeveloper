package objects6.generic;

/**
 * Created by evami on 02.11.17.
 */
public class Main {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        //list.add("test"); error
        list.add(2);
        for (int i = 0; i < 10; ++i){
            list.add(i);
        }
        Integer element = list.remove(3);
        List<Integer> list2 = list.clone();
        Integer element2 = list2.getValue(3);
        System.out.println(element2);
        System.out.println();
        for(Integer i: list){
            System.out.println(i);
        }

        List<String> strArrayList = new ArrayList<>();
        strArrayList.add("1");
    }
}
