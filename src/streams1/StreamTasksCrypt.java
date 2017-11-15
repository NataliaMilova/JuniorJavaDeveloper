package streams1;

import com.sun.xml.internal.bind.v2.runtime.output.Encoded;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Created by evami on 15.11.17.
 */
public class StreamTasksCrypt {

    public static void cryptFile(File text, File result, File password) throws IOException{
        byte[] buf = new byte[1024];
        int len;
        try (CryptFileInputStream src = new CryptFileInputStream(text, password);
             OutputStream dest = new FileOutputStream(result)){
            while ((len = src.read(buf)) > 0) {
                dest.write(buf, 0, len);
            }
        }
        System.out.println("success");
    }

    public static void cryptPass(File text, File result, String password ) throws IOException{
        byte[] buf = new byte[1024];
        int[] buf2 = new int[1024];
        byte[] passBytes = password.getBytes("UTF-16");
        int len;
        try (InputStream src = new FileInputStream(text);
            OutputStream dest = new FileOutputStream(result)){
            while ((len = src.read(buf)) > 0) {
                for (int i = 0; i < len; ++i){
                    dest.write(buf[i] ^ passBytes[i % passBytes.length]);
                }
            }
        }
        System.out.println("success");
    }

    public static void main(String[] args) throws IOException {
        File src = new File("/home/evami/crypt/hello.txt");
        File cryptSrc = new File("/home/evami/crypt/cryptSrc.txt");
        File cryptResult = new File("/home/evami/crypt/cryptResult.txt");
        File srcFile = new File("/home/evami/crypt/cryptFile/hello.txt");
        File cryptSrcFile = new File("/home/evami/crypt/cryptFile/cryptSrc.txt");
        File cryptResultFile = new File("/home/evami/crypt/cryptFile/cryptResult.txt");
        File pass = new File("/home/evami/crypt/cryptFile/password.txt");
        String password = "hello";


        cryptPass(src, cryptSrc, password);
        cryptPass(cryptSrcFile, cryptResult, password);


        cryptFile(srcFile, cryptSrcFile, pass);
        cryptFile(cryptSrcFile, cryptResultFile,pass);
    }

}
