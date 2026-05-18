package Application;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

import AcademicThigns.Major;
import Adamdar.Student;
import Enums.GENDER;
import Enums.MAJOR;
import Enums.PROGRAMS;
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

            MAJOR selectedMajorEnum = chooseMajor(scanner);
            int yearOfStudy = chooseYearOfStudy(scanner);
            Major major = new Major(selectedMajorEnum, selectedMajorEnum.getTranslatedName(), yearOfStudy);

            SCHOOLS school = chooseSchool(scanner);

            PROGRAMS program = chooseProgram(scanner);

            Student st = new Student(f_name, l_name, email, phoneNumber, birthday, gender, password, major, school,
                    program);

            Printer.printSucces("Student was created !");
            return st;

        } catch (Exception e) {
            Printer.printError("Error while creating student: " + e.getMessage());
            return null;
        }
    }

    private MAJOR chooseMajor(Scanner scanner) {
        while (true) {
            Printer.println("\n" + LangManager.get("choose_major_title"));

            MAJOR[] majors = MAJOR.values();
            for (int i = 0; i < majors.length; i++) {
                Printer.println(String.format(" [%d] -> %s", (i + 1), majors[i].getTranslatedName()));
            }

            Printer.printAction(LangManager.get("choose_action"), false);
            String input = scanner.nextLine().trim();

            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= majors.length) {
                    return majors[choice - 1];
                } else {
                    Printer.println(LangManager.get("error_input"));
                }
            } catch (NumberFormatException e) {
                Printer.println(LangManager.get("error_input"));
            }
        }
    }

    private int chooseYearOfStudy(Scanner scanner) {
        while (true) {
            Printer.printAction(LangManager.get("input_year_of_study") + " (1-5): ", false);
            String input = scanner.nextLine().trim();

            try {
                int year = Integer.parseInt(input);
                if (year >= 1 && year <= 5) {
                    return year;
                } else {
                    Printer.println(LangManager.get("error_input"));
                }
            } catch (NumberFormatException e) {
                Printer.println(LangManager.get("error_input"));
            }
        }
    }

    private PROGRAMS chooseProgram(Scanner scanner) {
        while (true) {
            Printer.println("\n" + LangManager.get("choose_program_title"));

            PROGRAMS[] programs = PROGRAMS.values();
            for (int i = 0; i < programs.length; i++) {
                Printer.println(String.format(" [%d] -> %s", (i + 1), programs[i].name()));
            }

            Printer.printAction(LangManager.get("choose_action"), false);
            String input = scanner.nextLine().trim();

            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= programs.length) {
                    return programs[choice - 1];
                } else {
                    Printer.println(LangManager.get("error_input"));
                }
            } catch (NumberFormatException e) {
                Printer.println(LangManager.get("error_input"));
            }
        }
    }

    private SCHOOLS chooseSchool(Scanner scanner) {
        while (true) {
            Printer.println("\n" + LangManager.get("choose_school_title"));

            SCHOOLS[] schools = SCHOOLS.values();
            for (int i = 0; i < schools.length; i++) {
                Printer.println(String.format(" [%d] -> %s", (i + 1), schools[i].name()));
            }

            Printer.printAction(LangManager.get("choose_action"), false);
            String input = scanner.nextLine().trim();

            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= schools.length) {
                    return schools[choice - 1];
                } else {
                    Printer.println(LangManager.get("error_input"));
                }
            } catch (NumberFormatException e) {
                Printer.println(LangManager.get("error_input"));
            }
        }
    }

}