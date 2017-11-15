package mycollections;

import javafx.util.Pair;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by evami on 04.11.17.
 */
public class Words {

    private static Pair<HashMap<String, Integer>, Integer> readWords(Scanner reader) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        int sum = 0;
        String s;
        while (reader.hasNext()){
            s = reader.next().toLowerCase().intern();
            if (hashMap.get(s)!= null)
                hashMap.put(s, hashMap.get(s) + 1);
            else
                hashMap.put(s, 1);
            sum++;
            System.out.println(s);
        }
       return new Pair<>(hashMap, sum);
    }

    public static HashMap<Integer, java.util.ArrayList<String>> readWordsByGroups(Scanner reader){
        HashMap<Integer, java.util.ArrayList<String>> hashMap = new HashMap<>();
        String s;
        while (reader.hasNext()){
            s = reader.next();
            if (hashMap.get(s.length())!= null)
                hashMap.get(s.length()).add(s);
            else{
                hashMap.put(s.length(), new java.util.ArrayList<>());
                hashMap.get(s.length()).add(s);
            }
            System.out.println(s);
        }
        return hashMap;
    }

    //public static

    /*public static void main(String[] args) {
        int sum = 0;
        try {
            File input = new File("/home/evami/helloworld.txt");
            Scanner reader = new Scanner(input).useDelimiter("[^a-z-A-Z]+");
            //HashMap<String, Integer> map = readWords(reader);
            /*Scanner reader = new Scanner(input).useDelimiter("[^a-z-A-Z]*");
            HashMap<String, Integer> map2 = readWords(reader, null);
            System.out.println(map2.size());
            System.out.println(map2.values());
            System.out.println(map2.keySet());
            for (Integer i: map2.values()){
                sum += i;
            }
            System.out.println(sum);
            for (Integer i: map2.values()){
                System.out.println(((float)i) / sum * 100 + " %");
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }*/
}
