package basics;

import java.util.Scanner;

/**
 * Created by evami on 21.10.17.
 */
public class Conditions {

    public static void evenNumber(Scanner in){
        try {
            int n;
            System.out.println("Enter n:");
            n = in.nextInt();
            if (n % 2 == 0)
                System.out.println(n + " is even");
            else
                System.out.println(n + " is not even");
        }
        catch (Exception e){
            System.err.println(e);
        }
    }

    public static void nearlyToTenNumber(Scanner in){
        try{
            double m, n;
            int a = 10;
            System.out.println("Enter n:");
            n = in.nextDouble();
            System.out.println("Enter m:");
            m = in.nextDouble();
            if (Math.abs(n - a) == Math.abs(m - a))
                System.out.println("m is equidistant n");
            else if (Math.abs(n - a) < Math.abs(m - a))
                System.out.println(n + " nearly to 10 then " + m);
            else
                System.out.println(m + " nearly to 10 then " + n);
        }
        catch(Exception e){
            System.err.println(e);
        }
    }

    public static void quadraticEquation(Scanner in){
        try {
            double a, b, c, d, y1, y2;
            System.out.println("Enter a:");
            a = in.nextDouble();
            System.out.println("Enter b:");
            b = in.nextDouble();
            System.out.println("Enter c:");
            c = in.nextDouble();
            if (a == 0)
                System.out.println("Not quadratic equation");
            else {
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
                    System.out.println("Equation has not roots");
            }
        }
        catch(Exception e){
            System.err.println(e);
        }
    }

    public static boolean numberInInterval(){
        double n = 5 + (Math.random() * 151);
        System.out.println("Выбрано число " + n);
        return ((n > 25) && (n < 100));
    }

    public static int maxNumeralOfNumber(){
        int max, a, b, c;
        int n = 100 + (int)(Math.random() * 900);
        System.out.println("Выбрано число " + n);
        a = n % 10;
        b = (n % 100)/10;
        c = n/100;
        max = a;
        if (max < b)
            max = b;
        if (max < c)
            max = c;
        return max;
    }

    public static void ascendingSequence(Scanner in){
        try {
            int a, b, c;
            System.out.println("Enter a:");
            a = in.nextInt();
            System.out.println("Enter b:");
            b = in.nextInt();
            System.out.println("Enter c:");
            c = in.nextInt();
            System.out.printf("%d  %d %d", a, b, c);
            if (a > b){
                a = a ^ b;
                b = b ^ a;
                a = a ^ b;
            }
            if (a > c){
                a = a ^ c;
                c = c ^ a;
                a = a ^ c;
            }
            if (b > c){
                b = b ^ c;
                c = c ^ b;
                b = b ^ c;
            }
            System.out.printf("\n%d  %d %d", a, b, c);
        }
        catch (Exception e){
            System.err.println(e);
        }
    }

    public static void timeForWork(){
        int n = (int)((Math.random( ) * 28801));
        int hours = n / 3600;
        System.out.println("Осталось " + n + " секунд");
        switch (hours) {
            case 0:
                System.out.println("Осталось менее часа");
                break;
            case 1:
                System.out.println("Остался " + hours + " час");
                break;
            case 2:
            case 3:
            case 4:
                System.out.println("Осталось " + hours + " часа");
                break;
            case 5:
            case 6:
            case 7:
            case 8:
                System.out.println("Осталось " + hours + " часов");
                break;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ascendingSequence(in);
    }
}
