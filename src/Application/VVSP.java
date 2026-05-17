package Application;

import java.time.LocalDate;
import java.util.*;
import Adamdar.*;
import Enums.GENDER;

/**
 * @author Meiramkhan Alinur
 * @version v3
 */
public class VVSP {

    public List<Student> spisok;

    public static void executeVVSP() {
        new LoginMenu().show();
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

    private void selectLanguageMenu() {
        TUIConsole.clearScreen();
        Printer.println("=== SELECT LANGUAGE / ТІЛ ТАНДАУ / ВЫБЕРИТЕ ЯЗЫК ===");
        Printer.println("1: English");
        Printer.println("2: Қазақша");
        Printer.println("3: Русский");
        Printer.printAction("Choose (1-3): ", false);

        String langChoice = scanner.nextLine().trim();
        switch (langChoice) {
            case "1" -> {
                LangManager.setLanguage("en");
            }
            case "2" -> {
                LangManager.setLanguage("kk");
            }
            case "3" -> {
                LangManager.setLanguage("ru");
            }
            default -> {
                LangManager.setLanguage("kk");
            }
        }
    }

    public void show() {
        selectLanguageMenu();

        while (isRunning) {
            TUIConsole.clearScreen();
            Printer.println("=================================");
            Printer.println("       " + LangManager.get("main_title") + "          ");
            Printer.println("=================================");
            Printer.println(LangManager.get("menu_login"));
            Printer.println(LangManager.get("menu_about"));
            Printer.println(LangManager.get("menu_exit"));
            Printer.printAction(LangManager.get("choose_action"), false);

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    Printer.printInfo("-> selected: LOGIN");
                    printLogin();
                case "67":
                    isRunning = false;
                    System.out.println(LangManager.get("goodbye"));
                    break;
                case "2":
                    printBlankPage();
                    break;
                default:
                    System.out.println(LangManager.get("error_input"));
                    TUIConsole.waitForEnter();
            }
        }
    }

    private void printLogin() {
        boolean isRunning = true;

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

        while (isRunning) {
            System.out.println("--- VVSP LOGIN PAGE ---");
            System.out.print("login: ");
            String login = scanner.nextLine();
            System.out.print("password: ");
            String password = scanner.nextLine();

            Admin admin = admins.get(login);

            //
            // FIND USERS (Adamdars)
            //

            Map<String, Adam> adamdar = DataStorage.loadAdamdar();
            TUIConsole.clearScreen();
            Printer.println("=================================");
            Printer.println("       " + LangManager.get("login_title") + "          ");
            Printer.println("=================================");
            Printer.println(LangManager.get("for_exit") + "\n");

            System.out.print(LangManager.get("login_login"));
            String login = scanner.nextLine().trim();
            if (login.equals("67"))
                return;

            System.out.print(LangManager.get("login_password"));
            String password = scanner.nextLine().trim();

            Admin currentAdmin = admins.get(login);
            if (currentAdmin != null && currentAdmin.loginTo(password)) {
                this.admin = currentAdmin;
                System.out.println(LangManager.get("login_success_admin"));
                TUIConsole.waitForEnter();
                printAdminPanel();
            } else {
                Printer.printError("Login or password incorrect");
            }

            Adam adam = adamdar.get(login);

            if (adam != null && adam.loginTo(password)) {
                if (adam instanceof Student) {
                    printStudentPanel((Student) adam);
                } else if (adam instanceof Teacher) {
                    printTeacherPanel((Teacher) adam);
                } else if (adam instanceof Manager) {
                    printManagerPanel((Manager) adam);
                } else if (adam instanceof Dean) {
                    printDeanPanel((Dean) adam);
                }
            }

            System.out.println(LangManager.get("login_wrong_login_or_psw"));
            TUIConsole.waitForEnter();
        }
    }

    private void printAdminPanel() {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("--- ADMIN PANEL: " + this.admin.getName().toUpperCase() + " ---");
            System.out.println("1: Create ADAM");
            System.out.println("2: Edit User");
            System.out.println("3: Delete User");
            System.out.println("67: EXIT");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    Printer.printInfo("Create Dean (1) or Manager (2)");
                    String choid = scanner.nextLine();

                case "4" -> {
                    Printer.debugPrintInfo("-> selected: View ADAM");

                    Map<String, Adam> ada = DataStorage.loadAdamdar();
                    if (ada == null || ada.isEmpty()) {
                        Printer.println("┌────────────────────────────────────────────────────────┐");
                        Printer.println("│ " + String.format("%-54s", LangManager.get("adam_not_found_in_db")) + " │");
                        Printer.println("└────────────────────────────────────────────────────────┘");
                    } else {
                        String colName = LangManager.get("name");
                        String colEmail = LangManager.get("email");
                        String colRole = LangManager.get("adamdar_role_type");

                        Printer.println(
                                "┌──────────────────────┬─────────────────────────────────┬────────────────────────┐");
                        Printer.println(String.format("│ %-20s │ %-31s │ %-22s │", colName, colEmail, colRole));
                        Printer.println(
                                "├──────────────────────┼─────────────────────────────────┼────────────────────────┤");

                        for (Adam adam : ada.values()) {
                            String fullName = adam.getF_name() + " "
                                    + adam.getL_name();
                            String email = adam.getEmail();
                            String role = adam.getClass().getSimpleName();

                            String line = String.format("│ %-20s │ %-31s │ %-22s │",

                                    truncate(fullName, 20),
                                    truncate(email, 31),
                                    truncate(role, 22));

                            Printer.println(line);
                        }
                        Printer.println(
                                "└──────────────────────┴─────────────────────────────────┴────────────────────────┘");
                        Printer.printInfo(LangManager.get("total_user_found") + ada.size() + "\n");
                    }

                    TUIConsole.waitForEnter();
                }
                case "67" -> {
                    Printer.debugPrintInfo("-> selected: EXIT");
                    TUIConsole.waitForEnter();
                    return;
                }

                    Printer.printInfo("-> selected: Create User");
                    break;
                case "67":
                    isRunning = false;
                    Printer.printSucces("Goodbye lol...");
                default:
                    Printer.printError("ERROR: wrong input stoopid lol");
            }
        }
    }

    private void printStudentPanel(Student student) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("--- Student PANEL: " + student.getF_name().toUpperCase() + " ---");
            System.out.println("67: EXIT");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "67":
                    isRunning = false;
                    Printer.printSucces("Goodbye lol...");
                default:
                    Printer.printError("ERROR: wrong input stoopid lol");
            }
        }
    }

    private void printTeacherPanel(Teacher teacher) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("--- Student PANEL: " + teacher.getF_name().toUpperCase() + " ---");
            System.out.println("67: EXIT");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "67":
                    isRunning = false;
                    Printer.printSucces("Goodbye lol...");
                default:
                    Printer.printError("ERROR: wrong input stoopid lol");
            }
        }
    }

    private void printManagerPanel(Manager manager) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("--- Student PANEL: " + manager.getF_name().toUpperCase() + " ---");
            System.out.println("1: ");
            System.out.println("67: EXIT");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "67":
                    isRunning = false;
                    Printer.printSucces("Goodbye lol...");
                default:
                    Printer.printError("ERROR: wrong input stoopid lol");
            }
        }
    }

    private void printDeanPanel(Dean dean) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("--- Student PANEL: " + dean.getF_name().toUpperCase() + " ---");
            System.out.println("67: EXIT");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "67":
                    isRunning = false;
                    Printer.printSucces("Goodbye lol...");
                default:
                    Printer.printError("ERROR: wrong input stoopid lol");
            }
        }
    }
}