package objects4.accumulator.accumulator;

/**
 * Created by evami on 23.10.17.
 */
public class Accumulator {
    private int value;
    private Operation operation;

    public Accumulator(int value, Operation operation){
        this.value = value;
        this.operation = operation;
    }

    public int accumulate(int a){
        this.value = operation.doOperation(this.value, a);
        return this.value;
    }

    public int getValue(){
        return this.value;
    }

    public void setOperation(Operation operation){
        this.operation = operation;
    }

    public void setValue(int value){
        this.value = value;
    }

    public static void main(String[] args) {
        Operation op = new Operation() {
            @Override
            public int doOperation(int a, int b) {
                return a % b;
            }
        };
        Accumulator acc = new Accumulator(20,op);
        acc.accumulate(3);
        System.out.println(acc.getValue());

        Operation op2 = new Operation() {
            @Override
            public int doOperation(int a, int b) {
                return a << b;
            }
        };
        Accumulator acc2 = new Accumulator(2,op2);
        acc2.accumulate(3);
        System.out.println(acc2.getValue());
    }
}