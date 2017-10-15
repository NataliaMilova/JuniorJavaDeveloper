import java.util.Arrays;

/**
 * Created by evami on 15.10.17.
 */
public class Task2_15 {
    public static void main(String[] args) {
        int[] fibonachi = new int[11];
        fibonachi[0] = fibonachi[1] = 1;
        for (int i = 2; i < 11; ++i){
            fibonachi[i] = fibonachi[i -1] + fibonachi[i-2];
        }
        System.out.println(Arrays.toString(fibonachi));
    }
}
