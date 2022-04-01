package utility;

public class Validation {
    // Exception Handling
    //Check Required for String
    public static void validRequired(String input) throws ValidationException {
        if (input.length() == 0) {
            throw new ValidationException(Font.useFont(Font.BOLD_RED, "Please key in some value"));
        }
    }

    //Check Menu option
   public static void validOption(int option, int lowerLimit, int upperLimit) throws ValidationException {
        int intLength = String.valueOf(option).length();// convert integer to String and get length of the String
        if (intLength == 2) {
            throw new ValidationException(Font.useFont(Font.BOLD_RED, "Please don't key in more than 2 digits."));
        }

        // Check only the integer range be allow.
        if (option < lowerLimit || option > upperLimit) {
            throw new ValidationException(
                    Font.useFont(Font.BOLD_RED, "Please only enter from the range of " + lowerLimit + " to " + upperLimit));
        }
    }

    // Check char
    public static void validCharYN(char yesOrNo) throws ValidationException {
        if (yesOrNo != 'Y' && yesOrNo != 'N') {
            throw new ValidationException(Font.useFont(Font.BOLD_RED, "Please only key in Y or N."));
        }
    }
}
