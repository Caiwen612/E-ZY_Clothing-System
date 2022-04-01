package utility;

public class ValidationException extends Exception {
    //Create a custom message
    public ValidationException(String errorMessage) {
        super(errorMessage);
    }
}
