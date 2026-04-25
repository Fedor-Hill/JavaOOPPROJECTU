package Application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import Adamdar.Admin;

public class DataStorage {
    private static final String FILE_USER = "users.ser";
    private static final String FILE_ADMIN = "admins.ser";

    public static void saveToFile(Object object, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(object);
            System.out.println("Data succes write to " + filename);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static Object loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error to load: " + e.getMessage());
            return null;
        }
    }

    @SuppressWarnings("unchecked") 
    public static List<Admin> loadAdmins() {
        File file = new File(FILE_ADMIN);

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Admin>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error when reading: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void saveAdmins(List<Admin> admins) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_ADMIN))) {
            oos.writeObject(admins);
        } catch (IOException e) {
            System.err.println("Error while saving: " + e.getMessage());
        }
    }
}
