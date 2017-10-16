/**
 * Created by evami on 15.10.17.
 */
public class Task2_19 {
    public static void main(String[] args) {
        String str1, str2;
        int count = 0;
        for (int i = 0; i <= 23; ++i) {
            str1 = String.format("%02d", i);
            for (int j = 0; j <= 59; ++j) {
                str2 = String.format("%02d", j);
                if (str1.charAt(0) == str2.charAt(1) && str1.charAt(1) == str2.charAt(0))
                    ++count;
            }
        }
        System.out.println(count);
    }
}

