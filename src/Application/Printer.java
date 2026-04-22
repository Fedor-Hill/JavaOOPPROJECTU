package Application;

public abstract class Printer {

    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";

    public static void printSucces(String msg) {
        System.out.println(GREEN + "-SUCCESS-: " + msg + RESET);
    }

    public static void printError(String msg) {
        System.out.println(RED + "-ERROR-: " + msg + RESET);
    }

    public static void printWarning(String message) {
        System.out.println(YELLOW + "-WARNING-: " + message + RESET);
    }

    public static void printInfo(String message) {
        System.out.println(BLUE + "-INFO-: " + message + RESET);
    }

}
