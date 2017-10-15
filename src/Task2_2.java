/**
 * Created by evami on 13.10.17.
 */
import java.util.Scanner;

public class Task2_2 {
    public static void main(String[] args) {
        try{
            double n;
            double m;
            double a;
            Scanner in = new Scanner(System.in);
            System.out.println("Enter a:");
            a = in.nextDouble();
            System.out.println("Enter n:");
            n = in.nextDouble();
            System.out.println("Enter m:");
            m = in.nextDouble();
            if (Math.abs(n - a) == Math.abs(m - a))
                System.out.println("m is equidistant n");
            else if (Math.abs(n - a) < Math.abs(m - a))
                System.out.println(n + " nearly to 10 then " + m);
            else
                System.out.println(m + " nearly to 10 then " + n);
        }
        catch(Exception e){
            System.err.println(e);
        }
    }
}
