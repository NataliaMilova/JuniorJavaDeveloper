/**
 * Created by evami on 13.10.17.
 */
import java.util.Scanner;

public class Task2_1 {
    public static void main(String[] args) {
        try{
            int n;
            Scanner in = new Scanner(System.in);
            System.out.println("Enter n:");
            n = in.nextInt();
            if (n % 2 == 0)
                System.out.println(n + " is even");
            else
                System.out.println(n + " is not even");
        }
        catch (Exception e){
            System.err.println(e);
        }
    }
}
