package Adamdar;

import java.time.LocalDate;
import java.util.*;

import Application.Printer;
import Enums.ACCESS_RIGHT;
import Enums.GENDER;
import Enums.SCHOOLS;

/**
 * @author Kim Alina
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

    public Manager createManager() {
        Scanner scanner = new Scanner(System.in);

        Manager m = null;

        while (true) {
            try {
                Printer.printInfo("### --- f_name --- ###");
                String f_name = scanner.nextLine();
                Printer.printInfo("### --- l_name --- ###");
                String l_name = scanner.nextLine();
                Printer.printInfo("### --- email --- ###");
                String email = scanner.nextLine();
                Printer.printInfo("### --- phoneNumber --- ###");
                String phoneNumber = scanner.nextLine();
                Printer.printInfo("### --- birthday --- ###");
                System.out.print("Year: ");
                int year = scanner.nextInt();
                System.out.print("\nMonth: ");
                int month = scanner.nextInt();
                System.out.println("\nday: ");
                int day = scanner.nextInt();
                LocalDate birthday = LocalDate.of(year, month, day);
                System.out.println();
                Printer.printSucces("### --- Gender (Male or Female) --- ###");
                String genderStr = scanner.nextLine();

                GENDER gender;

                if (genderStr.contains("female")) {
                    gender = GENDER.FEMALE;
                } else {
                    gender = GENDER.MALE;
                }

                Printer.printSucces("### --- Password --- ###");
                String password = scanner.nextLine();

                m = new Manager(f_name, l_name, email, phoneNumber, birthday, gender, password);
            } catch (Exception e) {
                Printer.printError("Error while creating MANAGER: " + e.toString());
            }

            break;
        }
        scanner.close();

        return m;
    }

    public Dean createDean() {
        Scanner scanner = new Scanner(System.in);

        Dean d = null;

        while (true) {
            try {
                Printer.printInfo("### --- f_name --- ###");
                String f_name = scanner.nextLine();
                Printer.printInfo("### --- l_name --- ###");
                String l_name = scanner.nextLine();
                Printer.printInfo("### --- email --- ###");
                String email = scanner.nextLine();
                Printer.printInfo("### --- phoneNumber --- ###");
                String phoneNumber = scanner.nextLine();
                Printer.printInfo("### --- birthday --- ###");
                System.out.print("Year: ");
                int year = scanner.nextInt();
                System.out.print("\nMonth: ");
                int month = scanner.nextInt();
                System.out.println("\nday: ");
                int day = scanner.nextInt();
                LocalDate birthday = LocalDate.of(year, month, day);
                System.out.println();
                Printer.printSucces("### --- Gender (Male or Female) --- ###");
                String genderStr = scanner.nextLine();

                GENDER gender;

                if (genderStr.contains("female")) {
                    gender = GENDER.FEMALE;
                } else {
                    gender = GENDER.MALE;
                }

                Printer.printSucces("### --- Password --- ###");
                String password = scanner.nextLine();

                SCHOOLS school = SCHOOLS.SITE;

                d = new Dean(f_name, l_name, email, phoneNumber, birthday, gender, password, school);
            } catch (Exception e) {
                Printer.printError("Error while creating DEAN: " + e.toString());
            }

            break;
        }
        scanner.close();

        return d;
    }
}