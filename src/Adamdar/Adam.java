package Adamdar;

import Enums.GENDER;

import java.io.*;
import java.util.*;
import java.time.*;

/**
 * @author Meiramkhan Alinur
 */
public abstract class Adam {

    /**
     * Default constructor
     */
    public Adam() {
    }

    /**
     * 
     */
    private String id;

    /**
     * 
     */
    private String f_name;

    /**
     * 
     */
    private String l_name;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private String phoneNumber;

    /**
     * 
     */
    private LocalDate birthday;

    /**
     * 
     */
    private GENDER gender;

    /**
     * @return
     */
    public String getId() {
        // TODO implement here
        return "";
    }

    /**
     * @return
     */
    public int getAge() {
        // TODO implement here
        return 0;
    }

}