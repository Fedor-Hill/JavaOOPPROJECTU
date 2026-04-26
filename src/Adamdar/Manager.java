package Adamdar;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import Enums.ACCESS_RIGHT;
import Enums.GENDER;

/**
 * @author Meiramkhan Alinur
 */
public class Manager extends Employee {

    /**
     * Default constructor
     */
    public Manager(String f_name, String l_name, String email, String phoneNumber, LocalDate birthday, GENDER gender, String password) {
        super(f_name, l_name, email, phoneNumber, birthday, gender, phoneNumber, password);
    }

    /**
     * 
     */
    private String info;

    /**
     * 
     */
    private List<ACCESS_RIGHT> accesses;

}