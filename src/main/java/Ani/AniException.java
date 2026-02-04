package ani;

/**
 * Exception class for all exceptions to be caught in the tasks.
 */
public class AniException extends RuntimeException {

    /**
     * Takes in the error message to output.
     *
     * @param message Message of error.
     */
    public AniException(String message) {
        super(message);
    }
}
