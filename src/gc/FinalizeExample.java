package gc;

/**
 * Created by xmitya on 29.10.17.
 */
public class FinalizeExample {
    private Object ref;

    public FinalizeExample(Object ref) {
        this.ref = ref;
    }

    // -Xmx128m -XX:+UseSerialGC
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            new FinalizeExample(new byte[1024]);

            Thread.sleep(1);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("I was collected");

        super.finalize();
    }
}
