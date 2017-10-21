package arraysAndStrings;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by evami on 17.10.17.
 */
public class Tasks3 {

    public static void arrayOfEvenNumbers(){
        int[] evenNumbers = new int[10];
        evenNumbers[0] = 2;
        for (int i = 1; i < 10; ++i){
            evenNumbers[i] = evenNumbers[i - 1] + 2;
        }
        System.out.println(Arrays.toString(evenNumbers));
        for (int i: evenNumbers) {
            System.out.println(i);
        }
    }

    public static void arrayOfUnevenNumbers(){
        int[] unevenNumbers = new int[50];
        unevenNumbers[0] = 1;
        for (int i = 1; i < 50; ++i){
            unevenNumbers[i] = unevenNumbers[i-1] + 2;
        }
        System.out.println(Arrays.toString(unevenNumbers));
        for (int i = 49; i >= 0; --i){
            System.out.print(unevenNumbers[i] + " ");
        }
    }

    public static void randomIntegerNumbersWithCountOfEven(){
        int[] rndIntNum = new int[15];
        int count = 0;
        for (int i = 0; i < 15; ++i){
            rndIntNum[i] = (int)(Math.random() * 10);
            if (rndIntNum[i] % 2 == 0)
                ++count;
        }
        System.out.println("\n" + Arrays.toString(rndIntNum));
        System.out.println("even numbers: " + count);
    }

    public static void randomIntegerNumbersWithReplace(){
        int[] rndIntNum = new int[8];
        for (int i = 0; i < 8; ++i){
            rndIntNum[i] = 1 + (int)(Math.random() * 10);
        }
        System.out.println(Arrays.toString(rndIntNum));
        for (int i = 1; i < 8; i += 2){
            rndIntNum[i] = 0;
        }
        System.out.println(Arrays.toString(rndIntNum));
    }

    public static void averageOfTwoArrays(){
        int[] firstArray = new int[5];
        int[] secondArray = new int[5];
        int firstCount = 0;
        int secondCount = 0;
        for (int i = 0; i < firstArray.length; ++i){
            firstArray[i] = (int)(Math.random() * 6);
            firstCount += firstArray[i];
        }
        System.out.println(Arrays.toString(firstArray));
        for (int i = 0; i < secondArray.length; ++i){
            secondArray[i] = (int)(Math.random() * 6);
            secondCount += secondArray[i];
        }
        System.out.println(Arrays.toString(secondArray));
        if (firstCount == secondCount)
            System.out.println("Average is equal");
        else
            if (firstCount > secondCount)
                System.out.println("Average of first array is larger then average of second array");
            else
                System.out.println("Average of second array is larger then average of first array");
    }

    public static void AscArray(){
        int[] rndIntArray = new int[4];
        boolean flag = true;
        for (int i = 0; i < 4; ++i){
            rndIntArray[i] = 10 + (int)(Math.random() * 90);
        }
        System.out.println(Arrays.toString(rndIntArray));
        for (int i = 0; i < 3; ++i){
            if (rndIntArray[i + 1] <= rndIntArray[i]){
                flag = false;
                break;
            }
        }
        if (flag)
            System.out.println("Yes");
        else
            System.out.println("No");
    }

    public static void fibonaci(){
        int[] fibonachiArray = new int[20];
        fibonachiArray[0] = 1;
        fibonachiArray[1] = 1;
        for (int i = 2; i < 20; ++i){
            fibonachiArray[i] = fibonachiArray[i - 1] + fibonachiArray[i - 2];
        }
        System.out.println(Arrays.toString(fibonachiArray));
    }

    public static void searchMaxNumber(){
        int[] rndIntArray = new int[12];
        int index = 0;
        for (int i = 0; i < rndIntArray.length; ++i){
            rndIntArray[i] = -15 + (int)(Math.random() * 31);
            if (rndIntArray[i] >= rndIntArray[index])
                index = i;
        }
        System.out.println(Arrays.toString(rndIntArray));
        System.out.println(index);
    }

    public static void treeArrays(){
        int[] firstArray = new int[10];
        int[] secondArray = new int[10];
        double[] thirdArray = new double[10];
        int count = 0;
        for (int i = 0; i < firstArray.length; ++i){
            firstArray[i] = 1 + (int)(Math.random() * 9);
            secondArray[i] = 1 + (int)(Math.random() * 9);
            thirdArray[i] = (double)firstArray[i] / secondArray[i];
            if (firstArray[i] % secondArray[i] == 0)
                ++count;
        }
        System.out.println(Arrays.toString(firstArray));
        System.out.println(Arrays.toString(secondArray));
        System.out.println(Arrays.toString(thirdArray));
        System.out.println(count);
    }

    public static void countOfNums(){
        int[] rndIntArray = new int[11];
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < rndIntArray.length; ++i) {
            rndIntArray[i] = -1 + (int)(Math.random() * 3);
            switch (rndIntArray[i]){
                case -1:
                    ++count1;
                    break;
                case 1:
                    ++count2;
                    break;
            }
        }
        System.out.println(Arrays.toString(rndIntArray));
        if ((count1 > count2) && (count1 > rndIntArray.length - count1 -count2))
            System.out.println("-1");
        else
            if ((count2 > count1) && (count2 > rndIntArray.length - count1 -count2))
                System.out.println("1");
            else
                if ((rndIntArray.length - count1 -count2 > count1) && (count2 < rndIntArray.length - count1 -count2))
                    System.out.println("0");
    }

    public static void sumAbsOfPathArray(Scanner in){
        int n = -1;
        int count = 0;
        while ((n < 0) || (n % 2 != 0)){
            System.out.println("Enter n(even and positive): ");
            n = in.nextInt();
        }
        int[] rndIntArray = new int[n];
        for (int i = 0; i < rndIntArray.length; ++i) {
            rndIntArray[i] = -5 + (int) (Math.random() * 11);
            if (i < n / 2)
                count += Math.abs(rndIntArray[i]);
            else
                count -= Math.abs(rndIntArray[i]);;
        }
        System.out.println(Arrays.toString(rndIntArray));
        if (count > 0)
            System.out.println("Left");
        else
            if (count < 0)
                System.out.println("Right");
            else
                System.out.println("Left = Right");
    }

    public static void randomArray(){
        int[] rndIntArray = new int[12];
        int countPositive = 6;
        int countNegative = 6;
        int count = 0;
        int tmpNum;
        while (count < rndIntArray.length){
            tmpNum = -10 + (int)(Math.random() * 21);
            if ((tmpNum > 0) && (countPositive != 0)) {
                rndIntArray[count] = tmpNum;
                ++count;
                --countPositive;
            }
            else
                if ((tmpNum < 0) && (countNegative != 0))
                    rndIntArray[count] = tmpNum;
                    ++count;
                    --countNegative;
        }
        System.out.println(Arrays.toString(rndIntArray));
    }

    public static void userArray(Scanner in){
        int n = 0;
        int count = 0;
        while (n < 3){
            System.out.println("Enter n( > 3): ");
            n = in.nextInt();
        }
        int[] rndIntArray = new int[n];
        int[] secondArray = new int[n];
        for (int i = 0; i < rndIntArray.length; ++i){
            rndIntArray[i] = (int) (Math.random() * (n + 1));
            if (rndIntArray[i] % 2 == 0){
                secondArray[count] = rndIntArray[i];
                ++count;
            }
        }
        System.out.println(Arrays.toString(rndIntArray));
        for (int i = 0; i < count; ++i){
            System.out.print(secondArray[i] + " ");
        }
    }

    public static void matrix(){
        int[][] rndIntMatrix = new int[8][5];
        for (int i = 0; i < rndIntMatrix.length; ++i){
            for (int j = 0; j < rndIntMatrix[0].length; ++j){
                rndIntMatrix[i][j] = 10 + (int)(Math.random() * 90);
            }
            System.out.println(Arrays.toString(rndIntMatrix[i]));
        }
    }

    public static void searchMaxNumberInMatrix(){
        int[][] rndIntMatrix = new int [5][8];
        int maxNum = rndIntMatrix[0][0];
        for (int i = 0; i < rndIntMatrix.length; ++i){
            for (int j = 0; j < rndIntMatrix[0].length; ++j){
                rndIntMatrix[i][j] = -99 + (int)(Math.random() * 199);
                if (rndIntMatrix[i][j] > maxNum)
                    maxNum = rndIntMatrix[i][j];
            }
            System.out.println(Arrays.toString(rndIntMatrix[i]));
        }
        System.out.println(maxNum);
    }

    public static void searchMaxRowInMatrix(){
        int[][] rndIntMatrix = new int [7][4];
        int[] sumOfRows = new int[7];
        Arrays.fill(sumOfRows, 1);
        int maxRow = 0;
        for (int i = 0; i < rndIntMatrix.length; ++i){
            for (int j = 0; j < rndIntMatrix[0].length; ++j){
                rndIntMatrix[i][j] = -5 + (int)(Math.random() * 11);
                sumOfRows[i] *= Math.abs(rndIntMatrix[i][j]);
            }
            if  (sumOfRows[i] > sumOfRows[maxRow])
                maxRow = i;
            System.out.println(Arrays.toString(rndIntMatrix[i]));
        }
        System.out.println(maxRow);
    }

    public static void matrixWithReplace(){
        int[][] rndIntMatrix = new int [6][7];
        int maxNum;
        int tmpNum;
        for (int i = 0; i < rndIntMatrix.length; ++i){
            maxNum = 0;
            for (int j = 0; j < rndIntMatrix[0].length; ++j){
                rndIntMatrix[i][j] = (int)(Math.random() * 10);
                if (rndIntMatrix[i][j] > rndIntMatrix[i][maxNum])
                    maxNum = j;
            }
            tmpNum = rndIntMatrix[i][maxNum];
            rndIntMatrix[i][maxNum] = rndIntMatrix[i][0];
            rndIntMatrix[i][0] = tmpNum;
            System.out.println(Arrays.toString(rndIntMatrix[i]));
        }
    }

    public static void multTable(){
        int[][] table = new int[8][8];
        int count = 0;
        int row;
        int col;
        while (count < 15){
            row = (int)(Math.random() * 8);
            col = (int)(Math.random() * 8);
            if (table[row][col] == 0){
                System.out.println((row + 2) + "*" + (col + 2));
                ++count;
                table[row][col] = 1;
                table[col][row] = 1;
            }
        }
    }
}
