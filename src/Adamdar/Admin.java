package Adamdar;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

import Application.LangManager;
import Application.Printer;
import Enums.ACCESS_RIGHT;
import Enums.GENDER;
import Enums.MANAGER_TYPE;
import Enums.SCHOOLS;

/**
 * @author Kim Alina
 */
public class Admin extends Eva {

    /**
     * Default constructor
     */
    public Admin(String id, String name, String email, LocalDate birthday, GENDER gender, String password) {
        super(id, name, email, birthday, gender, password);
    }

    /**
     * 
     */
    private List<ACCESS_RIGHT> accesses = List.of(ACCESS_RIGHT.ALL);

    public List<ACCESS_RIGHT> getAccesses() {
        return accesses;
    }

    public Manager createManager(Scanner scanner) {
        try {
            Printer.println("\n--- " + LangManager.get("manager_creation_header") + " ---");

            Printer.printAction("First name: ", false);
            String f_name = scanner.nextLine();

            Printer.printAction("Last name: ", false);
            String l_name = scanner.nextLine();

            Printer.printAction("Email: ", false);
            String email = scanner.nextLine();

            Printer.printAction("Phone number: ", false);
            String phoneNumber = scanner.nextLine();

            LocalDate birthday = null;
            while (birthday == null) {
                try {
                    Printer.printInfo("Birth date (YYYY-MM-DD): ");
                    birthday = LocalDate.parse(scanner.nextLine().trim());
                } catch (DateTimeParseException e) {
                    Printer.println("Invalid date format. Please use YYYY-MM-DD.");
                }
            }

            GENDER gender = null;
            while (gender == null) {
                try {
                    Printer.printInfo("Gender (MALE/FEMALE): ");
                    gender = GENDER.valueOf(scanner.nextLine().toUpperCase().trim());
                } catch (IllegalArgumentException e) {
                    Printer.println("Invalid gender. Please enter MALE or FEMALE.");
                }
            }

            Printer.printInfo("Password: ");
            String password = scanner.nextLine();

            Manager m = new Manager(f_name, l_name, email, phoneNumber, birthday, gender, password,
                    MANAGER_TYPE.OR);
            Printer.printSucces("Manager was created successfully!");
            return m;

        } catch (Exception e) {
            Printer.printError("Error while creating MANAGER: " + e.getMessage());
            return null;
        }
    }

    public Dean createDean(Scanner scanner) {
        try {
            Printer.println("\n--- " + LangManager.get("dean_creation_header") + " ---");

            Printer.printAction("First name: ", false);
            String f_name = scanner.nextLine();

            Printer.printAction("Last name: ", false);
            String l_name = scanner.nextLine();

            Printer.printAction("Email: ", false);
            String email = scanner.nextLine();

            Printer.printAction("Phone number: ", false);
            String phoneNumber = scanner.nextLine();

            LocalDate birthday = null;
            while (birthday == null) {
                try {
                    Printer.printInfo("Birth date (YYYY-MM-DD): ");
                    birthday = LocalDate.parse(scanner.nextLine().trim());
                } catch (DateTimeParseException e) {
                    Printer.println("Invalid date format. Please use YYYY-MM-DD.");
                }
            }

            GENDER gender = null;
            while (gender == null) {
                try {
                    Printer.printInfo("Gender (MALE/FEMALE): ");
                    gender = GENDER.valueOf(scanner.nextLine().toUpperCase().trim());
                } catch (IllegalArgumentException e) {
                    Printer.println("Invalid gender. Please enter MALE or FEMALE.");
                }
            }

            Printer.printInfo("Password: ");
            String password = scanner.nextLine();

            SCHOOLS school = chooseSchool(scanner);

            Dean d = new Dean(f_name, l_name, email, phoneNumber, birthday, gender, password, school);
            Printer.printSucces("Dean was created successfully!");
            return d;

        } catch (Exception e) {
            Printer.printError("Error while creating DEAN: " + e.getMessage());
            return null;
        }
    }

    private SCHOOLS chooseSchool(Scanner scanner) {
        while (true) {
            Printer.println("\n=== SELECT DEAN'S SCHOOL ===");
            SCHOOLS[] schools = SCHOOLS.values();
            for (int i = 0; i < schools.length; i++) {
                Printer.println(String.format(" [%d] -> %s", (i + 1), schools[i].name()));
            }
            Printer.printAction("Choose action: ", false);
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= 1 && choice <= schools.length) {
                    return schools[choice - 1];
                }
                Printer.println("Invalid option. Try again.");
            } catch (NumberFormatException e) {
                Printer.println("Invalid input. Please enter a number.");
            }
        }
    }
}