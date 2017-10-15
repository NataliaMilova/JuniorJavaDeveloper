/**
 * Created by evami on 15.10.17.
 */
import java.util.Scanner;

public class Task2_16 {
    public static void main(String[] args) {
        int n;
        int tmp;
        int sum = 0;
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter n: ");
            n = in.nextInt();
            if (n > 0){
                while (n != 0){
                    tmp = n % 10;
                    sum += tmp;
                    n = n / 10;
                }
                System.out.println(sum);
            }
            else
                System.out.println("No natural number");
        }
        catch(Exception e){
            System.out.println("Input error");
        }
    }

}
