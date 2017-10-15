/**
 * Created by evami on 15.10.17.
 */
public class Task2_18 {
    public static void main(String[] args) {
        String str;
        int count = 0;
        for (int i = 1; i <= 50000; ++i) {
            str = String.format("%05d", i);
            if (str.indexOf('2') != -1)
                ++count;
            }
        System.out.println("Fall: " + count);
    }
}
