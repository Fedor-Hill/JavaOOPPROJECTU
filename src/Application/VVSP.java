package Application;

import java.time.LocalDate;
import java.util.*;
import Adamdar.Student;
import Enums.GENDER;
import Adamdar.Admin;

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
        new Window().run();

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
                    printLogin();
                    break;
                case "67":
                    isRunning = false;
                    System.out.println("Goodbye lol...");
                    break;
                default:
                    Printer.printError("ERROR: wrong input stoopid");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n--- VVSP SYSTEM MENU ---");
        System.out.println("1: LOGIN");
        System.out.println("67: EXIT");
        System.out.print("Choose action: ");
    }

    private void printLogin() {
        boolean isRunning = true;

        List<Admin> admins = DataStorage.loadAdmins();

        if (admins.isEmpty()) {
            LocalDate localDate = LocalDate.parse("1970-01-01");
            Admin firstborn = new Admin("0", "fenya", "fenya@kbtu.kz", localDate, GENDER.CROISSANT, "1234");
            admins.add(firstborn);
            DataStorage.saveAdmins(admins);
        }

        while (isRunning) {
            System.out.println("--- VVSP LOGIN PAGE ---");
            System.out.println("login: ");
            String login = scanner.nextLine();
            System.out.println("password: ");
            String password = scanner.nextLine();

            for (Admin eva : admins) {
                if (eva.getName().equals(login)) {
                    if (eva.loginTo(password)) {
                        isRunning = false;
                        Printer.printSucces("Login succes !");
                        printAdminPanel();
                    }
                }
            }

            Printer.printError("Login or password incorrect");
        }

    }

    private void printAdminPanel() {
        boolean isRunning = true;
        int choice = 0;

        while (isRunning) {
            System.out.println("--- ADMIN PANEL ---");
            System.out.println("1: Create User");
            System.out.println("2: Edit User");
            System.out.println("3: Delete User");
            System.out.println("67: EXIT");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("-> selected: LOGIN");
                    break;
                case 67:
                    isRunning = false;
                    System.out.println("Goodbye lol...");
                    break;
                default:
                    Printer.printError("ERROR: wrong input stoopid");
            }
        }
    }
}       