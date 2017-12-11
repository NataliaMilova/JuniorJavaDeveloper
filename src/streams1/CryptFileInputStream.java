package streams1;

import java.io.*;


/**
 * Created by evami on 15.11.17.
 */
public class CryptFileInputStream extends FilterInputStream{

    private CycleInputStream pass;

    public CryptFileInputStream(InputStream src, File pass) throws FileNotFoundException {
        super(src);
        this.pass = new CycleInputStream(pass);
    }

    @Override
    public int read() throws IOException {
        int tmp = super.read();
        int tmp2 = pass.read();
        if (tmp != -1)
            return tmp ^ tmp2;
        return -1;
    }

    @Override
    public int read(byte b[], int off, int len) throws IOException {
        if ((off | len | (b.length - (len + off)) | (off + len)) < 0)
            throw new IndexOutOfBoundsException();

        int tmp;
        for (int i = off; i < b.length; ++i){
            tmp = read();
            if (tmp == -1)
                return i;
            b[i] = (byte)tmp;
        }
        return b.length;
    }
}
