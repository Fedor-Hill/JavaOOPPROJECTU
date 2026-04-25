package Adamdar;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import Enums.ACCESS_RIGHT;
import Enums.GENDER;

/**
 * @author Meiramkhan Alinur
 */
public class Admin extends Eva {

    /**
     * Default constructor
     */
    public Admin(String id, String name, String email, LocalDate birthday, GENDER gender, String password) {
        super(id, name, email, birthday, gender, password);
    }

    /**
     * 
     */
    private List<ACCESS_RIGHT> accesses = List.of(ACCESS_RIGHT.ALL);

    public List<ACCESS_RIGHT> getAccesses() {
        return accesses;
    }

}