package Adamdar;

import java.io.*;
import java.time.*;
import Enums.GENDER;

/**
 * @author Kim Alina
 */
public abstract class Eva implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public Eva(String id, String name, String email, LocalDate birthday, GENDER gender, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.gender = gender;
        this.password = password;
    }

    /**
     * 
     */
    private String id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    public LocalDate birthday;

    /**
     * 
     */
    private GENDER gender;

    private String password;

    public boolean loginTo(String password) {
        return this.password.equals(password);
    }; 

    public String getId() {
        return id;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public GENDER getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }
}