package Adamdar;

import Research.Researcher;
import AcademicThigns.*;
import Enums.*;
import Research.ResearchDELO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Student class
 * 
 * @author Kim Alina
 */
public class Student extends Adam implements Researcher, Comparable<Student> {

    private int course;
    private Major major;
    private double gpa;

    private SCHOOLS school;
    private PROGRAMS program;

    private Vector<Subject> registeredSubjects;
    private Vector<Enrollment> transcript;

    /**
     * Constructor
     */
    public Student(String f_name, String l_name, String email, String phoneNumber, LocalDate birthday, GENDER gender,
            String password,
            Major major, SCHOOLS school, PROGRAMS program) {
        super(f_name, l_name, email, phoneNumber, birthday, gender, password);

        this.major = major;
        this.school = school;
        this.program = program;

        this.course = 1;
        this.gpa = 0.0;

        this.registeredSubjects = new Vector<>();
        this.transcript = new Vector<>();
    }

    // ===== SUBJECT REGISTRATION =====

    /**
     * Register subject
     */
    public void registerSubject(Subject subject) {

        if (registeredSubjects.contains(subject)) {

            System.out.println(
                    "Subject already registered!");

            return;
        }

        if (!subject.isAvailableForMajor(major)) {

            System.out.println(
                    "Subject is not available for your major!");

            return;
        }

        if (getTotalCredits()
                + subject.getCredits() > getMaxCredits()) {

            System.out.println(
                    "Credit limit exceeded!");

            return;
        }

        registeredSubjects.add(subject);

        System.out.println(
                "Subject registered successfully!");
    }

    /**
     * Drop subject
     */
    public void dropSubject(Subject subject) {

        if (registeredSubjects.remove(subject)) {

            System.out.println(
                    "Subject dropped!");
        } else {

            System.out.println(
                    "Subject not found!");
        }
    }

    /**
     * View registered subjects
     */
    public void viewSubjects() {

        if (registeredSubjects.isEmpty()) {

            System.out.println(
                    "No registered subjects.");

            return;
        }

        for (Subject s : registeredSubjects) {
            System.out.println(s);
        }
    }

    /**
     * Count credits
     */
    public int getTotalCredits() {

        int total = 0;

        for (Subject s : registeredSubjects) {
            total += s.getCredits();
        }

        return total;
    }

    /**
     * Different limits for programs
     */
    public int getMaxCredits() {

        switch (program) {

            case BACHELOR:
                return 21;

            case MASTER:
                return 24;

            case PhD:
                return 18;

            default:
                return 21;
        }
    }

    // ===== TRANSCRIPT =====

    /**
     * Add enrollment
     */
    public void addEnrollment(
            Enrollment enrollment) {

        transcript.add(enrollment);
    }

    /**
     * View transcript
     */
    public void viewTranscript() {

        if (transcript.isEmpty()) {

            System.out.println(
                    "Transcript is empty.");

            return;
        }

        for (Enrollment e : transcript) {
            System.out.println(e);
        }
    }

    /**
     * View marks
     */
    public void viewMarks() {

        for (Enrollment e : transcript) {

            System.out.println(
                    e.getTakedSubject().getTitle()
                            + " : "
                            + e.getAttestation()
                                    .getDigitGrade());
        }
    }

    /**
     * View shedule
     */
    public List<ScheduledLesson> getMySchedule(Map<String, ScheduledLesson> globalSchedule) {
        List<ScheduledLesson> mySchedule = new ArrayList<>();

        if (this.registeredSubjects == null || this.registeredSubjects.isEmpty()) {
            return mySchedule;
        }

        for (ScheduledLesson lesson : globalSchedule.values()) {
            if (this.registeredSubjects.contains(lesson.getCourse())) {
                mySchedule.add(lesson);
            }
        }

        return mySchedule;
    }

    /**
     * Calculate GPA
     */
    public double calculateGpa() {

        if (transcript == null || transcript.isEmpty()) {
            this.gpa = 0.0;
            return 0.0;
        }

        double totalGradePoints = 0.0;
        int totalCredits = 0;

        for (Enrollment e : transcript) {
            int courseCredits = e.getTakedSubject().getCredits();

            double digitGrade = e.getAttestation().getDigitGrade();

            totalGradePoints += digitGrade * courseCredits;

            totalCredits += courseCredits;
        }

        if (totalCredits == 0) {
            this.gpa = 0.0;
        } else {
            this.gpa = totalGradePoints / totalCredits;
        }

        return this.gpa;
    }

    // ===== TEACHERS =====

    /**
     * View teachers of subject
     */
    public void viewTeachers(
            Subject subject) {

        for (Teacher t : subject.getTeachers()) {
            System.out.println(t);
        }
    }

    /**
     * Rate teacher
     */
    public void rateTeacher(
            Teacher teacher,
            double rate) {

        teacher.addRating(rate);
    }

    // ===== PROGRAM FEATURES =====

    /**
     * Check if graduate student
     */
    public boolean isGraduateStudent() {

        return program == PROGRAMS.MASTER
                || program == PROGRAMS.PhD;
    }

    /**
     * Check if can lead research
     */
    public boolean canLeadResearch() {

        return program == PROGRAMS.PhD;
    }

    /**
     * PhD special feature
     */
    public void publishResearchPaper() {

        if (program != PROGRAMS.PhD) {

            System.out.println(
                    "Only PhD students can publish papers!");

            return;
        }

        System.out.println(
                getFullName()
                        + " published a research paper!");
    }

    // ===== RESEARCH =====

    @Override
    public ResearchDELO getResearchDELO() {

        if (program == PROGRAMS.PhD) {
            return new ResearchDELO();
        }

        return null;
    }

    // ===== GETTERS =====

    public int getCourse() {
        return course;
    }

    public Major getMajor() {
        return major;
    }

    public double getGpa() {
        return calculateGpa();
    }

    public SCHOOLS getSchool() {
        return school;
    }

    public PROGRAMS getProgram() {
        return program;
    }

    public Vector<Subject> getRegisteredSubjects() {
        return registeredSubjects;
    }

    public Vector<Enrollment> getTranscript() {
        return transcript;
    }

    // ===== COMPARABLE =====

    @Override
    public int compareTo(Student other) {

        return Double.compare(
                this.getGpa(),
                other.getGpa());
    }

    // ===== OBJECT METHODS =====

    @Override
    public String toString() {

        return "Student: "
                + getFullName()
                + " | Program: "
                + program
                + " | Total score: "
                + String.format("%.2f", getGpa());
    }
}