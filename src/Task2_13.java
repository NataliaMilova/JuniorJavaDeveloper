import java.util.Scanner;

/**
 * Created by evami on 15.10.17.
 */

public class Task2_13 {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter n: ");
            int n = in.nextInt();
            if (n > 0){
                for (int i = 1; i <= n; ++i){
                    if (n % i == 0)
                        System.out.print(i + " ");
                }
            }
            else
                System.out.println("No natural number");
        }
        catch (Exception e){
            System.err.println("Input error");
        }

    }
}
