import java.util.Scanner;

/**
 * Created by evami on 15.10.17.
 */
public class Task2_14 {
    public static void main(String[] args) {
        try {
            boolean tmp = false;
            Scanner in = new Scanner(System.in);
            System.out.println("Enter n: ");
            int n = in.nextInt();
            if (n > 0){
                for (int i = 2; i <= (int)Math.sqrt(n); ++i){
                    if (n % i == 0)
                        tmp = true;
                }
                if (tmp)
                    System.out.println("Составное число");
                else
                    System.out.println("Простое число");
            }
            else
                System.out.println("No natural number");
        }
        catch (Exception e){
            System.err.println("Input error");
        }
    }
}
