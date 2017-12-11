package reflection.injector.example;

/**
 * Created by evami on 25.11.17.
 */
public class ImplementsTestInterface implements TestInterface{
    @Override
    public String getValue() {
        return String.valueOf(Math.random());
    }
}
