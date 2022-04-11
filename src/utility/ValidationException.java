package utility;

//Author: Team
public class ValidationException extends Exception {
    //Create a custom message
    public ValidationException(String errorMessage) {
        super(errorMessage);
    }
}
