package basics;

import java.util.Scanner;

/**
 * Created by evami on 21.10.17.
 */
public class Cycles {

    public static void fourDigitSequence(){
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

    public static void unevenSequence(){
        int n = 1;
        for (int i = 0; i < 55 ; ++i){
            System.out.print(n + " ");
            n += 2;
            if ((i + 1) % 11 == 0)
                System.out.print("\n");
        }
    }

    public static void positivSequence() {
        int n = 90;
        while (n >= 0) {
            System.out.print(n + " ");
            n -= 5;
        }
    }

    public static void sequenceOfPowsNumTwo(){
        int n = 2;
        for (int i = 0; i < 20; ++i){
            System.out.print((n << i) + " ");
        }
    }

    public static void factorial(Scanner in){
        try {
            long factorial = 1;
            System.out.println("Enter n [1;20]: ");
            int n = in.nextInt();
            if ((n > 0) && (n < 21)){
                for (int i = 1; i <= n; ++i){
                    factorial *= i;
                }
                System.out.println("Factorial: " + factorial);
            }
            else
                System.out.println("No natural number for factorial");
        }
        catch (Exception e){
            System.err.println("Input error");
        }
    }

    public static void positiveDividersOfNaturalNum(Scanner in){
        try {
            System.out.println("Enter n: ");
            int n = in.nextInt();
            for (int i = 1; i <= n; ++i){
                if (n % i == 0)
                    System.out.print(i + " ");
            }
        }
        catch (Exception e){
            System.err.println("Input error");
        }
    }

    public static void simpleNum(Scanner in){
        try {
            boolean tmp = false;
            System.out.println("Enter n: ");
            int n = in.nextInt();
            for (int i = 2; i <= (int)Math.sqrt(n); ++i){
                if (n % i == 0) {
                    tmp = true;
                    break;
                }
            }
            if (tmp)
                    System.out.println("Составное число");
                else
                    System.out.println("Простое число");
        }
        catch (Exception e){
            System.err.println("Input error");
        }
    }

    public static void fibonachi(){
        int[] fibonachi = new int[11];
        fibonachi[0] = fibonachi[1] = 1;
        for (int i = 2; i < 11; ++i){
            fibonachi[i] = fibonachi[i -1] + fibonachi[i-2];
            System.out.print(fibonachi[i] + " ");
        }
    }

    public static int sumNumericsOfNum(int n){
        int tmp;
        int sum = 0;
        while (n != 0){
            tmp = n % 10;
            sum += tmp;
            n = n / 10;
        }
        return sum;
    }

    public static void luckyTickets(){
        int count = 0;
        for (int i = 1; i <= 999999; ++i) {
            if (sumNumericsOfNum(i / 1000) == sumNumericsOfNum(i % 1000))
                ++count;
        }
        System.out.println("Lucky tickets: " + count);
    }

    public static void fallSelfs(){
        int count = 0;
        int tmp;
        for (int i = 1; i <= 50000; ++i) {
            tmp = i;
            while (tmp != 0){
                if (tmp % 10 == 2){
                    count++;
                    break;
                }
                tmp /= 10;
            }
        }
        System.out.println("Fall: " + count);
    }

    public static void clock(){
        int count = 0;
        for (int i = 0; i <= 23; ++i) {
            for (int j = 0; j <= 59; ++j) {
                if (i / 10 == j % 10 && i % 10 == j / 10)
                    ++count;
            }
        }
        System.out.println(count);
    }

    public static void army(){
        int tmp1;
        int count = 0;
        for (int i = 1; i <= 99999; ++i) {
            tmp1 = i;
            while (tmp1 != 0) {
                if ((tmp1 % 100 == 13) || (tmp1 % 10 == 4)) {
                    ++count;
                    break;
                }
                tmp1 /= 10;
            }
        }
        System.out.println("Fall: " + count);
    }

    public static void main(String[] args) {
        clock();
    }
}
