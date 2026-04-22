package Application;

import java.io.*;
import java.util.*;
import Adamdar.Student;

/**
 * @author Meiramkhan Alinur
 */
public class VVSP {

    /**
     * Default constructor
     */
    public VVSP() {
    }

    /**
     * 
     */
    public List<Student> spisok;

    public void executeVVSP() {
        new Window().run();;
    }

}

class Window {
    private Scanner scanner = new Scanner(System.in);
    private boolean isRunning = true;

    public void run() {
        while (isRunning) {
            printMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("-> selected: LOGIN");
                    break;
                case "67":
                    isRunning = false;
                    System.out.println("Goodbye lol...");
                    break;
                default:
                    System.out.println("ERROR: wrong input stoopid");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n--- VVSP SYSTEM MENU ---");
        System.out.println("1: LOGIN");
        System.out.println("67: EXIT");
        System.out.print("Выберите действие: ");
    }
}