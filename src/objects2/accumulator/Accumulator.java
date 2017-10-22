package objects2.accumulator;

/**
 * Created by evami on 21.10.17.
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
}
