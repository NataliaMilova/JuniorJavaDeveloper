package reflection.injector;

/**
 * Created by evami on 25.11.17.
 */
public class DependencyInjectionException extends RuntimeException{
    public DependencyInjectionException(String message){
        super(message);
    }
    public DependencyInjectionException(String message, Throwable cause){
        super(message, cause);
    }
}
