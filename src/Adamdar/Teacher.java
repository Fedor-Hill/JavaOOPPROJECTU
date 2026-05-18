package Adamdar;

import Research.Researcher;
import Enums.*;
import Research.ResearchDELO;

import AcademicThigns.*;
import Application.Printer;

import java.time.LocalDate;
import java.util.Vector;

/**
 * Teacher class
 * 
 * @author Kim Alina
 */
public class Teacher extends Employee implements Researcher {

    private ResearchDELO researchDELO = new ResearchDELO();

    private TEACHER_LVL lvl;

    private Vector<ACCESS_RIGHT> accesses;

    private Vector<Subject> subjects;

    private double rating;
    private int ratesAmount;

    private SCHOOLS school;

    /**
     * Constructor
     */
    public Teacher(String f_name, String l_name, String email, String phoneNumber, LocalDate birthday, GENDER gender,
            String password, TEACHER_LVL lvl, SCHOOLS school) {
        super(f_name, l_name, email, phoneNumber, birthday, gender, password, "Teacher");

        this.lvl = lvl;
        this.school = school;

        this.subjects = new Vector<>();

        this.rating = 0.0;
        this.ratesAmount = 0;

        this.accesses = new Vector<>();
    }

    // ===== SUBJECT MANAGEMENT =====

    /**
     * Add subject
     */
    public void addSubject(Subject subject) {

        if (!subjects.contains(subject)) {
            subjects.add(subject);

            subject.addTeacher(this);

            System.out.println("Subject added successfully!");
        }
    }

    /**
     * Remove subject
     */
    public void removeSubject(Subject subject) {

        if (subjects.remove(subject)) {
            System.out.println("Subject removed!");
        } else {
            System.out.println("Subject not found!");
        }
    }

    /**
     * View subjects
     */
    public void viewSubjects() {

        if (subjects.isEmpty()) {
            System.out.println("No subjects.");
            return;
        }

        for (Subject s : subjects) {
            System.out.println(s);
        }
    }

    // ===== STUDENT MANAGEMENT =====

    /**
     * View students of subject
     */
    public void viewStudents(Subject subject, Vector<Student> students) {

        System.out.println("Students of " + subject.getTitle());

        for (Student s : students) {

            if (s.getRegisteredSubjects().contains(subject)) {
                System.out.println(s);
            }
        }
    }

    // ===== MARKS =====

    /**
     * Put marks
     */
    public void putMarks(Student student,
            Subject subject,
            double firstAttestation,
            double secondAttestation,
            double finalExam) {

        for (Enrollment e : student.getTranscript()) {

            if (e.getTakedSubject().equals(subject)) {

                Attestation attestation = new Attestation(
                        firstAttestation,
                        secondAttestation,
                        finalExam);

                e.setAttestation(attestation);

                System.out.println("Marks updated!");

                return;
            }
        }

        Enrollment enrollment = new Enrollment(
                subject,
                new Attestation(
                        firstAttestation,
                        secondAttestation,
                        finalExam),
                "FALL",
                2026);

        student.addEnrollment(enrollment);

        System.out.println("Marks added!");
    }

    // ===== RATING =====

    /**
     * Add student rating
     */
    public void addRating(double rate) {

        if (rate < 0 || rate > 5) {
            System.out.println("Invalid rating!");
            return;
        }

        rating = ((rating * ratesAmount) + rate)
                / (ratesAmount + 1);

        ratesAmount++;
    }

    // ===== RESEARCH =====

    @Override
    public ResearchDELO getResearchDELO() {

        if (lvl == TEACHER_LVL.PROFESSOR) {
            return researchDELO;
        }

        return null;
    }

    // ===== GETTERS =====

    public TEACHER_LVL getLvl() {
        return lvl;
    }

    public Vector<Subject> getSubjects() {
        return subjects;
    }

    public double getRating() {
        return rating;
    }

    public Vector<ACCESS_RIGHT> getAccesses() {
        return accesses;
    }

    public SCHOOLS getSchool() {
        return school;
    }

    // ===== OBJECT METHODS =====

    @Override
    public String toString() {

        return "Teacher: "
                + getFullName()
                + " | Level: "
                + lvl
                + " | Rating: "
                + String.format("%.2f", rating);
    }

    public void generateReport(
            Subject subject,
            Vector<Student> students) {

        Printer.printInfo(
                "=== REPORT FOR "
                        + subject.getTitle()
                        + " ===");

        double total = 0;
        int count = 0;
        int passed = 0;
        int failed = 0;

        for (Student s : students) {

            for (Enrollment e : s.getTranscript()) {

                if (e.getTakedSubject().equals(subject)) {

                    double grade = e.getAttestation()
                            .getDigitGrade();

                    System.out.println(
                            s.getFullName()
                                    + " | Grade: "
                                    + grade);

                    total += grade;
                    count++;

                    if (grade >= 50) {
                        passed++;
                    } else {
                        failed++;
                    }
                }
            }
        }

        if (count == 0) {

            Printer.printWarning(
                    "No data for report.");

            return;
        }

        double average = total / count;

        Printer.printInfo(
                "\n=== STATISTICS ===");

        System.out.println(
                "Average grade: "
                        + average);

        System.out.println(
                "Passed: "
                        + passed);

        System.out.println(
                "Failed: "
                        + failed);
    }
}