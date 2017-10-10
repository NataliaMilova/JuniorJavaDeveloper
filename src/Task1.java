/**
 * Created by evami on 10.10.17.
 */
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        try{
            //консольный ввод данных
            Scanner in = new Scanner(System.in);
            System.out.println("Enter q: ");
            int q = in.nextInt();
            System.out.println("Enter w: ");
            int w = in.nextInt();
            if ((q > 0) && (w > 0)){
                int div = q / w;
                int mod = q % w;
                System.out.println(div + " and " + mod + " remainder");
            }
            else{
                System.out.println("Not natural number");
            }
        }
        catch(Exception e){
            System.out.println("Input error ");
        }
    }

}
