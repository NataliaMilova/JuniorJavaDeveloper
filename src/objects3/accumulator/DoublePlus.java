package objects3.accumulator;

/**
 * Created by evami on 23.10.17.
 */
public class DoublePlus implements Operation {

    @Override
    public int doOperation(int a, int b){
        return a + 2 * b;
    }
}

