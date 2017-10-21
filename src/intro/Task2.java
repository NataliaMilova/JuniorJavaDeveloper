package intro;

import java.util.Scanner;

/**
 * Created by evami on 20.10.17.
 */
public class Task2 {
    public static void main(String[] args) {
        try {
            int sum;
            //консольный ввод данных
            Scanner in = new Scanner(System.in);
            System.out.println("Enter n: ");
            int n = in.nextInt();
            if ((n > 9) && (n % 100 == n)){
                sum = (n / 10) + (n % 10);
                System.out.println("Sum: " + sum);
            }
            else{
                System.out.println("not two-digit number");
            }
        }
        catch(Exception e){
            System.out.println("Input error ");
        }
    }
}
