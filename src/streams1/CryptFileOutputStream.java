package streams1;

import java.io.*;

/**
 * Created by evami on 20.11.17.
 */
public class CryptFileOutputStream extends FilterOutputStream {

    private CycleInputStream pass;

    public CryptFileOutputStream(OutputStream out, File pass) throws FileNotFoundException {
        super(out);
        this.pass = new CycleInputStream(pass);
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b ^ this.pass.read());
    }

    @Override
    public void write(byte b[], int off, int len) throws IOException {
        if ((off | len | (b.length - (len + off)) | (off + len)) < 0)
            throw new IndexOutOfBoundsException();

        for (int i = 0 ; i < len ; i++) {
            write(b[off + i] ^ this.pass.read());
        }
    }

}
