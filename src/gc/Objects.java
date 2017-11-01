package gc;

/**
 * Created by xmitya on 29.10.17.
 */
public class Objects {
    // -Xmx512m -Xms128m -XX:+UseSerialGC
    public static void main(String[] args) throws InterruptedException {
        byte[][] store = new byte[1024][];

        for (int i = 0; ; i++) {
            if (i < 0)
                i = 0;

            byte[] bytes = new byte[1024 * 1024];

            store[i % store.length] = bytes;

            Thread.sleep(10);
        }
    }
}
