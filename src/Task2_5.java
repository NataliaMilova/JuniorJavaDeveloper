/**
 * Created by evami on 13.10.17.
 */
public class Task2_5 {
    public static void main(String[] args) {
        int max, a, b, c;
        int n = (int)(99 + Math.random() * 901);
        System.out.println("Выбрано число " + n);
        a = n % 10;
        b = (n % 100)/10;
        c = n/100;
        max = a;
        if (max < b)
            max = b;
        if (max < c)
            max = c;
        System.out.println("Наибольшая цифра = " + max);
    }
}
