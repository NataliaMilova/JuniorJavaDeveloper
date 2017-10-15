/**
 * Created by evami on 15.10.17.
 */
public class Task2_17 {
    public static void main(String[] args) {
        String str;
        int count = 0;
        int first = 0;
        int second = 0;
        for (int i = 1; i <= 999999; ++i) {
            str = String.format("%06d", i);
            for (int j = 0; j < 6; ++j) {
                if (j < 3)
                    first += Integer.parseInt(String.valueOf(str.charAt(j)));
                else
                    second += Integer.parseInt(String.valueOf(str.charAt(j)));
            }
            if (first == second)
                ++count;
            first = second = 0;
        }
        System.out.println("Lucky tickets: " + count);
    }
}
