package objects2.accumulator;

/**
 * Created by evami on 21.10.17.
 */
public class DoublePlus extends Operation{

    @Override
    public int doOperation(int a, int b){
        return a + 2 * b;
    }
}
