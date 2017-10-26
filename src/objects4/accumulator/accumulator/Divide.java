package objects4.accumulator.accumulator;

/**
 * Created by evami on 23.10.17.
 */
public class Divide implements Operation {

    @Override
    public int doOperation(int a, int b){
        if (b == 0){
            System.out.println("Divide on 0");
            return a;
        }
        return a / b;
    }
}