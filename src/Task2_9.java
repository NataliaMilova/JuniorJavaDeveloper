/**
 * Created by evami on 15.10.17.
 */
public class Task2_9 {
    public static void main(String[] args) {
        int n = 1;
        int tmp = 0;
        for (int i = 0; i < 55 ; ++i){
            System.out.print(n + " ");
            n += 2;
            ++tmp;
            if (tmp % 11 == 0)
                System.out.print("\n");
        }
    }
}
