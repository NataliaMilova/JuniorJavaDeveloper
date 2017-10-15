/**
 * Created by evami on 11.10.17.
 */
import java.util.Scanner;

public class Task1_4 {
    public static void main(String[] args) {
        try {
            int sum;
            //консольный ввод данных
            Scanner in = new Scanner(System.in);
            System.out.println("Enter n: ");
            int n = in.nextInt();
            if ((n > 99) && (n < 1000)){
                sum = (n % 10) + ((n % 100)/10) +(n/100);
                System.out.println("Sum: " + sum);
            }
            else{
                System.out.println("not three-digit number");
            }
        }
        catch(Exception e){
            System.out.println("Input error ");
        }
    }
}
