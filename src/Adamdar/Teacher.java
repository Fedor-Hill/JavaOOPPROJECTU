package Adamdar;

import Research.Researcher;
import Enums.*;
import Research.ResearchDELO;

import java.time.LocalDate;
import java.util.*;

/**
 * @author Meiramkhan Alinur
 */
public class Teacher extends Employee implements Researcher {

    ResearchDELO researchDELO = new ResearchDELO();

    /**
     * Default constructor
     */
    public Teacher(String f_name, String l_name, String email, String phoneNumber, LocalDate birthday, GENDER gender, String password) {
        super(f_name, l_name, email, phoneNumber, birthday, gender, phoneNumber, password);
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
     * @return ResearchDELO 
     */
    public ResearchDELO getResearchDELO() {

        if (lvl == TEACHER_LVL.Proffessor) {
            return researchDELO;
        }

        return null;
    }
}