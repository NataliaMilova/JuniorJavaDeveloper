package intro;

import java.util.Scanner;

/**
 * Created by evami on 20.10.17.
 */
public class Task3 {
    public static void main(String[] args) {
        try{
            Scanner in = new Scanner(System.in);
            System.out.println("Enter n: ");
            double n = in.nextDouble();
            int result = (int)Math.round(n);
            System.out.println("Result: " + result);
        }
        catch(Exception e){
            System.out.println("Input error ");
        }
    }
}
