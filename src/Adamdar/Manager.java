package Adamdar;

import java.time.LocalDate;
import java.util.*;

import Application.RequestHandler;
import Application.AdamdarCreationRequest;
import Enums.ACCESS_RIGHT;
import Enums.GENDER;


/**
 * @author Meiramkhan Alinur
 */
public class Manager extends Employee implements RequestHandler {

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

    public void createAdamRequest() {
        if (this.accesses.contains(ACCESS_RIGHT.CREATE_STUDENT)) {
            AdamdarCreationRequest req = new AdamdarCreationRequest(this.getId(), "approve new Student"); 
        }
    }


    @Override
    public void viewRequests() {
        
    }

    @Override
    public void approveRequest() {
        
        
    }

    @Override
    public void rejectRequest() {
        
    }
}