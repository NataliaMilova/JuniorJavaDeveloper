package objects4.accumulator.accumulator;

/**
 * Created by evami on 23.10.17.
 */
public class Power implements Operation {

    @Override
    public int doOperation(int a, int b){
        int result = 1;
        for (int i = 1; i <= b; ++i){
            result *= a;
        }
        return result;
    }
}
