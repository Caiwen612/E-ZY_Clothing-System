package utility;

public class Font {

    // Usage = System.out.println( Font.BOLD_RED + "This is the red bold text" +
    // FONT.RESET);
    // Note: Don't forget to use the RESET after printing as the effect will remain
    // if it's not cleared
    // Lazy pack = BOLD_ + UNDERLINE_ + BACKGROUND_ + TEXT_

    // RESET = reset to default text and background
    public static final String RESET = "\u001B[0m";

    // BOLD
    // [1 = BOLD
    public static final String BOLD_BLACK = "\u001B[1;30m";
    public static final String BOLD_RED = "\u001B[1;31m";
    public static final String BOLD_GREEN = "\u001B[1;32m";
    public static final String BOLD_YELLOW = "\u001B[1;33m";
    public static final String BOLD_BLUE = "\u001B[1;34m";
    public static final String BOLD_PURPLE = "\u001B[1;35m";
    public static final String BOLD_CYAN = "\u001B[1;36m";
    public static final String BOLD_WHITE = "\u001B[1;37m";

    // UNDERLINE
    // 4 = UNDERLINE
    public static final String UNDERLINE_BLACK = "\u001B[4;30m";
    public static final String UNDERLINE_RED = "\u001B[4;31m";
    public static final String UNDERLINE_GREEN = "\u001B[4;32m";
    public static final String UNDERLINE_YELLOW = "\u001B[4;33m";
    public static final String UNDERLINE_BLUE = "\u001B[4;34m";
    public static final String UNDERLINE_PURPLE = "\u001B[4;35m";
    public static final String UNDERLINE_CYAN = "\u001B[4;36m";
    public static final String UNDERLINE_WHITE = "\u001B[4;37m";

    // Background color
    public static final String BACKGROUND_BLACK = "\u001B[40m";
    public static final String BACKGROUND_RED = "\u001B[41m";
    public static final String BACKGROUND_GREEN = "\u001B[42m";
    public static final String BACKGROUND_YELLOW = "\u001B[43m";
    public static final String BACKGROUND_BLUE = "\u001B[44m";
    public static final String BACKGROUND_PURPLE = "\u001B[45m";
    public static final String BACKGROUND_CYAN = "\u001B[46m";
    public static final String BACKGROUND_WHITE = "\u001B[47m";

    // HIGH BRIGHT
    public static final String BACKGROUND_BRIGHT_BLACK = "\u001B[100m";
    public static final String BACKGROUND_BRIGHT_RED = "\u001B[101m";
    public static final String BACKGROUND_BRIGHT_GREEN = "\u001B[102m";
    public static final String BACKGROUND_BRIGHT_YELLOW = "\u001B[103m";
    public static final String BACKGROUND_BRIGHT_BLUE = "\u001B[104m";
    public static final String BACKGROUND_BRIGHT_MAGENTA = "\u001B[105m";
    public static final String BACKGROUND_BRIGHT_CYAN = "\u001B[106m";
    public static final String BACKGROUND_BRIGHT_WHITE = "\u001B[107m";

    // Color text(Foreground)
    public static final String TEXT_BLACK = "\u001B[30m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_YELLOW = "\u001B[33m";
    public static final String TEXT_BLUE = "\u001B[34m";
    public static final String TEXT_PURPLE = "\u001B[35m";
    public static final String TEXT_CYAN = "\u001B[36m";
    public static final String TEXT_WHITE = "\u001B[37m";

    // High Bright
    public static final String TEXT_BRIGHT_BLACK = "\u001B[90m";
    public static final String TEXT_BRIGHT_RED = "\u001B[91m";
    public static final String TEXT_BRIGHT_GREEN = "\u001B[92m";
    public static final String TEXT_BRIGHT_YELLOW = "\u001B[93m";
    public static final String TEXT_BRIGHT_BLUE = "\u001B[94m";
    public static final String TEXT_BRIGHT_MAGENTA = "\u001B[95m";
    public static final String TEXT_BRIGHT_CYAN = "\u001B[96m";
    public static final String TEXT_BRIGHT_WHITE = "\u001B[97m";

    // Method to use Font.useFont(Font.BOLD_RED, "Please enter a selection of A to E
    // only!")
    public static String useFont(String fontColor, String message) {
        return fontColor + message + RESET;
    }
}
