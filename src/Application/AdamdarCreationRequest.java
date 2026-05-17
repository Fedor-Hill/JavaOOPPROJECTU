package Application;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

import AcademicThigns.Major;
import Adamdar.Student;
import Enums.GENDER;
import Enums.SCHOOLS;

public class AdamdarCreationRequest extends Request {

    public AdamdarCreationRequest(String adamId, String description) {
        super(adamId, description);

    }

    public Student createStudent(Scanner scanner) {
        // Scanner scanner = new Scanner(System.in);

        try {
            Printer.println("--- Creating a new student ---");

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
                    birthday = LocalDate.parse(scanner.nextLine());
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

            // Printer.printInfo("Major (e.g., SE, IS): ");
            Major major = new Major();

            SCHOOLS school = null;
            while (school == null) {
                try {
                    Printer.printInfo("School (e.g., SITE, BS): ");
                    school = SCHOOLS.valueOf(scanner.nextLine().toUpperCase().trim());
                } catch (IllegalArgumentException e) {
                    Printer.println("Invalid school code. Please try again.");
                }
            }

            Student st =  new Student(f_name, l_name, email, phoneNumber, birthday, gender, password, major, school);
            Printer.printSucces("Student was created !");

            return st;

        } catch (Exception e) {
            Printer.printError("Error while creating student: " + e.getMessage());
            return null;
        }
    }

}