package Adamdar;

import Research.Researcher;
import AcademicThigns.*;
import Enums.*;
import Research.ResearchDELO;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * @author Kim Alina
 */
public class Student extends Adam implements Researcher {

    /**
     * Default constructor
     */
    public Student(String f_name, String l_name, String email, String phoneNumber, LocalDate birthday, GENDER gender, String password,
            Major major, SCHOOLS school) {
        super(f_name, l_name, email, phoneNumber, birthday, gender, password);
        this.major = major;

        this.transcript = new ArrayList<>();
        this.school = school; 

    }

    /**
     * 
     */
    private int course;

    /**
     * 
     */
    private Major major;

    /**
     * 
     */
    private double gpa;

    /**
     * 
     */
    public List<Enrollment> transcript;

    /**
     * 
     */
    private SCHOOLS school;

    /**
     * 
     */
    private PROGRAMS program;

    /**
     * @return
     */
    public ResearchDELO getResearchDELO() {
        // TODO implement Researcher.getResearchDELO() here
        return null;
    }
}