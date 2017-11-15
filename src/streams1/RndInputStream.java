package streams1;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/**
 * Created by evami on 15.11.17.
 */
public class RndInputStream extends InputStream {

    private Random rnd = new Random(0);

    @Override
    public int read() throws IOException {
        return this.rnd.nextInt(256);
    }

    @Override
    public int read(byte b[], int off, int len) throws IOException {
        for (int i = off; i < b.length; ++i)
            b[i] = (byte)read();
        return b.length;
    }
}
