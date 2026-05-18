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

    private Student student;

    public Student getStudent() {
        return student;
    }

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

            SCHOOLS school = chooseSchool(scanner);
            PROGRAMS program = chooseProgram(scanner);

            MAJOR selectedMajorEnum = chooseMajor(scanner, school, program);

            int yearOfStudy = chooseYearOfStudy(scanner);
            Major major = new Major(selectedMajorEnum, selectedMajorEnum.getTranslatedName(), yearOfStudy);

            Student st = new Student(f_name, l_name, email, phoneNumber, birthday, gender, password, major, school,
                    program);

            Printer.printSucces("Student was created !");
            this.student = st;
            return st;

        } catch (Exception e) {
            Printer.printError("Error while creating student: " + e.getMessage());
            return null;
        }
    }

    private MAJOR chooseMajor(Scanner scanner, SCHOOLS selectedSchool, PROGRAMS selectedProgram) {
        while (true) {
            Printer.println("\n" + LangManager.get("choose_major_title"));

            MAJOR[] allMajors = MAJOR.values();
            List<MAJOR> availableMajors = new ArrayList<>();

            for (MAJOR m : allMajors) {
                if (m.getSchool() == selectedSchool && m.getDegreeLevel() == selectedProgram) {
                    availableMajors.add(m);
                }
            }

            if (availableMajors.isEmpty()) {
                Printer.printWarning(String.format("No majors available for school %s at %s level.",
                        selectedSchool.name(), selectedProgram.name()));
                return null;
            }

            for (int i = 0; i < availableMajors.size(); i++) {
                Printer.println(String.format(" [%d] -> %s", (i + 1), availableMajors.get(i).getTranslatedName()));
            }

            Printer.printAction(LangManager.get("choose_action"), false);
            String input = scanner.nextLine().trim();

            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= availableMajors.size()) {
                    return availableMajors.get(choice - 1); 
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