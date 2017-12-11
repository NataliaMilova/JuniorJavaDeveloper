package patterns.decorator.test;


import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created by evami on 15.11.17.
 */
public class CryptFileInputStream extends FilterInputStream {

    private byte pass;
    private int ind;

    public CryptFileInputStream(InputStream src, byte pass) throws FileNotFoundException {
        super(src);
        this.pass = pass;
    }

    @Override
    public int read() throws IOException {
        int tmp = (byte)super.read();
        if (tmp == -1)
            return -1;
        return tmp ^ pass;
    }

    @Override
    public int read(byte b[], int off, int len) throws IOException {
        int length = super.read(b, off, len);
        if (length > 0) {
            for (int i = 0; i < length; ++i) {
                b[i] ^= pass;
            }
        }
        return length;
    }
}
