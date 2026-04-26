package Adamdar;

import Research.Researcher;
import Enums.*;
import Research.ResearchDELO;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * @author Meiramkhan Alinur
 */
public class Teacher extends Employee implements Researcher {

    /**
     * Default constructor
     */
    public Teacher(String f_name, String l_name, String email, String phoneNumber, LocalDate birthday, GENDER gender) {
        super(f_name, l_name, email, phoneNumber, birthday, gender, phoneNumber);
    }

    /**
     * 
     */
    private TEACHER_LVL lvl;

    /**
     * 
     */
    private List<ACCESS_RIGHT> accesses;

    /**
     * @return
     */
    public ResearchDELO getResearchDELO() {
        // TODO implement Researcher.getResearchDELO() here
        return null;
    }
}