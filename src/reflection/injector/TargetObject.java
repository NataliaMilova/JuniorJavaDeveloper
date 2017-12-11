package reflection.injector;

import reflection.injector.example.A;
import reflection.injector.example.B;
import reflection.injector.example.TestInterface;

/**
 * Created by evami on 25.11.17.
 */
public class TargetObject {
    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        DIContext ctx = DIContext.instance();

        B b = ctx.getObject("reflection.injector.example.B");

        String randomString = b.getI().getValue();

        TestInterface i = ctx.getObject("reflection.injector.example.ImplementsTestInterface");

        assert i == b.getI();// singleton
        System.out.println(i);
        System.out.println(b.getI());

        A a = ctx.getObject("reflection.injector.example.A");

        System.out.println(a.getI());
        String c = ctx.getObject("java.lang.String");

        System.out.println(c);
    }
}
