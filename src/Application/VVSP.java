package Application;

import java.time.LocalDate;
import java.util.*;
import Adamdar.*;
import Enums.GENDER;

/**
 * @author Meiramkhan Alinur
 */
public class VVSP {

    public List<Student> spisok;

    public static void executeVVSP() {
        new LoginMenu().show();
    }
}

class LoginMenu {
    private Scanner scanner = new Scanner(System.in);
    private boolean isRunning = true;
    private Admin admin = null;
    // private Adam adam = null;

    public void show() {
        while (isRunning) {
            System.out.println("\n--- VVSP LOGIN MENU ---");
            System.out.println("1: LOGIN");
            System.out.println("67: EXIT");
            System.out.print("Choose action: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    Printer.printInfo("-> selected: LOGIN");
                    printLogin();
                case "67":
                    isRunning = false;
                    Printer.printSucces("Goodbye lol...");
                    break;
                default:
                    Printer.printError("ERROR: wrong input stoopid");
            }
        }
    }

    private void printLogin() {
        boolean isRunning = true;

        //
        // FIND ADMINS
        //

        Map<String, Admin> admins = DataStorage.loadAdmins();
        if (admins.isEmpty()) {
            LocalDate defaultDate = LocalDate.of(1970, 1, 1);
            Admin admin = new Admin("0", "fenya", "fenya@kbtu.kz", defaultDate, GENDER.CROISSANT, "1234");

            admins.put(admin.getName(), admin);
            DataStorage.saveAdmins(admins);
        }

        //
        // FIND USERS (Adamdars)
        //

        Map<String, Adam> adamdar = DataStorage.loadAdamdar();

        while (isRunning) {
            System.out.println("--- VVSP LOGIN PAGE ---");
            System.out.print("login: ");
            String login = scanner.nextLine();
            System.out.print("password: ");
            String password = scanner.nextLine();

            Admin admin = admins.get(login);

            if (admin != null && admin.loginTo(password)) {
                this.admin = admin;
                Printer.printSucces("Login success!");
                printAdminPanel();
            } else {
                Printer.printError("Login or password incorrect");
            }

            Adam adam = adamdar.get(login);

            if (adam != null && adam.loginTo(password)) {
                if (adam instanceof Student) {
                    printStudentPanel((Student) adam);
                } else if (adam instanceof Teacher) {
                    printTeacherPanel((Teacher) adam);
                } else if (adam instanceof Manager) {
                    printManagerPanel((Manager) adam);
                } else if (adam instanceof Dean) {
                    printDeanPanel((Dean) adam);
                }
            }
        }
    }

    private void printAdminPanel() {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("--- ADMIN PANEL: " + this.admin.getName().toUpperCase() + " ---");
            System.out.println("1: Create ADAM");
            System.out.println("2: Edit User");
            System.out.println("3: Delete User");
            System.out.println("67: EXIT");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    Printer.printInfo("Create Dean (1) or Manager (2)");
                    String choid = scanner.nextLine();


                    Printer.printInfo("-> selected: Create User");
                    break;
                case "67":
                    isRunning = false;
                    Printer.printSucces("Goodbye lol...");
                default:
                    Printer.printError("ERROR: wrong input stoopid lol");
            }
        }
    }

    private void printStudentPanel(Student student) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("--- Student PANEL: " + student.getF_name().toUpperCase() + " ---");
            System.out.println("67: EXIT");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "67":
                    isRunning = false;
                    Printer.printSucces("Goodbye lol...");
                default:
                    Printer.printError("ERROR: wrong input stoopid lol");
            }
        }
    }

    private void printTeacherPanel(Teacher teacher) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("--- Student PANEL: " + teacher.getF_name().toUpperCase() + " ---");
            System.out.println("67: EXIT");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "67":
                    isRunning = false;
                    Printer.printSucces("Goodbye lol...");
                default:
                    Printer.printError("ERROR: wrong input stoopid lol");
            }
        }
    }

    private void printManagerPanel(Manager manager) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("--- Student PANEL: " + manager.getF_name().toUpperCase() + " ---");
            System.out.println("1: ");
            System.out.println("67: EXIT");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "67":
                    isRunning = false;
                    Printer.printSucces("Goodbye lol...");
                default:
                    Printer.printError("ERROR: wrong input stoopid lol");
            }
        }
    }

    private void printDeanPanel(Dean dean) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("--- Student PANEL: " + dean.getF_name().toUpperCase() + " ---");
            System.out.println("67: EXIT");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "67":
                    isRunning = false;
                    Printer.printSucces("Goodbye lol...");
                default:
                    Printer.printError("ERROR: wrong input stoopid lol");
            }
        }
    }
}