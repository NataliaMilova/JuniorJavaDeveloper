package exceptions.angrycalc;

/**
 * Created by evami on 13.11.17.
 */
public class AngryCalcExceptions extends Throwable{
    public AngryCalcExceptions(String message){
        super(message);
    }

    public AngryCalcExceptions(String message, Throwable cause){
        super(message, cause);
    }
}
