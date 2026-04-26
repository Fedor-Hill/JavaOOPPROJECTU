package Adamdar;

import Enums.GENDER;
import Enums.SCHOOLS;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * @author Meiramkhan Alinur
 */
public class Dean extends Employee {

    /**
     * Default constructor
     */
    public Dean(String f_name, String l_name, String email, String phoneNumber, LocalDate birthday, GENDER gender, String password, SCHOOLS school) {
        super(f_name, l_name, email, phoneNumber, birthday, gender, phoneNumber, password);
        this.school = school;
    }

    /**
     * 
     */
    private SCHOOLS school;

    public SCHOOLS getSchool() {
        return school;
    }

}