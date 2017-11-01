package gc;

import java.lang.ref.SoftReference;

/**
 * Created by xmitya on 29.10.17.
 */
public class References {
    // -Xmx256m -XX:+UseSerialGC
    public static void main(String[] args) throws InterruptedException {
        Object[] objects = new Object[1024 * 1024];

        for (int i = 0; ; i++) {
            if (i < 0)
                i = 0;

            byte[] bytes = new byte[100 * 1024];

            objects[i % objects.length] = new SoftReference<>(bytes);

            Thread.sleep(10);
        }
    }
}
