package Application;

import java.io.*;
import java.util.*;

import Adamdar.Adam;
import Adamdar.Admin;
import Adamdar.Student;

public class DataStorage {
    private static final String ADMIN_FILE = "admins.ser";
    private static final String AMADAR_FILE = "adamdar.ser";

    public static <T> void save(Map<String, T> map, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(map);
        } catch (IOException e) {
            System.err.println("Error saving to " + filename + ": " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> Map<String, T> load(String filename) {
        File file = new File(filename);
        if (!file.exists())
            return new HashMap<>(); 

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Map<String, T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading from " + filename + ": " + e.getMessage());
            return new HashMap<>();
        }
    }

    public static void saveAdmins(Map<String, Admin> admins) {
        save(admins, ADMIN_FILE);
    }

    public static Map<String, Admin> loadAdmins() {
        return load(ADMIN_FILE);
    }

    public static void saveAdamdar(Map<String, Adam> adamdar) {
        save(adamdar, AMADAR_FILE);
    }

    public static Map<String, Adam> loadAdamdar() {
        return load(AMADAR_FILE);
    }
}