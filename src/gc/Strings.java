package gc;

/**
 * Created by xmitya on 29.10.17.
 */
public class Strings {

    private static final byte[] DATA = "Съешь еще этих мягких франзузских булок да выпей чаю. Съешь еще этих мягких франзузских булок да выпей чаю.Съешь еще этих мягких франзузских булок да выпей чаю.Съешь еще этих мягких франзузских булок да выпей чаю.Съешь еще этих мягких франзузских булок да выпей чаю.Съешь еще этих мягких франзузских булок да выпей чаю.".getBytes();

    // -Xmx512m -XX:+UseSerialGC
    public static void main(String[] args) throws InterruptedException {
        String[] strings = new String[1024 * 1024];

        for (int i = 0; ; i++) {
            if (i < 0)
                i = 0;

            strings[i % strings.length] = new String(DATA).intern();

            if (i % 100 == 0)
                Thread.sleep(1);
        }
    }
}
