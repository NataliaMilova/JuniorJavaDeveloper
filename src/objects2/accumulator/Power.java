package objects2.accumulator;

/**
 * Created by evami on 21.10.17.
 */
public class Power extends Operation{

    @Override
    public int doOperation(int a, int b){
        int result = 1;
        for (int i = 1; i <= b; ++i){
            result *= a;
        }
        return result;
    }
}
