package Application;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.UUID;

import AcademicThigns.Major;
import Adamdar.Student;
import Enums.GENDER;
import Enums.REQUEST_STATUS;
import Enums.SCHOOLS;

public class Request implements Serializable {
    private String requestId;
    private String adamId;
    private String description;
    private REQUEST_STATUS status;

    public Request(String adamId, String description) {
        this.requestId = UUID.randomUUID().toString();
        this.adamId = adamId;
        this.description = description;
        this.status = REQUEST_STATUS.PENDING;
    }
}

class AdamdarCreationRequest extends Request {

    public AdamdarCreationRequest(String adamId, String description) {
        super(adamId, description);

    }

    private Student createStudent() {
        Scanner scanner = new Scanner(System.in);
        
        try {
            Printer.printInfo("--- Creating a new student ---");

            Printer.printInfo("First name: ");
            String f_name = scanner.nextLine();

            Printer.printInfo("Last name: ");
            String l_name = scanner.nextLine();

            Printer.printInfo("Email: ");
            String email = scanner.nextLine();

            Printer.printInfo("Phone number: ");
            String phoneNumber = scanner.nextLine();

            Printer.printInfo("Birth date (YYYY-MM-DD): ");
            LocalDate birthday = LocalDate.parse(scanner.nextLine());

            Printer.printInfo("Gender (MALE/FEMALE/CROISSANT): ");
            GENDER gender = GENDER.valueOf(scanner.nextLine().toUpperCase());

            Printer.printInfo("Password: ");
            String password = scanner.nextLine();

            Printer.printInfo("Major (e.g., SE, IS): ");
            Major major = new Major();

            Printer.printInfo("School (e.g., SITE, BS): ");
            SCHOOLS school = SCHOOLS.valueOf(scanner.nextLine().toUpperCase());

            scanner.close();

            return new Student(f_name, l_name, email, phoneNumber, birthday, gender, password, major, school);

        } catch (Exception e) {
            Printer.printError("Error while creating student: " + e.getMessage());
            scanner.close();
            return null;
        }
    }

}