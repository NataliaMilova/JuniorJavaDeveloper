package multithreading.fileserver;

/**
 * Created by evami on 26.11.17.
 */
public class FileServerUnchekedException extends RuntimeException {
    public FileServerUnchekedException(String message){
        super(message);
    }
    public FileServerUnchekedException(String message, Throwable cause){
        super(message, cause);
    }
}
