/**
 * Created by evami on 13.10.17.
 */
public class Task2_4 {
    public static void main(String[] args) {
        double n = 5 + (Math.random() * 156);
        System.out.println("Выбрано число " + n);
        if ((n > 5) && (n < 100)){
            System.out.println("Число "+ n + " содержится в интервале (25;100)");
        }
        else
            System.out.println("Число "+ n + " не содержится в интервале (25;100)");
    }
}
