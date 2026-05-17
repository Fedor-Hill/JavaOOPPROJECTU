package Adamdar;

import Enums.*;

import Application.Printer;
import Application.Request;

import java.time.LocalDate;
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
    public Dean(String f_name, String l_name, String email, String phoneNumber, LocalDate birthday, GENDER gender, String password, SCHOOLS school) {
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
                    "Request is null!"
            );

            return;
        }

        request.setStatus(
                REQUEST_STATUS.APPROVED
        );

        signedRequests.add(request);

        Printer.printSucces(
                "Request signed by dean!"
        );
    }

    /**
     * Reject request
     */
    public void rejectRequest(Request request) {

        if (request == null) {

            Printer.printError(
                    "Request is null!"
            );

            return;
        }

        request.setStatus(
                REQUEST_STATUS.REJECTED
        );

        Printer.printWarning(
                "Request rejected by dean!"
        );
    }

    /**
     * View signed requests
     */
    public void viewSignedRequests() {

        if (signedRequests.isEmpty()) {

            Printer.printWarning(
                    "No signed requests."
            );

            return;
        }

        Printer.printInfo(
                "=== SIGNED REQUESTS ==="
        );

        for (Request r : signedRequests) {
            System.out.println(r);
        }
    }

    // ===== SCHOOL MANAGEMENT =====

    /**
     * View students of school
     */
    public void viewStudents(
            Vector<Student> students
    ) {

        Printer.printInfo(
                "=== STUDENTS OF "
                        + school
                        + " ==="
        );

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
            Vector<Teacher> teachers
    ) {

        Printer.printInfo(
                "=== TEACHERS OF "
                        + school
                        + " ==="
        );

        for (Teacher t : teachers) {

            if (t.getSchool() == school) {
                System.out.println(t);
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