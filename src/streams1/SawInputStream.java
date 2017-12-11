package streams1;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by evami on 16.11.17.
 */
public class SawInputStream extends InputStream{

    private int maxValue;
    private int minValue;
    private int value;

    public SawInputStream(int minValue, int maxValue) {
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.value = this.minValue;
    }

    @Override
    public int read() throws IOException {
        if (this.value > this.maxValue)
            this.value = this.minValue;
        if (this.value != this.minValue)
            this.value++;
        return this.value;
    }

    @Override
    public int read(byte b[], int off, int len) throws IOException {
        for (int i = off; i < b.length; ++i)
            b[i] = (byte)read();
        return b.length;
    }
}
