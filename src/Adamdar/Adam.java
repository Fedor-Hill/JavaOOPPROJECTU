package Adamdar;

import Enums.GENDER;
import Application.IdGenerator;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

/**
 * Base abstract class for all people in the university system
 * 
 * @author Kim Alina
 */
public abstract class Adam {

    private String id;
    private String f_name;
    private String l_name;
    private String email;
    private String phoneNumber;
    private LocalDate birthday;
    private GENDER gender;
    private String password;

    /**
     * Constructor
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
     * Authentication
     */
    public boolean loginTo(String password) {
        return this.password.equals(password);
    }

    /**
     * Get full name
     */
    public String getFullName() {
        return f_name + " " + l_name;
    }

    /**
     * Calculate real age
     */
    public int getAge() {
        return Period.between(birthday, LocalDate.now()).getYears();
    }

    // ===== GETTERS =====

    public String getId() {
        return id;
    }

    public String getF_name() {
        return f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public GENDER getGender() {
        return gender;
    }

    // ===== SETTERS =====

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // ===== OBJECT METHODS =====

    @Override
    public String toString() {
        return "ID: " + id +
                " | Name: " + getFullName() +
                " | Email: " + email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Adam adam)) return false;

        return Objects.equals(id, adam.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}