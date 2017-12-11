package patterns.decorator.test;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by evami on 20.11.17.
 */
public class CryptFileOutputStream extends FilterOutputStream {

    private byte pass;
    private int ind;

    public CryptFileOutputStream(OutputStream out, byte pass) throws IOException {
        super(out);
        this.pass = pass;
    }

    @Override
    public void write(int b) throws IOException {
        super.write(b ^ pass);
    }

    @Override
    public void write(byte b[], int off, int len) throws IOException {
        for (int i = 0; i < len ; i++) {
            super.write(b[i + off] ^ pass);
        }
    }
}
