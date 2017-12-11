package reflection.injector.example;

import reflection.injector.Resource;

/**
 * Created by evami on 25.11.17.
 */
public class B {
    private int val;

    @Resource
    private A aVal;

    public ImplementsTestInterface getI() {
        return aVal.getI();
    }

    @Override
    public String toString() {
        return "B{" +
                "val=" + val +
                ", aVal=" + aVal +
                '}';
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public A getaVal() {
        return aVal;
    }

    public void setaVal(A aVal) {
        this.aVal = aVal;
    }
}
