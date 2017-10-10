/**
 * Created by evami on 10.10.17.
 */
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        try {
            //консольный ввод данных
            Scanner in = new Scanner(System.in);
            System.out.println("Enter n: ");
            int n = in.nextInt();
            if (n%100 == n){
                int sum = (n / 10) + (n % 10);
                System.out.println("Sum: " + sum);
            }
            else{
                System.out.println(two-digit number);
            }
        }
        catch(Exception e){
            System.out.println("Input error ");
        }
    }
}
