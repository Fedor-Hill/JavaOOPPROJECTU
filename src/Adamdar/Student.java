package Adamdar;

import Research.Researcher;
import AcademicThigns.*;
import Enums.*;
import Research.ResearchDELO;
import AcademicThigns.Enrollment;

import java.io.*;
import java.util.*;

/**
 * @author Meiramkhan Alinur
 */
public class Student extends Adam implements Researcher {

    /**
     * Default constructor
     */
    public Student() {
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

    /**
     * 
     */
    public void Operation2() {
        // TODO implement Researcher.Operation2() here
    }

}