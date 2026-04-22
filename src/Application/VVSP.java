package Application;

import java.io.*;
import java.util.*;
import Adamdar.Student;

/**
 * @author Meiramkhan Alinur
 */
public class VVSP {

    /**
     * Default constructor
     */
    public VVSP() {
    }

    /**
     * 
     */
    public List<Student> spisok;

    public void executeVVSP() {
        new Window().run();;
    }

}

class Window {
    private Scanner scanner = new Scanner(System.in);
    private boolean isRunning = true;

    public void run() {
        while (isRunning) {
            printMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("-> Выбрано: Просмотр студентов");
                    break;
                case "2":
                    System.out.println("-> Выбрано: Добавить научный проект");
                    break;
                case "0":
                    isRunning = false;
                    System.out.println("Выход из системы...");
                    break;
                default:
                    System.out.println("Ошибка: неверный выбор");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n--- VVSP SYSTEM MENU ---");
        System.out.println("1. Просмотр студентов");
        System.out.println("2. Добавить научный проект");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }
}