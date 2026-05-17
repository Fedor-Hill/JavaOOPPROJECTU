package Adamdar;

import java.time.LocalDate;
import java.util.Vector;

import Application.Printer;
import Application.Request;

import Enums.*;

/**
 * Employee base class
 * 
 * @author Kim Alina
 */
public class Employee extends Adam {

    private String job_title;

    private double salary;

    private Vector<Request> requests;

    private Vector<String> messages;

    /**
     * Constructor
     */
    public Employee(String f_name, String l_name, String email, String phoneNumber, LocalDate birthday, GENDER gender, String password, String job_title) {
        super(f_name, l_name, email, phoneNumber, birthday, gender, password);

        this.job_title = job_title;

        this.salary = 0.0;

        this.requests = new Vector<>();

        this.messages = new Vector<>();
    }

    // ===== MESSAGES =====

    /**
     * Send message
     */
    public void sendMessage(Employee employee, String message) {

        if (employee == null) {
            Printer.printError("Employee is null!");
            return;
        }

        String formattedMessage =
                "FROM: "
                + getFullName()
                + " -> "
                + message;

        employee.receiveMessage(formattedMessage);

        Printer.printSucces("Message sent!");
    }

    /**
     * Receive message
     */
    public void receiveMessage(String message) {
        messages.add(message);
    }

    /**
     * View inbox
     */
    public void viewMessages() {

        if (messages.isEmpty()) {
            Printer.printWarning("No messages.");
            return;
        }

        Printer.printInfo("=== MESSAGES ===");

        for (String msg : messages) {
            System.out.println(msg);
        }
    }

    // ===== REQUESTS =====

    /**
     * Send request
     */
    public void sendRequest(String description) {

        Request request =
                new Request(
                        getId(),
                        description
                );

        requests.add(request);

        Printer.printSucces("Request created!");
    }

    /**
     * View requests
     */
    public void viewMyRequests() {

        if (requests.isEmpty()) {
            Printer.printWarning("No requests.");
            return;
        }

        Printer.printInfo("=== REQUESTS ===");

        for (Request r : requests) {
            System.out.println(r);
        }
    }

    // ===== GETTERS =====

    public String getJob_title() {
        return job_title;
    }

    public double getSalary() {
        return salary;
    }

    public Vector<Request> getRequests() {
        return requests;
    }

    public Vector<String> getMessages() {
        return messages;
    }

    // ===== SETTERS =====

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    // ===== OBJECT METHODS =====

    @Override
    public String toString() {

        return "Employee: "
                + getFullName()
                + " | Position: "
                + job_title
                + " | Salary: "
                + salary;
    }
}