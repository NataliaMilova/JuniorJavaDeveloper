/**
 * Created by evami on 29.10.17.
 */
public final class SortsAlgoritms {

    private SortsAlgoritms(){

    }

    // аналогично перемещению игроком карт на руках
    public static void insertionSort(Comparable[] arr){
        Comparable tmp;
        int j;
        for (int i = 1; i < arr.length; ++i){
            tmp = arr[i];
            j = i - 1;
            while (j >= 0 && arr[j].compareTo(tmp) == 1){
                arr[j + 1] = arr[j];
                --j;
            }
            arr[i + 1] = tmp;
        }
    }

}
