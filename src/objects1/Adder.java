package objects1;

/**
 * Created by evami on 18.10.17.
 */
public class Adder {

    int state;
    int step;

    public Adder(int step){
        this.step = step;
    }

    public void add(){
        this.state += this.step;
    }

    public int getValue(){
        return this.state;
    }

    public static void main(String[] args) {
        Adder adder = new Adder(10);
        System.out.println("Start value = " + adder.getValue());
        adder.add();
        System.out.println("Changed state = " + adder.getValue());
        adder.add();
        System.out.println("Changed state = " + adder.getValue());
    }
}
