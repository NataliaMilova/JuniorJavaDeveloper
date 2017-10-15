import java.util.Scanner;

/**
 * Created by evami on 15.10.17.
 */
public class Task2_12 {
    public static void main(String[] args) {
        try {
            long factorial = 1;
            Scanner in = new Scanner(System.in);
            System.out.println("Enter n [1;20]: ");
            int n = in.nextInt();
            if ((n > 0) && (n < 21)){
                for (int i = 1; i <= n; ++i){
                    factorial *= i;
                }
                System.out.println("Factorial: " + factorial);
            }
            else
                System.out.println("No natural number for factorial");
        }
        catch (Exception e){
            System.err.println("Input error");
        }
    }
}
