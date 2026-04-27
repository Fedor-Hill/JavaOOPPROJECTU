package Adamdar;

import java.time.LocalDate;

import Enums.GENDER;

/**
 * @author Meiramkhan Alinur
 */
public class Employee extends Adam {

    /**
     * Default constructor
     */
    public Employee(String f_name, String l_name, String email, String phoneNumber, LocalDate birthday, GENDER gender, String password, String job_title) {
        super(f_name, l_name, email, phoneNumber, birthday, gender, password);
        this.job_title = job_title; 
    }

    /**
     * 
     */
    private String job_title;

    /**
     * 
     */
    private double salary;

    public String getJob_title() {
        return job_title;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

}