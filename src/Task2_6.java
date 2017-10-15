/**
 * Created by evami on 13.10.17.
 */
import java.util.Arrays;
import java.util.Scanner;

public class Task2_6 {
    public static void main(String[] args) {
        try{
            int[] startarray = new int[3];
            int[] sortarray = new int[3];
            Scanner in = new Scanner(System.in);
            System.out.println("Enter a:");
            startarray[0] = in.nextInt();
            System.out.println("Enter b:");
            startarray[1] = in.nextInt();
            System.out.println("Enter c:");
            startarray[2] = in.nextInt();
            for (int i = 0; i < 3; ++i){
                sortarray[i] = startarray[i];
            }
            System.out.println("Начальная последовательность" + Arrays.toString(startarray));
            if ((sortarray[0] == sortarray[1]) || (sortarray[0] == sortarray[2]) || (sortarray[2] == sortarray[1]))
                System.out.println("Input error");
            else {
                Arrays.sort(sortarray);
                System.out.println("Возрастающая последовательность" + Arrays.toString(sortarray));
            }
        }
        catch (Exception e){
            System.err.println(e);
        }
    }
}
