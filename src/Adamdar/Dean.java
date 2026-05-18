package Adamdar;

import Enums.*;

import Application.Printer;
import Application.Request;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.Vector;

/**
 * Dean class
 * 
 * @author Kim Alina
 */
public class Dean extends Employee {

    private SCHOOLS school;

    private Vector<Request> signedRequests;

    /**
     * Constructor
     */
    public Dean(String f_name, String l_name, String email, String phoneNumber, LocalDate birthday, GENDER gender,
            String password, SCHOOLS school) {
        super(f_name, l_name, email, phoneNumber, birthday, gender, password, "Dean");

        this.school = school;

        this.signedRequests = new Vector<>();
    }

    // ===== REQUESTS =====

    /**
     * Sign request
     */
    public void signRequest(Request request) {

        if (request == null) {

            Printer.printError(
                    "Request is null!");

            return;
        }

        request.setStatus(
                REQUEST_STATUS.APPROVED);

        signedRequests.add(request);

        Printer.printSucces(
                "Request signed by dean!");
    }

    /**
     * Reject request
     */
    public void rejectRequest(Request request) {

        if (request == null) {

            Printer.printError(
                    "Request is null!");

            return;
        }

        request.setStatus(
                REQUEST_STATUS.REJECTED);

        Printer.printWarning(
                "Request rejected by dean!");
    }

    /**
     * View signed requests
     */
    public void viewSignedRequests() {

        if (signedRequests.isEmpty()) {

            Printer.printWarning(
                    "No signed requests.");

            return;
        }

        Printer.printInfo(
                "=== SIGNED REQUESTS ===");

        for (Request r : signedRequests) {
            System.out.println(r);
        }
    }

    // ===== SCHOOL MANAGEMENT =====

    /**
     * View students of school
     */
    public void viewStudents(
            Vector<Student> students) {

        Printer.printInfo(
                "=== STUDENTS OF "
                        + school
                        + " ===");

        for (Student s : students) {

            if (s.getSchool() == school) {
                System.out.println(s);
            }
        }
    }

    /**
     * View teachers of school
     */
    public void viewTeachers(
            Vector<Teacher> teachers) {

        Printer.printInfo(
                "=== TEACHERS OF "
                        + school
                        + " ===");

        for (Teacher t : teachers) {

            if (t.getSchool() == school) {
                System.out.println(t);
            }
        }
    }

    /**
     * Create teacher of scool
     * @param scanner
     * @param deanSchool
     * @return
     */
    public Teacher createTeacherDirectly(Scanner scanner, Enums.SCHOOLS deanSchool) {
        try {
            Printer.println("--- Registering a New Teacher ---");

            Printer.printAction("First name: ", false);
            String f_name = scanner.nextLine().trim();

            Printer.printAction("Last name: ", false);
            String l_name = scanner.nextLine().trim();

            Printer.printAction("Email: ", false);
            String email = scanner.nextLine().trim();

            Printer.printAction("Phone number: ", false);
            String phoneNumber = scanner.nextLine().trim();

            LocalDate birthday = null;
            while (birthday == null) {
                try {
                    Printer.printAction("Birth date (YYYY-MM-DD): ", false);
                    birthday = LocalDate.parse(scanner.nextLine().trim());
                } catch (java.time.format.DateTimeParseException e) {
                    Printer.println("Invalid date format. Please use YYYY-MM-DD.");
                }
            }

            Enums.GENDER gender = null;
            while (gender == null) {
                try {
                    Printer.printAction("Gender (MALE/FEMALE): ", false);
                    gender = Enums.GENDER.valueOf(scanner.nextLine().toUpperCase().trim());
                } catch (IllegalArgumentException e) {
                    Printer.println("Invalid gender. Please enter MALE or FEMALE.");
                }
            }

            Printer.printAction("Password: ", false);
            String password = scanner.nextLine().trim();

            Enums.TEACHER_LVL teacherLvl = chooseTeacherLevel(scanner);

            Enums.SCHOOLS school = deanSchool;

            Teacher teacher = new Teacher(f_name, l_name, email, phoneNumber, birthday, gender, password, teacherLvl,
                    school);

            Printer.printSucces("Teacher entity successfully generated!");
            return teacher;

        } catch (Exception e) {
            Printer.printError("Error while creating teacher: " + e.getMessage());
            return null;
        }
    }

    private Enums.TEACHER_LVL chooseTeacherLevel(Scanner scanner) {
        while (true) {
            Printer.println("\nSelect Teacher Level:");
            Enums.TEACHER_LVL[] levels = Enums.TEACHER_LVL.values();
            for (int i = 0; i < levels.length; i++) {
                Printer.println(String.format(" [%d] -> %s", (i + 1), levels[i].name()));
            }
            Printer.printAction("Choose level: ", false);
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= 1 && choice <= levels.length) {
                    return levels[choice - 1];
                } else {
                    Printer.println("Invalid option.");
                }
            } catch (NumberFormatException e) {
                Printer.println("Invalid input. Please enter a number.");
            }
        }
    }

    // ===== GETTERS =====

    public SCHOOLS getSchool() {
        return school;
    }

    public Vector<Request> getSignedRequests() {
        return signedRequests;
    }

    // ===== OBJECT METHODS =====

    @Override
    public String toString() {

        return "Dean: "
                + getFullName()
                + " | School: "
                + school;
    }
}