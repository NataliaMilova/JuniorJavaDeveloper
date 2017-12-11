package reflection.injector.example;

import reflection.injector.Resource;

/**
 * Created by evami on 25.11.17.
 */
public class A {
    @Resource
    private String str;

    @Resource(ImplementsTestInterface.class)
    private ImplementsTestInterface i;

    @Override
    public String toString() {
        return "A{" +
                "str='" + str + '\'' +
                ", i=" + i +
                '}';
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public ImplementsTestInterface getI() {
        return i;
    }

    public void setI(ImplementsTestInterface i) {
        this.i = i;
    }
}

