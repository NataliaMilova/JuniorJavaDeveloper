package streams1;

import java.io.*;

/**
 * Created by evami on 15.11.17.
 */
public class CryptFileInputStream extends InputStream{

    private InputStream src;
    private CycleInputStream pass;

    public CryptFileInputStream(File src, File pass) throws FileNotFoundException {
        this.src = new FileInputStream(src);
        this.pass = new CycleInputStream(pass);
    }

    @Override
    public int read() throws IOException {
        int tmp = src.read();
        int tmp2 = pass.read();
        if (tmp != -1)
            return tmp ^ tmp2;
        return -1;
    }

    @Override
    public int read(byte b[], int off, int len) throws IOException {
        int tmp;
        for (int i = off; i < b.length; ++i){
            tmp = this.read();
            if (tmp == -1)
                return i;
            b[i] = (byte)tmp;
        }
        return b.length;
    }
}
