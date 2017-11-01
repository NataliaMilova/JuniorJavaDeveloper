import java.util.Arrays;

/**
 * Created by evami on 29.10.17.
 */
public final class SortsAlgoritms {

    private SortsAlgoritms(){

    }

    // аналогично перемещению игроком карт на руках. Сложность O(n^2)
    public static void insertionSort(Comparable[] arr){
        Comparable tmp;
        int j;
        for (int i = 1; i < arr.length ; ++i){
            tmp = arr[i];
            j = i - 1;
            while (j >= 0 && arr[j].compareTo(tmp) == 1){
                arr[j + 1] = arr[j];
                --j;
            }
            arr[j + 1] = tmp;
        }
    }

    //Сортировка выбором: выбирается каждый раз минимальный элемент из неотсортированных
    //и ставится по порядку с начала. Сложность O(n^2)
    public static void selectionSort(Comparable[] arr){
        Comparable min;
        int i = 0;
        int index;
        while (i != arr.length - 1){
            min = arr[i];
            index = i;
            for (int j = i; j < arr.length; ++j){
                if (arr[j].compareTo(arr[index]) < 0)
                    index = j;
            }
            min = arr[index];
            arr[index] = arr[i];
            arr[i] = min;
            i++;
        }
    }

    public static void main(String[] args) {
        Comparable [] arr1 = {1, -80, 4, 2};
        Comparable [] arr2 = {1, -80, 4, 2};
        insertionSort(arr1);
        System.out.println(Arrays.toString(arr1));
        selectionSort(arr2);
        System.out.println(Arrays.toString(arr2));
    }
}
