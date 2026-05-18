package Adamdar;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Vector;

import AcademicThigns.Subject;
import Application.AdamdarCreationRequest;
import Application.Printer;
import Application.Request;
import Application.RequestHandler;

import Enums.*;

/**
 * Manager class
 * 
 * @author Kim Alina
 */
public class Manager extends Employee implements RequestHandler {

        private MANAGER_TYPE type;

        private Vector<ACCESS_RIGHT> accesses;

        /**
         * Constructor
         */
        public Manager(String f_name, String l_name, String email, String phoneNumber, LocalDate birthday,
                        GENDER gender, String password, MANAGER_TYPE type) {
                super(f_name, l_name, email, phoneNumber, birthday, gender, password, "Manager");

                this.type = type;

                this.accesses = new Vector<>();
        }

        // ===== REGISTRATION =====

        /**
         * Approve student registration
         */
        public void approveRegistration(Student student,
                        Subject subject) {

                student.registerSubject(subject);

                Printer.printSucces(
                                "Registration approved!");
        }

        /**
         * Reject registration
         */
        public void rejectRegistration(Student student,
                        Subject subject) {

                Printer.printWarning(
                                "Registration rejected for "
                                                + student.getFullName());
        }

        // ===== SUBJECT MANAGEMENT =====

        /**
         * Assign subject to teacher
         */
        public void assignSubjectToTeacher(Teacher teacher,
                        Subject subject) {

                teacher.addSubject(subject);

                Printer.printSucces(
                                "Subject assigned successfully!");
        }

        /**
         * Add subject for registration
         */
        public void addSubjectForRegistration(Subject subject) {

                Printer.printSucces(
                                subject.getTitle()
                                                + " added for registration.");
        }

        // ===== REPORTS =====

        /**
         * Create GPA report
         */
        public void createPerformanceReport(Vector<Student> students) {

                Printer.printInfo(
                                "=== PERFORMANCE REPORT ===");

                for (Student s : students) {

                        System.out.println(
                                        s.getFullName()
                                                        + " | GPA: "
                                                        + s.getGpa());
                }
        }

        // ===== SORTING =====

        /**
         * Sort students by GPA
         */
        public void sortStudentsByGpa(Vector<Student> students) {

                Collections.sort(students);

                Printer.printSucces(
                                "Students sorted by GPA!");
        }

        /**
         * Sort students alphabetically
         */
        public void sortStudentsAlphabetically(
                        Vector<Student> students) {

                students.sort(
                                (s1, s2) -> s1.getFullName()
                                                .compareTo(
                                                                s2.getFullName()));

                Printer.printSucces(
                                "Students sorted alphabetically!");
        }

        // ===== REQUESTS =====

        @Override
        public void viewRequests() {

                if (getRequests().isEmpty()) {

                        Printer.printWarning(
                                        "No requests.");

                        return;
                }

                for (Request r : getRequests()) {
                        System.out.println(r);
                }
        }

        @Override
        public void approveRequest() {

                if (getRequests().isEmpty()) {
                        Printer.printWarning("No requests to approve.");
                        return;
                }

                Request requestToApprove = null;

                for (int i = 0; i < getRequests().size(); i++) {
                        Request r = getRequests().get(i);

                        if (!(r instanceof AdamdarCreationRequest)) {
                                requestToApprove = r;
                                break;
                        }
                }

                if (requestToApprove == null) {
                        Printer.printWarning(
                                        "All pending requests are Student Registration profiles awaiting the Dean's approval.");
                        Printer.printInfo("Manager cannot process these files.");
                        return;
                }

                requestToApprove.setStatus(Enums.REQUEST_STATUS.APPROVED);
                Printer.printSucces("Request successfully approved by Manager!");
        }

        @Override
        public void rejectRequest() {

                if (getRequests().isEmpty()) {
                        Printer.printWarning("No requests to approve.");
                        return;
                }

                Request requestToApprove = null;

                for (int i = 0; i < getRequests().size(); i++) {
                        Request r = getRequests().get(i);

                        if (!(r instanceof AdamdarCreationRequest)) {
                                requestToApprove = r;
                                break;
                        }
                }

                if (requestToApprove == null) {
                        Printer.printWarning(
                                        "All pending requests are Student Registration profiles awaiting the Dean's approval.");
                        Printer.printInfo("Manager cannot process these files.");
                        return;
                }

                requestToApprove.setStatus(Enums.REQUEST_STATUS.REJECTED);
                Printer.printSucces("Request successfully approved by Manager!");
        }

        // ===== GETTERS =====

        public MANAGER_TYPE getType() {
                return type;
        }

        public Vector<ACCESS_RIGHT> getAccesses() {
                return accesses;
        }

        // ===== OBJECT METHODS =====

        @Override
        public String toString() {

                return "Manager: "
                                + getFullName()
                                + " | Type: "
                                + type;
        }
}