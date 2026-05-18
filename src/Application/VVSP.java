package Application;

import java.time.LocalDate;
import java.util.*;

import AcademicThigns.LessonType;
import AcademicThigns.ScheduledLesson;
import AcademicThigns.Subject;
import Adamdar.*;
import Enums.GENDER;
import Enums.PROGRAMS;
import Enums.SCHOOLS;

/**
 * @author Meiramkhan Alinur
 * @version v4
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

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    Printer.debugPrintInfo("-> selected: LOGIN");
                    printLogin();
                    break;
                case "67":
                    Printer.debugPrintInfo("-> selected: EXIT");
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
                return;
            }

            Adam adam = adamdar.get(login);
            if (adam != null && adam.loginTo(password)) {
                System.out.println("Success!");
                TUIConsole.waitForEnter();

                if (adam instanceof Student) {
                    printStudentPanel((Student) adam);
                } else if (adam instanceof Teacher) {
                    printTeacherPanel((Teacher) adam);
                } else if (adam instanceof Manager) {
                    printManagerPanel((Manager) adam);
                } else if (adam instanceof Dean) {
                    printDeanPanel((Dean) adam);
                }

                return;
            }

            System.out.println(LangManager.get("login_wrong_login_or_psw"));
            TUIConsole.waitForEnter();
        }

    }

    private void printAdminPanel() {
        while (true) {
            TUIConsole.clearScreen();
            Printer.println("=================================");
            Printer.println("       ADMIN PANEL: " + this.admin.getName().toUpperCase());
            Printer.println("=================================");
            Printer.println(" [1]-> Create ADAM (Student/Manager/Dean)");
            Printer.println(" [2]-> Edit ADAM");
            Printer.println(" [3]-> Delete ADAM");
            Printer.println(" [4]-> View ADAM");
            Printer.println(" [67]-> EXIT");

            Printer.printAction("\nChoose action: ", false);

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    TUIConsole.clearScreen();
                    Printer.println("=== WHAT TYPE OF ADAM DO YOU WANT TO CREATE? ===");
                    Printer.println(" [1] -> Student");
                    Printer.println(" [2] -> Manager");
                    Printer.println(" [3] -> Dean");
                    Printer.printAction("Choose option: ", false);
                    String typeChoice = scanner.nextLine().trim();

                    Adam newAdam = null;

                    if (typeChoice.equals("1")) {
                        TUIConsole.clearScreen();
                        AdamdarCreationRequest arc = new AdamdarCreationRequest(this.admin.getId(), "creation");
                        newAdam = arc.createStudent(scanner);
                    } else if (typeChoice.equals("2")) {
                        TUIConsole.clearScreen();
                        newAdam = this.admin.createManager(scanner);
                    } else if (typeChoice.equals("3")) {
                        TUIConsole.clearScreen();
                        newAdam = this.admin.createDean(scanner);
                    } else {
                        Printer.printError("Invalid type selection.");
                    }

                    if (newAdam != null) {
                        Map<String, Adam> ada = DataStorage.loadAdamdar();
                        ada.put(newAdam.getEmail(), newAdam);
                        DataStorage.saveAdamdar(ada);
                    }
                    TUIConsole.waitForEnter();
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
            Printer.println(" [2]  -> View Available & Register for Subject");
            Printer.println(" [3]  -> Drop Registered Subject");
            Printer.println(" [4]  -> View Registered Subjects (Current Semester)");
            Printer.println(" [5]  -> View Transcript & My Marks");
            Printer.println(" [6]  -> Rate a Teacher");
            Printer.println(" [7]  -> Rate a Teacher");
            if (student.isGraduateStudent()) {
                Printer.println(" [7]  -> Graduate Research Dashboard");
            }
            Printer.println(" [8]  -> Schedule");
            Printer.println(" [67]-> EXIT");

            Printer.printAction("\nChoose action: ", false);

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    Printer.debugPrintInfo("-> selected: Student Private Data");
                    Printer.printInfo("-> selected: Student Private Data");

                    // Динамически вытаскиваем данные из объекта student
                    String fullName = student.getF_name() + " " + student.getL_name();
                    String email = student.getEmail();
                    String phone = student.getPhoneNumber();
                    String birthday = (student.getBirthday() != null) ? student.getBirthday().toString() : "N/A";
                    String gender = (student.getGender() != null) ? student.getGender().toString() : "N/A";
                    String schoolStr = (student.getSchool() != null) ? student.getSchool().toString() : "N/A";

                    String majorStr = student.getMajor().getName().getTranslatedName();

                    // Твоя красивая псевдографическая рамка
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

                    printProfileRow("School / Faculty", schoolStr);
                    printProfileRow("Major / Specialty", majorStr);
                    printProfileRow("Current Course", String.valueOf(student.getCourse()));

                    Printer.println("├──────────────────────────────────────────────────────────────────────────┤");

                    String maskedPassword = "**************" + " (hidden)";
                    printProfileRow("Account Password", maskedPassword);

                    Printer.println("└──────────────────────────────────────────────────────────────────────────┘");

                    TUIConsole.waitForEnter();
                }

                case "2" -> {
                    Printer.debugPrintInfo("-> selected: Register for Subject");
                    TUIConsole.clearScreen();
                    Printer.println("=== SUBJECT REGISTRATION ===");

                    Map<String, Subject> allSubjectsMap = DataStorage.loadSubjects();

                    List<Subject> availableForMe = new ArrayList<>();

                    for (Subject sub : allSubjectsMap.values()) {
                        if (sub.isAvailableForMajor(student.getMajor())) {
                            availableForMe.add(sub);
                        }
                    }

                    if (availableForMe.isEmpty()) {
                        Printer.printWarning("No subjects found available for your major.");
                    } else {
                        for (int i = 0; i < availableForMe.size(); i++) {
                            Subject s = availableForMe.get(i);
                            Printer.println(String.format(" [%d] -> %s (%s) | Credits: %d",
                                    (i + 1), s.getTitle(), s.getId(), s.getCredits()));
                        }

                        Printer.printAction("\nEnter subject number to register (0 to cancel): ", false);
                        try {
                            int subIdx = Integer.parseInt(scanner.nextLine().trim());
                            if (subIdx > 0 && subIdx <= availableForMe.size()) {
                                Subject selectedSub = availableForMe.get(subIdx - 1);

                                student.registerSubject(selectedSub);

                                Map<String, Adam> ada = DataStorage.loadAdamdar();
                                ada.put(student.getEmail(), student);
                                DataStorage.saveAdamdar(ada);
                            }
                        } catch (Exception e) {
                            Printer.printError("Invalid choice.");
                        }
                    }
                    TUIConsole.waitForEnter();
                }

                case "3" -> {
                    Printer.debugPrintInfo("-> selected: Drop Subject");
                    TUIConsole.clearScreen();
                    Printer.println("=== DROP REGISTERED SUBJECT ===");
                    Vector<Subject> mySubs = student.getRegisteredSubjects();

                    if (mySubs.isEmpty()) {
                        Printer.printWarning("You don't have any registered subjects yet.");
                    } else {
                        for (int i = 0; i < mySubs.size(); i++) {
                            Printer.println(String.format(" [%d] -> %s", (i + 1), mySubs.get(i).getTitle()));
                        }

                        Printer.printAction("\nEnter subject number to DROP (0 to cancel): ", false);
                        try {
                            int dropIdx = Integer.parseInt(scanner.nextLine().trim());
                            if (dropIdx > 0 && dropIdx <= mySubs.size()) {
                                Subject toDrop = mySubs.get(dropIdx - 1);

                                student.dropSubject(toDrop); // Твой метод удаления

                                // Сохраняем базу
                                Map<String, Adam> ada = DataStorage.loadAdamdar();
                                ada.put(student.getEmail(), student);
                                DataStorage.saveAdamdar(ada);
                            }
                        } catch (Exception e) {
                            Printer.printError("Invalid choice.");
                        }
                    }
                    TUIConsole.waitForEnter();
                }

                case "4" -> {
                    Printer.debugPrintInfo("-> selected: View Registered Subjects");
                    TUIConsole.clearScreen();
                    Printer.println("=== CURRENT SEMESTER REGISTERED SUBJECTS ===");
                    student.viewSubjects(); // Твой метод из класса Student
                    TUIConsole.waitForEnter();
                }

                case "5" -> {
                    Printer.debugPrintInfo("-> selected: View Transcript & Marks");
                    TUIConsole.clearScreen();
                    Printer.println("=== ACADEMIC TRANSCRIPT ===");
                    student.viewTranscript(); // Твой метод
                    Printer.println("\n--- Current Grade Breakdown ---");
                    student.viewMarks(); // Твой метод
                    TUIConsole.waitForEnter();
                }

                case "6" -> {
                    Printer.debugPrintInfo("-> selected: Rate a Teacher");
                    TUIConsole.clearScreen();
                    Printer.println("=== RATE A TEACHER ===");
                    Vector<Subject> mySubs = student.getRegisteredSubjects();

                    if (mySubs.isEmpty()) {
                        Printer.printWarning("Register for subjects first to see your instructors.");
                    } else {
                        List<Teacher> myTeachers = new ArrayList<>();
                        for (Subject s : mySubs) {
                            for (Teacher t : s.getTeachers()) {
                                if (!myTeachers.contains(t)) {
                                    myTeachers.add(t);
                                }
                            }
                        }

                        if (myTeachers.isEmpty()) {
                            Printer.printWarning("No teachers are currently assigned to your subjects.");
                        } else {
                            for (int i = 0; i < myTeachers.size(); i++) {
                                Printer.println(String.format(" [%d] -> %s (Faculty: %s)",
                                        (i + 1), myTeachers.get(i).getFullName(), myTeachers.get(i).getSchool()));
                            }

                            Printer.printAction("\nSelect teacher to rate (0 to cancel): ", false);
                            try {
                                int tIdx = Integer.parseInt(scanner.nextLine().trim());
                                if (tIdx > 0 && tIdx <= myTeachers.size()) {
                                    Teacher targetTeacher = myTeachers.get(tIdx - 1);

                                    Printer.printAction("Enter score (1.0 to 10.0): ", false);
                                    double rate = Double.parseDouble(scanner.nextLine().trim());

                                    if (rate >= 1.0 && rate <= 10.0) {
                                        student.rateTeacher(targetTeacher, rate); // Твой метод рейтинга

                                        // Сохраняем обновленного преподавателя в общую базу
                                        Map<String, Adam> ada = DataStorage.loadAdamdar();
                                        ada.put(targetTeacher.getEmail(), targetTeacher);
                                        DataStorage.saveAdamdar(ada);

                                        Printer.printSucces("Thank you! Rating submitted successfully.");
                                    } else {
                                        Printer.printError("Invalid range! Please rate between 1.0 and 10.0");
                                    }
                                }
                            } catch (Exception e) {
                                Printer.printError("Input error: " + e.getMessage());
                            }
                        }
                    }
                    TUIConsole.waitForEnter();
                }

                case "7" -> {
                    if (student.isGraduateStudent()) {
                        Printer.debugPrintInfo("-> selected: Graduate Research Dashboard");
                        TUIConsole.clearScreen();
                        Printer.println("=== GRADUATE RESEARCH DASHBOARD ===");
                        if (student.getProgram() == PROGRAMS.PhD) {
                            Printer.println("Status: Head of Research Group");
                            Printer.println(" [1] -> Publish New Research Paper");
                            Printer.printAction("Choose action: ", false);
                            if (scanner.nextLine().trim().equals("1")) {
                                student.publishResearchPaper(); // Твой PhD метод
                            }
                        } else {
                            Printer.println("Status: Master's Degree Researcher");
                            Printer.println("Feature: Access to ResearchDELO system logs granted.");
                        }
                        TUIConsole.waitForEnter();
                    } else {
                        System.out.println("ERROR: Wrong input");
                        TUIConsole.waitForEnter();
                    }
                }

                case "8" -> {
                    Printer.debugPrintInfo("-> selected: View My Weekly Schedule");
                    TUIConsole.clearScreen();
                    Printer.println("===========================================================================");
                    Printer.println("                      WEEKLY SCHEDULE FOR: " + student.getF_name().toUpperCase());
                    Printer.println("===========================================================================");

                    Map<String, ScheduledLesson> globalSchedule = DataStorage.loadSchedule();

                    List<ScheduledLesson> myLessons = student.getMySchedule(globalSchedule);

                    if (myLessons.isEmpty()) {
                        Printer.printWarning("Your schedule is empty. Please register for subjects first.");
                    } else {
                        for (int i = 0; i < myLessons.size(); i++) {
                            ScheduledLesson lesson = myLessons.get(i);

                            String courseTitle = (lesson.getCourse() != null) ? lesson.getCourse().getTitle()
                                    : "Unknown Course";
                            String type = (lesson.getLessonType() != null) ? lesson.getLessonType().toString() : "N/A";
                            String instructor = (lesson.getTeacher() != null) ? lesson.getTeacher().getFullName()
                                    : "TBA";
                            String timeSlot = (lesson.getSlot() != null) ? lesson.getSlot().toString()
                                    : "No time specified";
                            String roomNum = (lesson.getRoom() != null) ? lesson.getRoom().toString() : "Online/TBA";

                            Printer.println(String.format(" [%d] -> %s (%s)", (i + 1), courseTitle, type));
                            Printer.println(String.format("       Time: %s | Room: %s", timeSlot, roomNum));
                            Printer.println(String.format("       Instructor: %s", instructor));
                            Printer.println(
                                    "---------------------------------------------------------------------------");
                        }
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

    private void printManagerPanel(Manager manager) {
        while (true) {
            TUIConsole.clearScreen();
            Printer.println("=================================");
            Printer.println("       MANAGER PANEL: " + manager.getF_name().toUpperCase());
            Printer.println("       TYPE: " + manager.getType().toString() + "\n=================================");
            Printer.println(" [1]-> Course & Registration Management");
            Printer.println(" [2]-> Teacher Assignment");
            Printer.println(" [3]-> Performance Reports & Sorting");
            Printer.println(" [4]-> Manage Requests");
            Printer.println(" [5]-> Create Student");
            Printer.println(" [67]-> LOGOUT");

            Printer.printAction("\nChoose action: ", false);
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    TUIConsole.clearScreen();
                    Printer.println("=== Course & Registration Management ===");
                    Printer.println(" [1] -> Open Registration for a Subject");
                    Printer.println(" [2] -> Approve Student Registration");
                    Printer.println(" [3] -> Reject Student Registration");
                    Printer.printAction("Choose sub-action: ", false);
                    String sub = scanner.nextLine().trim();

                    Map<String, Adam> ada = DataStorage.loadAdamdar();

                    if (sub.equals("1")) {
                        Printer.println("\n--- CREATING NEW SUBJECT ---");

                        Printer.printAction("Enter Course ID (e.g., CS101): ", false);
                        String id = scanner.nextLine().trim();

                        Printer.printAction("Enter Course Title (e.g., OOP): ", false);
                        String title = scanner.nextLine().trim();

                        int credits = 0;
                        while (true) {
                            try {
                                Printer.printAction("Enter Credits: ", false);
                                credits = Integer.parseInt(scanner.nextLine().trim());
                                break;
                            } catch (NumberFormatException e) {
                                Printer.printError("Please enter a valid number for credits.");
                            }
                        }

                        LessonType type = LessonType.LECTURE;
                        while (true) {
                            try {
                                Printer.printAction("Enter Lesson Type (LECTURE / PRACTICE / LAB): ", false);
                                type = LessonType.valueOf(scanner.nextLine().toUpperCase().trim());
                                break;
                            } catch (IllegalArgumentException e) {
                                Printer.printError("Invalid type! Choose from LECTURE, PRACTICE, LAB.");
                            }
                        }

                        Subject subj = new Subject(id, title, credits, type);

                        Printer.printAction("Enter Intended Year of Study (1-4, or 0 to skip): ", false);
                        try {
                            int year = Integer.parseInt(scanner.nextLine().trim());
                            subj.setIntendedYear(year);
                        } catch (NumberFormatException e) {
                        }

                        manager.addSubjectForRegistration(subj);

                    } else if (sub.equals("2") || sub.equals("3")) {
                        Printer.printAction("Enter Student Email: ", false);
                        String email = scanner.nextLine().trim();
                        Student student = (Student) ada.get(email);

                        if (student != null) {
                            Printer.printAction("Enter Subject ID: ", false);
                            String subjId = scanner.nextLine().trim();

                            Subject subj = new Subject();
                            subj.setId(subjId);

                            if (sub.equals("2"))
                                manager.approveRegistration(student, subj);
                            else
                                manager.rejectRegistration(student, subj);
                        } else {
                            Printer.printError("Student not found!");
                        }
                    }
                    TUIConsole.waitForEnter();
                }

                case "2" -> {
                    TUIConsole.clearScreen();
                    Printer.println("=== Teacher Assignment ===");
                    Map<String, Adam> ada = DataStorage.loadAdamdar();

                    Printer.printAction("Enter Teacher Email: ", false);
                    String tEmail = scanner.nextLine().trim();
                    Teacher teacher = (Teacher) ada.get(tEmail);

                    if (teacher != null) {
                        Printer.printAction("Enter Subject ID to assign: ", false);
                        String id = scanner.nextLine().trim();

                        Subject subj = new Subject();
                        subj.setId(id);

                        Printer.printAction("Enter Subject Title: ", false);
                        String title = scanner.nextLine().trim();
                        subj.setTitle(title);

                        manager.assignSubjectToTeacher(teacher, subj);
                    } else {
                        Printer.printError("Teacher not found!");
                    }
                    TUIConsole.waitForEnter();
                }

                case "3" -> {
                    TUIConsole.clearScreen();
                    Printer.println("=== Performance Reports & Sorting ===");
                    Printer.println(" [1] -> View Performance Report (GPA)");
                    Printer.println(" [2] -> Sort Students by GPA");
                    Printer.println(" [3] -> Sort Students Alphabetically");
                    Printer.printAction("Choose sub-action: ", false);
                    String sub = scanner.nextLine().trim();

                    Map<String, Adam> ada = DataStorage.loadAdamdar();
                    Vector<Student> students = new Vector<>();
                    for (Adam a : ada.values()) {
                        if (a instanceof Student) {
                            students.add((Student) a);
                        }
                    }

                    if (students.isEmpty()) {
                        Printer.printWarning("No students found in database.");
                    } else {
                        if (sub.equals("1")) {
                            manager.createPerformanceReport(students);
                        } else if (sub.equals("2")) {
                            manager.sortStudentsByGpa(students);
                            students.forEach(s -> System.out.println(s.getFullName() + " | GPA: " + s.getGpa()));
                        } else if (sub.equals("3")) {
                            manager.sortStudentsAlphabetically(students);
                            students.forEach(s -> System.out.println(s.getFullName()));
                        }
                    }
                    TUIConsole.waitForEnter();
                }

                case "4" -> {
                    TUIConsole.clearScreen();
                    Printer.println("=== Request Management ===");
                    Printer.println(" [1] -> View All Requests");
                    Printer.println(" [2] -> Approve Latest Request");
                    Printer.println(" [3] -> Reject Latest Request");
                    Printer.printAction("Choose sub-action: ", false);
                    String sub = scanner.nextLine().trim();

                    if (sub.equals("1")) {
                        manager.viewRequests();
                    } else if (sub.equals("2")) {
                        manager.approveRequest();
                    } else if (sub.equals("3")) {
                        manager.rejectRequest();
                    }
                    TUIConsole.waitForEnter();
                }
                case "5" -> {
                    TUIConsole.clearScreen();
                    Printer.println("=== CREATE STUDENT REGISTRATION REQUEST ===");

                    AdamdarCreationRequest creationRequest = new AdamdarCreationRequest(
                            manager.getId(),
                            "Request for creating a new Student profile");
                    Student pendingStudent = creationRequest.createStudent(scanner);

                    if (pendingStudent != null) {
                        Map<String, Adam> ada = DataStorage.loadAdamdar();

                        Manager currentManager = (Manager) ada.get(manager.getEmail());

                        if (currentManager != null) {
                            currentManager.getRequests().add(creationRequest);
                            manager.getRequests().add(creationRequest);

                            DataStorage.saveAdamdar(ada);

                            Printer.printSucces("Registration request successfully forwarded to the Deanery!");
                        } else {
                            Printer.printError("Manager context sync error.");
                        }
                    }
                    TUIConsole.waitForEnter();
                }

                case "67" -> {
                    return;
                }

                default -> {
                    System.out.println("ERROR: Wrong input");
                    TUIConsole.waitForEnter();
                }
            }
        }
    }

    private void printDeanPanel(Dean dean) {
        while (true) {
            TUIConsole.clearScreen();
            Printer.println("=================================");
            Printer.println("       DEAN PANEL: " + dean.getF_name().toUpperCase());
            Printer.println("       FACULTY: " + dean.getSchool());
            Printer.println("=================================");
            Printer.println(" [1]-> View Faculty Students");
            Printer.println(" [2]-> View Faculty Statistics (Average GPA)");
            Printer.println(" [3]-> Manage Student Complaints & Requests");
            Printer.println(" [3]-> Create Teacher");
            Printer.println(" [67]-> LOGOUT");

            Printer.printAction("\nChoose action: ", false);
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    TUIConsole.clearScreen();
                    Printer.println("=== STUDENTS OF " + dean.getSchool() + " ===");

                    Map<String, Adam> ada = DataStorage.loadAdamdar();
                    int count = 0;

                    Printer.println("┌──────────────────────┬─────────────────────────────────┬──────────┐");
                    Printer.println(String.format("│ %-20s │ %-31s │ %-8s │", "Name", "Email", "GPA"));
                    Printer.println("├──────────────────────┼─────────────────────────────────┼──────────┤");

                    for (Adam a : ada.values()) {
                        if (a instanceof Student) {
                            Student s = (Student) a;
                            if (s.getSchool() == dean.getSchool()) {
                                count++;
                                Printer.println(String.format("│ %-20s │ %-31s │ %-8.2f │",
                                        truncate(s.getFullName(), 20),
                                        truncate(s.getEmail(), 31),
                                        s.getGpa()));
                            }
                        }
                    }
                    Printer.println("└──────────────────────┴─────────────────────────────────┴──────────┘");
                    Printer.printInfo("Total students in your faculty: " + count + "\n");
                    TUIConsole.waitForEnter();
                }

                case "2" -> {
                    TUIConsole.clearScreen();
                    Printer.println("=== FACULTY STATISTICS ===");

                    Map<String, Adam> ada = DataStorage.loadAdamdar();
                    double totalGpa = 0;
                    int studentCount = 0;

                    for (Adam a : ada.values()) {
                        if (a instanceof Student) {
                            Student s = (Student) a;
                            if (s.getSchool() == dean.getSchool()) {
                                totalGpa += s.getGpa();
                                studentCount++;
                            }
                        }
                    }

                    if (studentCount == 0) {
                        Printer.printWarning("No students registered in " + dean.getSchool() + " yet.");
                    } else {
                        double averageGpa = totalGpa / studentCount;
                        Printer.println(" Faculty/School : " + dean.getSchool());
                        Printer.println(" Total Students : " + studentCount);
                        Printer.println(String.format(" Average GPA    : %.2f", averageGpa));

                        if (averageGpa >= 3.0) {
                            Printer.printSucces("\nPerformance status: EXCELLENT");
                        } else if (averageGpa >= 2.0) {
                            Printer.printInfo("\nPerformance status: STABLE");
                        } else {
                            Printer.printWarning("\nPerformance status: NEEDS ATTENTION");
                        }
                    }
                    TUIConsole.waitForEnter();
                }

                case "3" -> {
                    TUIConsole.clearScreen();
                    Printer.println("=== DEANERY: STUDENT REGISTRATION APPROVAL ===");

                    Map<String, Adam> ada = DataStorage.loadAdamdar();
                    List<AdamdarCreationRequest> facultyRequests = new ArrayList<>();

                    for (Adam a : ada.values()) {
                        if (a instanceof Manager) {
                            Manager m = (Manager) a;
                            for (Request r : m.getRequests()) {
                                if (r instanceof AdamdarCreationRequest) {
                                    AdamdarCreationRequest acr = (AdamdarCreationRequest) r;

                                    if (acr.getStatus() == Enums.REQUEST_STATUS.PENDING &&
                                            acr.getStudent() != null &&
                                            acr.getStudent().getSchool() == dean.getSchool()) {

                                        facultyRequests.add(acr);
                                    }
                                }
                            }
                        }
                    }

                    if (facultyRequests.isEmpty()) {
                        Printer.printWarning("No pending student requests for school: " + dean.getSchool());
                    } else {
                        for (int i = 0; i < facultyRequests.size(); i++) {
                            Student s = facultyRequests.get(i).getStudent();
                            Printer.println(String.format(" [%d] -> Student: %s %s | Major: %s | Program: %s",
                                    (i + 1), s.getF_name(), s.getL_name(), s.getMajor().getTitle(), s.getProgram()));
                        }

                        Printer.printAction("\nEnter request number to process (0 to cancel): ", false);
                        try {
                            int choiceIdx = Integer.parseInt(scanner.nextLine().trim());
                            if (choiceIdx > 0 && choiceIdx <= facultyRequests.size()) {
                                AdamdarCreationRequest selectedRequest = facultyRequests.get(choiceIdx - 1);
                                Student approvedStudent = selectedRequest.getStudent();

                                Printer.println("\n [1] -> APPROVE (Register Student in University)");
                                Printer.println(" [2] -> REJECT");
                                Printer.printAction("Choose action: ", false);
                                String action = scanner.nextLine().trim();

                                if (action.equals("1")) {

                                    selectedRequest.setStatus(Enums.REQUEST_STATUS.APPROVED);

                                    ada.put(approvedStudent.getEmail(), approvedStudent);

                                    DataStorage.saveAdamdar(ada);

                                    Printer.printSucces(
                                            "Student " + approvedStudent.getFullName() + " successfully registered!");
                                } else if (action.equals("2")) {
                                    selectedRequest.setStatus(Enums.REQUEST_STATUS.REJECTED);
                                    DataStorage.saveAdamdar(ada);
                                    Printer.printWarning("Request was rejected.");
                                }
                            }
                        } catch (Exception e) {
                            Printer.printError("Error processing choice: " + e.getMessage());
                        }
                    }
                    TUIConsole.waitForEnter();
                }

                case "4" -> {
                    TUIConsole.clearScreen();
                    Printer.println("=== DEANERY: DIRECT TEACHER REGISTRATION ===");
                    Printer.println(
                            "Notice: New teacher will be automatically assigned to " + dean.getSchool() + " faculty.");
                    Printer.println("--------------------------------------------------");

                    Teacher newTeacher = dean.createTeacherDirectly(scanner, dean.getSchool());

                    if (newTeacher != null) {
                        Map<String, Adam> ada = DataStorage.loadAdamdar();

                        ada.put(newTeacher.getEmail(), newTeacher);

                        DataStorage.saveAdamdar(ada);

                        Printer.printSucces(
                                "Teacher " + newTeacher.getFullName() + " is now active and can log in.");
                    }
                    TUIConsole.waitForEnter();
                }

                case "67" -> {
                    return;
                }

                default -> {
                    System.out.println("ERROR: Wrong input");
                    TUIConsole.waitForEnter();
                }
            }
        }
    }

    private void printTeacherPanel(Teacher teacher) {
        while (true) {
            TUIConsole.clearScreen();
            Printer.println("=================================");
            Printer.println("       TEACHER PANEL: " + teacher.getF_name().toUpperCase());
            Printer.println(
                    "       RANK: " + teacher.getLvl() + " | RATING: " + String.format("%.2f", teacher.getRating()));
            Printer.println("=================================");
            Printer.println(" [1]-> View My Subjects");
            Printer.println(" [2]-> View Students in My Subject");
            Printer.println(" [3]-> Put / Update Student Marks");
            Printer.println(" [4]-> Generate Subject Performance Report");
            Printer.println(" [67]-> LOGOUT");

            Printer.printAction("\nChoose action: ", false);
            String choice = scanner.nextLine().trim();

            Map<String, Adam> ada = DataStorage.loadAdamdar();
            Vector<Student> allStudents = new Vector<>();
            for (Adam a : ada.values()) {
                if (a instanceof Student) {
                    allStudents.add((Student) a);
                }
            }

            switch (choice) {
                case "1" -> {
                    TUIConsole.clearScreen();
                    Printer.println("=== MY SUBJECTS ===");
                    teacher.viewSubjects();
                    TUIConsole.waitForEnter();
                }

                case "2" -> {
                    TUIConsole.clearScreen();
                    Printer.println("=== VIEW STUDENTS ===");
                    if (teacher.getSubjects().isEmpty()) {
                        Printer.printWarning("You don't teach any subjects yet.");
                    } else {
                        teacher.viewSubjects();
                        Printer.printAction("\nEnter Subject ID to view students: ", false);
                        String subjId = scanner.nextLine().trim();

                        Subject targetSubj = teacher.getSubjects().stream()
                                .filter(s -> s.getId().equals(subjId))
                                .findFirst().orElse(null);

                        if (targetSubj != null) {
                            teacher.viewStudents(targetSubj, allStudents);
                        } else {
                            Printer.printError("Subject not found or you don't teach it!");
                        }
                    }
                    TUIConsole.waitForEnter();
                }

                case "3" -> {
                    TUIConsole.clearScreen();
                    Printer.println("=== PUT MARKS ===");
                    if (teacher.getSubjects().isEmpty()) {
                        Printer.printWarning("You don't have active subjects to grade.");
                    } else {
                        Printer.printAction("Enter Student Email: ", false);
                        String email = scanner.nextLine().trim();
                        Student student = (Student) ada.get(email);

                        if (student != null) {
                            teacher.viewSubjects();
                            Printer.printAction("\nEnter Subject ID: ", false);
                            String subjId = scanner.nextLine().trim();

                            Subject targetSubj = teacher.getSubjects().stream()
                                    .filter(s -> s.getId().equals(subjId))
                                    .findFirst().orElse(null);

                            if (targetSubj != null) {
                                try {
                                    Printer.printAction("First Attestation (0-30): ", false);
                                    double att1 = Double.parseDouble(scanner.nextLine().trim());

                                    Printer.printAction("Second Attestation (0-30): ", false);
                                    double att2 = Double.parseDouble(scanner.nextLine().trim());

                                    Printer.printAction("Final Exam (0-40): ", false);
                                    double fExam = Double.parseDouble(scanner.nextLine().trim());

                                    teacher.putMarks(student, targetSubj, att1, att2, fExam);

                                    DataStorage.saveAdamdar(ada);

                                } catch (NumberFormatException e) {
                                    Printer.printError("Invalid numbers entered. Grading aborted.");
                                }
                            } else {
                                Printer.printError("Subject not found in your list!");
                            }
                        } else {
                            Printer.printError("Student not found!");
                        }
                    }
                    TUIConsole.waitForEnter();
                }

                case "4" -> {
                    TUIConsole.clearScreen();
                    Printer.println("=== GENERATE ACADEMIC REPORT ===");
                    if (teacher.getSubjects().isEmpty()) {
                        Printer.printWarning("No subjects available.");
                    } else {
                        teacher.viewSubjects();
                        Printer.printAction("\nEnter Subject ID for statistical report: ", false);
                        String subjId = scanner.nextLine().trim();

                        Subject targetSubj = teacher.getSubjects().stream()
                                .filter(s -> s.getId().equals(subjId))
                                .findFirst().orElse(null);

                        if (targetSubj != null) {
                            teacher.generateReport(targetSubj, allStudents);
                        } else {
                            Printer.printError("Subject not found!");
                        }
                    }
                    TUIConsole.waitForEnter();
                }

                case "67" -> {
                    return;
                }

                default -> {
                    System.out.println("ERROR: Wrong input");
                    TUIConsole.waitForEnter();
                }
            }
        }
    }
}