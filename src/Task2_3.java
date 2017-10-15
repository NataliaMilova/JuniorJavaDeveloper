/**
 * Created by evami on 13.10.17.
 */
import java.util.Scanner;

public class Task2_3 {
    public static void main(String[] args) {
        try {
            double a;
            double b;
            double c;
            double d;
            double y1, y2;
            Scanner in = new Scanner(System.in);
            System.out.println("Enter a:");
            a = in.nextDouble();
            System.out.println("Enter b:");
            b = in.nextDouble();
            System.out.println("Enter c:");
            c = in.nextDouble();
            d = b * b + 4 * a * c;
            if (d > 0) {
                y1 = (-b + Math.sqrt(d))/(2 * a);
                y2 = (-b - Math.sqrt(d))/(2 * a);
                System.out.println("y1 = " + y1 +"\n" + "y2 = " + y2);
            }
            else if (d == 0) {
                y1 = -(b / (2 * a));
                System.out.println("y1 = " + y1);
            }
            else
                System.out.println("Корней нет");
        }
        catch(Exception e){
            System.err.println(e);
        }
    }
}
