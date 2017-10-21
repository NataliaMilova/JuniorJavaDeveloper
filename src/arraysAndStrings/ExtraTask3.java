package arraysAndStrings;

import java.util.Scanner;

/**
 * Created by evami on 18.10.17.
 */
public class ExtraTask3 {

    public static String crypt(String text, String key){
        byte[] textBytes = text.getBytes();
        byte[] keyBytes = key.getBytes();
        for (int i = 0; i < textBytes.length; ++i){
            textBytes[i] ^= keyBytes[i % keyBytes.length];
        }
        return new String(textBytes);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String text = in.nextLine();
        String key = in.nextLine();
        String cryptText = crypt(text, key);
        String uncryptText = crypt(cryptText, key);
        System.out.println(cryptText);
        System.out.println(uncryptText);
    }
}
