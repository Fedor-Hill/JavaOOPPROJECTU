package Application;

/***
 * Help class for beatiful printing 
 * @author Meiramkhan Alinur
 * @version 2.0
 */
public abstract class Printer {
    private static final boolean DEBUG = true; 

    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PINKY = "\u001B[95m";

    public static void debugPrintSuccess(String msg) {
        if (DEBUG) {}
    }

    public static void debugPrintError(String msg) {
        if (DEBUG) {}
    }

    public static void debugPrintWarning(String msg) {
        if (DEBUG) {}
    }

    public static void debugPrintInfo(String msg) {
        if (DEBUG) {}
    }

    public static void printSucces(String msg) {
        System.out.println(GREEN + "-SUCCESS-: " + msg + RESET);
    }

    public static void printError(String msg) {
        System.err.println(RED + msg + RESET);

        // System.out.println(RED + "-ERROR-: " + msg + RESET);
    }

    public static void printWarning(String message) {
        System.out.println(YELLOW + "-WARNING-: " + message + RESET);
    }

    public static void printInfo(String message) {
        System.out.println(BLUE + message + RESET);
    }

    /**
     * 
     * @param message - Your message
     * @param isLn - on new line ?
     */
    public static void printAction(String message, boolean isLn) {
        if (isLn) {
            System.out.println(BLUE + message + RESET);
        } else {
            System.out.print(BLUE + message + RESET);
        }
    }

    public static void println(String msg) {
        System.out.println(PINKY + msg + RESET);
    }

    public static void print(String msg) {
        System.out.print(PINKY + msg + RESET);
    }

}
