/**
 * Created by evami on 15.10.17.
 */
public class Task2_8 {
    public static void main(String[] args) {
        int n = 1000;
        int tmp = 0;
        while (n % 10000 == n){
            System.out.print(n + " ");
            n += 3;
            ++tmp;
            if (tmp % 24 == 0)
                System.out.print("\n");
        }
    }
}
