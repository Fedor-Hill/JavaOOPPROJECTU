package Application;

import java.time.LocalDate;
import java.util.*;
import Adamdar.*;
import Enums.GENDER;
import Enums.SCHOOLS;

/**
 * @author Meiramkhan Alinur
 * @version v2
 */
public class VVSP {

    public List<Student> spisok;

    public static void executeVVSP() {
        new LoginMenu().show();
    }
}

class TUIConsole {

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void waitForEnter() {
        System.out.println("\n[ Press ENTER to continue... ]");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }
}

class LoginMenu {
    private Scanner scanner = new Scanner(System.in);
    private boolean isRunning = true;
    private Admin admin = null;
    // private Adam adam = null;

    private static String truncate(String text, int length) {
        if (text == null)
            return "-";
        if (text.length() <= length)
            return text;
        return text.substring(0, length - 3) + "...";
    }

    public void show() {

        while (isRunning) {
            TUIConsole.clearScreen();
            Printer.println("=================================");
            Printer.println("       Wellcome to VVSP          ");
            Printer.println("=================================");
            Printer.println(" [1] -> LOGIN");
            Printer.println(" [2] -> ABOUT");
            Printer.println(" [67]-> EXIT");
            Printer.printAction("\nChoose action: ", false);

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    Printer.debugPrintInfo("-> selected: LOGIN");
                    printLogin();
                    break;
                case "67":
                    Printer.debugPrintInfo("-> selected: EXIT");
                    isRunning = false;
                    System.out.println("Goodbye lol...");
                    break;
                case "2":
                    printBlankPage();
                    break;
                default:
                    System.out.println("ERROR: Wrong input");
                    TUIConsole.waitForEnter();
            }
        }

    }

    private void printBlankPage() {

        while (true) {
            Printer.printWarning("Its just blank page loool...");
            TUIConsole.waitForEnter();
            return;
        }
    }

    private void printLogin() {

        while (true) {
            //
            // FIND ADMINS
            //

            Map<String, Admin> admins = DataStorage.loadAdmins();
            if (admins.isEmpty()) {
                LocalDate defaultDate = LocalDate.of(1970, 1, 1);
                Admin admin = new Admin("0", "fenya", "fenya@kbtu.kz", defaultDate, GENDER.CROISSANT, "1234");

                admins.put(admin.getName(), admin);
                DataStorage.saveAdmins(admins);
            }

            //
            // FIND USERS (Adamdars)
            //

            Map<String, Adam> adamdar = DataStorage.loadAdamdar();
            TUIConsole.clearScreen();
            Printer.println("=================================");
            Printer.println("       VVSP: LOGIN PAGE          ");
            Printer.println("=================================");
            Printer.println("67 for exit\n");

            System.out.print("Login: ");
            String login = scanner.nextLine().trim();
            if (login.equals("67"))
                return;

            System.out.print("Password: ");
            String password = scanner.nextLine().trim();

            Admin currentAdmin = admins.get(login);
            if (currentAdmin != null && currentAdmin.loginTo(password)) {
                this.admin = currentAdmin;
                System.out.println("Success (Admin)!");
                TUIConsole.waitForEnter();
                printAdminPanel();
                return;
            }

            Adam adam = adamdar.get(login);
            if (adam != null && adam.loginTo(password)) {
                System.out.println("Success!");
                TUIConsole.waitForEnter();

                if (adam instanceof Student) {
                    printStudentPanel((Student) adam);
                } else if (adam instanceof Teacher) {
                    // printTeacherPanel((Teacher) adam);
                    printBlankPage();
                }

                return;
            }

            System.out.println("Wrong login or password...");
            TUIConsole.waitForEnter();
        }

    }

    private void printAdminPanel() {
        while (true) {
            TUIConsole.clearScreen();
            Printer.println("=================================");
            Printer.println("       ADMIN PANEL: " + this.admin.getName().toUpperCase());
            Printer.println("=================================");
            Printer.println(" [1]-> Create ADAM");
            Printer.println(" [2]-> Edit ADAM");
            Printer.println(" [3]-> Delete ADAM");
            Printer.println(" [4]-> View ADAM");
            Printer.println(" [67]-> EXIT");

            Printer.printAction("\nChoose action: ", false);

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    Printer.debugPrintInfo("-> selected: Create ADAM");
                    Printer.printInfo("-> selected: Create ADAM");
                    TUIConsole.waitForEnter();
                    TUIConsole.clearScreen();
                    AdamdarCreationRequest arc = new AdamdarCreationRequest(this.admin.getId(), "epta");
                    Student st = arc.createStudent(scanner);
                    TUIConsole.waitForEnter();
                    TUIConsole.clearScreen();

                    if (st != null) {
                        Map<String, Adam> ada = DataStorage.loadAdamdar();
                        ada.put(st.getEmail(), st);
                        DataStorage.saveAdamdar(ada);
                    }
                }

                case "2" -> {
                    Printer.debugPrintInfo("-> selected: Edit ADAM");
                    TUIConsole.waitForEnter();
                }

                case "3" -> {
                    Printer.debugPrintInfo("-> selected: remove ADAM");
                    // TUIConsole.waitForEnter();
                    Printer.printAction("Email: ", false);
                    String email = scanner
                            .nextLine();
                    Map<String, Adam> ada = DataStorage.loadAdamdar();

                    if (ada.containsKey(email)) {
                        ada.remove(email);
                        DataStorage.saveAdamdar(ada);
                        Printer.printSucces("Adam war removed...");
                        TUIConsole.waitForEnter();
                    } else {
                        Printer.println("┌────────────────────────────────────────────────────────┐");
                        Printer.println("│               No Adams found in database               │");
                        Printer.println("└────────────────────────────────────────────────────────┘");
                    }

                }

                case "4" -> {
                    Printer.debugPrintInfo("-> selected: View ADAM");

                    Map<String, Adam> ada = DataStorage.loadAdamdar();
                    if (ada == null || ada.isEmpty()) {
                        Printer.println("┌────────────────────────────────────────────────────────┐");
                        Printer.println("│               No Adams found in database               │");
                        Printer.println("└────────────────────────────────────────────────────────┘");
                    } else {
                        Printer.println(
                                "┌──────────────────────┬─────────────────────────────────┬────────────────────────┐");
                        Printer.println(
                                "│ Name                 │ Email                           │ Role / Type            │");
                        Printer.println(
                                "├──────────────────────┼─────────────────────────────────┼────────────────────────┤");

                        for (Adam adam : ada.values()) {
                            String fullName = adam.getF_name() + " "
                                    + (adam.getL_name() != null ? adam.getL_name() : "");
                            String email = adam.getEmail() != null ? adam.getEmail() : "-";
                            String role = adam.getClass().getSimpleName();

                            String line = String.format("│ %-20s │ %-31s │ %-22s │",

                                    truncate(fullName, 20),
                                    truncate(email, 31),
                                    truncate(role, 22));

                            Printer.println(line);
                        }
                        Printer.println(
                                "└──────────────────────┴─────────────────────────────────┴────────────────────────┘");
                        Printer.printInfo("Total users found: " + ada.size() + "\n");
                    }

                    TUIConsole.waitForEnter();
                }
                case "67" -> {
                    Printer.debugPrintInfo("-> selected: EXIT");
                    TUIConsole.waitForEnter();
                    return;
                }

                default -> {
                    System.out.println("ERROR: Wrong input");
                    TUIConsole.waitForEnter();
                }
            }
        }
    }

    private void printStudentPanel(Student student) {
        while (true) {
            TUIConsole.clearScreen();
            Printer.println("=================================");
            Printer.println("       USER PANEL: " + student.getF_name().toUpperCase());
            Printer.println("=================================");
            Printer.println(" [1]-> Student Private Data");
            Printer.println(" [67]-> EXIT");

            Printer.printAction("\nChoose action: ", false);

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    Printer.debugPrintInfo("-> selected: Student Private Data");
                    Printer.printInfo("-> selected: Student Private Data");

                    String fullName = student.getF_name() + " " + student.getL_name();
                    String email = student.getEmail();
                    String phone = student.getPhoneNumber();
                    String birthday = student.getBirthday().toString();
                    String gender = GENDER.MALE.toString();
                    String school = SCHOOLS.SITE.toString();
                    String majorStr = "VTiPO СИЛА";

                    Printer.println("┌──────────────────────────────────────────────────────────────────────────┐");
                    Printer.println("│                           STUDENT PROFILE                                │");
                    Printer.println("├──────────────────────────────────────────────────────────────────────────┤");

                    printProfileRow("Full Name", fullName);
                    printProfileRow("Gender", gender);
                    printProfileRow("Birth Date", birthday);

                    Printer.println("├──────────────────────────────────────────────────────────────────────────┤");

                    printProfileRow("Email Address", email);
                    printProfileRow("Phone Number", phone);

                    Printer.println("├──────────────────────────────────────────────────────────────────────────┤");

                    printProfileRow("School / Faculty", school);
                    printProfileRow("Major / Specialty", majorStr);

                    Printer.println("├──────────────────────────────────────────────────────────────────────────┤");

                    String maskedPassword = "**************" + " (hidden)";
                    printProfileRow("Account Password", maskedPassword);

                    Printer.println("└──────────────────────────────────────────────────────────────────────────┘");

                    TUIConsole.waitForEnter();
                }

                case "67" -> {
                    Printer.debugPrintInfo("-> selected: EXIT");
                    TUIConsole.waitForEnter();
                    return;
                }

                default -> {
                    System.out.println("ERROR: Wrong input");
                    TUIConsole.waitForEnter();
                }
            }
        }
    }

    private static void printProfileRow(String label, String value) {
        int maxLabelLength = 18;
        int maxValueLength = 51;

        String truncatedValue = truncate(value, maxValueLength);

        String paddedLabel = padRight(label, maxLabelLength);
        String paddedValue = padRight(truncatedValue, maxValueLength);

        Printer.println("│ " + paddedLabel + " : " + paddedValue + " │");
    }

    private static String padRight(String text, int length) {
        if (text.length() >= length) {
            return text;
        }

        StringBuilder sb = new StringBuilder(text);
        while (sb.length() < length) {
            sb.append(" ");
        }
        return sb.toString();
    }
}