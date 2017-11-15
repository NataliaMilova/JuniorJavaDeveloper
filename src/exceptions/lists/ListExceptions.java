package exceptions.lists;

/**
 * Created by evami on 13.11.17.
 */
public class ListExceptions extends RuntimeException {
    public ListExceptions(String message){
        super(message);
    }

    public ListExceptions(String message, Throwable cause){
        super(message, cause);
    }
}
