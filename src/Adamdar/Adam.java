package Adamdar;

import Enums.GENDER;

import java.io.*;
import java.util.*;

import Application.IdGenerator;

import java.time.*;

/**
 * @author Kim Alina
 */
public abstract class Adam {

    /**
     * Default constructor
     */
    public Adam(String f_name, String l_name, String email, String phoneNumber, LocalDate birthday, GENDER gender, String password) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.email = email;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;

        this.id = IdGenerator.generateStudentId();
        this.password = password; 
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

    private String password; 

    public boolean loginTo(String password) {
        return this.password.equals(password);
    };

    /**
     * @return String
     */
    public String getId() {
        return this.id;
    }

    /**
     * TODO: UDPDATE
     * 
     * @return int
     */
    public int getAge() {

        return 19;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getF_name() {
        return f_name;
    }

    public GENDER getGender() {
        return gender;
    }

    public String getL_name() {
        return l_name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}