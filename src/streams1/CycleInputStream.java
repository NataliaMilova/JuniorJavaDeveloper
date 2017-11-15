package streams1;

import java.io.*;

/**
 * Created by evami on 15.11.17.
 */
public class CycleInputStream extends InputStream {
    private InputStream in;
    private File password;

    public CycleInputStream(File password) throws FileNotFoundException {
        this.in = new FileInputStream(password);
        this.password = password;
    }

    @Override
    public int read() throws IOException {
        int tmp = in.read();
        if (tmp == -1){
            if (this.in != null)
                this.in.close();
            this.in = new FileInputStream(password);
            tmp = in.read();
        }
        return tmp;
    }
}
