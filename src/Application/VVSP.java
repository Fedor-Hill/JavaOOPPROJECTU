package Application;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import AcademicThigns.LessonType;
import AcademicThigns.Major;
import AcademicThigns.Room;
import AcademicThigns.ScheduleSlot;
import AcademicThigns.ScheduledLesson;
import AcademicThigns.Subject;
import Adamdar.*;
import Enums.GENDER;
import Enums.MAJOR;
import Enums.PROGRAMS;
import Enums.RoomType;
import Enums.SCHOOLS;
import Enums.TEACHER_LVL;

/**
 * @author Meiramkhan Alinur
 * @version v5
 */
public class VVSP {

    public List<Student> spisok;

    public static void executeVVSP() {
        Map<String, Subject> subjectsCatalog = new HashMap<>();

        Subject oop = new Subject("CSCI2106", "Object-Oriented Programming", 3, LessonType.LECTURE);
        oop.addAcceptableMajor(MAJOR.VTiPO);
        oop.addAcceptableMajor(MAJOR.IS);
        subjectsCatalog.put(oop.getId(), oop);

        Subject ads = new Subject("CS201", "Algorithms and Data Structures", 4, LessonType.LECTURE);
        ads.addAcceptableMajor(MAJOR.VTiPO);
        subjectsCatalog.put(ads.getId(), ads);

        Subject bigData = new Subject("DS501", "Big Data Analytics", 4, LessonType.LECTURE);
        bigData.addAcceptableMajor(MAJOR.IS);
        subjectsCatalog.put(bigData.getId(), bigData);

        DataStorage.saveSubjects(subjectsCatalog);
        System.out.println("[DEBUG] Каталог предметов успешно перезаписан!");

        ScheduleSlot oopLectureSlot = new ScheduleSlot(
                DayOfWeek.MONDAY,
                LocalTime.of(9, 0),
                LocalTime.of(11, 0),
                "446");

        ScheduledLesson oopLecture = new ScheduledLesson(
                "L_CSCI2106",
                oop,
                null,
                LessonType.LECTURE,
                oopLectureSlot,
                null);

        Map<String, Adam> adamdarMap = DataStorage.loadAdamdar();
        if (adamdarMap == null) {
            adamdarMap = new HashMap<>();
        }

        Major major = new Major(MAJOR.FINANCE, MAJOR.FINANCE.getTranslatedName(), 1);

        Student student = new Student(
                "Aruzhan",
                "Sapar",
                "aruzhan@kbtu.kz",
                "+77010000001",
                LocalDate.of(2004, 5, 10),
                GENDER.FEMALE,
                "vipka",
                major,  
                SCHOOLS.BS,  
                PROGRAMS.BACHELOR 
        );

        Teacher oopTeacher = new Teacher(
                "Muragul",
                "Muratbekova",
                "muratbekova@kbtu.kz",
                "hz lol",
                LocalDate.of(1990, 5, 15),
                GENDER.FEMALE,
                "super_cool",
                TEACHER_LVL.PROFESSOR,
                SCHOOLS.SITE);

        oopTeacher.addSubject(oop);

        adamdarMap.put(student.getEmail(), student);
        adamdarMap.put(oopTeacher.getEmail(), oopTeacher);
        DataStorage.saveAdamdar(adamdarMap);

        subjectsCatalog.put(oop.getId(), oop);
        DataStorage.saveSubjects(subjectsCatalog);

        Map<String, ScheduledLesson> scheduleMap = new HashMap<>();
        scheduleMap.put(oopLecture.getId(), oopLecture);

        ScheduleSlot oopLabSlot = new ScheduleSlot(
                DayOfWeek.WEDNESDAY,
                LocalTime.of(11, 0),
                LocalTime.of(12, 0),
                "455");

        ScheduledLesson oopLaboratory = new ScheduledLesson(
                "P_CSCI2106",
                oop,
                oopTeacher,
                LessonType.PRACTICE,
                oopLabSlot,
                null);
        scheduleMap.put(oopLaboratory.getId(), oopLaboratory);

        DataStorage.saveSchedule(scheduleMap);

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

                    String fullName = student.getF_name() + " " + student.getL_name();
                    String email = student.getEmail();
                    String phone = student.getPhoneNumber();
                    String birthday = (student.getBirthday() != null) ? student.getBirthday().toString() : "N/A";
                    String gender = (student.getGender() != null) ? student.getGender().toString() : "N/A";
                    String schoolStr = (student.getSchool() != null) ? student.getSchool().toString() : "N/A";

                    String majorStr = student.getMajor().getName().getTranslatedName();

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
                    SubjectRegistrationManager.processRegistration(student, scanner);
                }

                case "3" -> {
                    SubjectRegistrationManager.processDrop(student, scanner);
                }

                case "4" -> {
                    Printer.debugPrintInfo("-> selected: View Registered Subjects");
                    TUIConsole.clearScreen();
                    Printer.println("===========================================================================");
                    Printer.println("               === CURRENT SEMESTER REGISTERED SUBJECTS ===");
                    Printer.println("===========================================================================");

                    student.viewSubjects();

                    TUIConsole.waitForEnter();
                }

                case "5" -> {
                    Printer.debugPrintInfo("-> selected: View Transcript & Marks");
                    TUIConsole.clearScreen();
                    Printer.println("=== ACADEMIC TRANSCRIPT ===");
                    student.viewTranscript();
                    Printer.println("\n--- Current Grade Breakdown ---");
                    student.viewMarks();
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
                                        student.rateTeacher(targetTeacher, rate);

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
                    printResearchPanel(student, scanner);
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

    private void printTeacherPanel(Teacher teacher) {
        while (true) {
            TUIConsole.clearScreen();
            Printer.println("===========================================================================");
            Printer.println("                       " + LangManager.get("teacher_portal_title"));
            Printer.println("                       " + teacher.getFullName() + " (" + teacher.getLvl() + ")");
            Printer.println("===========================================================================");

            Printer.println(" " + LangManager.get("cmd_teacher_courses"));
            Printer.println(" " + LangManager.get("cmd_put_marks"));
            Printer.println(" " + LangManager.get("cmd_course_report"));
            Printer.println(" " + LangManager.get("cmd_teacher_info"));
            Printer.println(" " + LangManager.get("cmd_research_dashboard"));
            Printer.println(" " + LangManager.get("cmd_exit"));

            Printer.printAction("\n" + LangManager.get("select_option"), false);
            String choice = scanner.nextLine().trim();

            if (choice.equals("67"))
                break;

            switch (choice) {
                case "1" -> viewCoursesAndStudents(teacher, scanner);
                case "2" -> handlePutMarks(teacher, scanner);
                case "3" -> handleGenerateReport(teacher, scanner);
                case "4" -> {
                    TUIConsole.clearScreen();
                    Printer.println("=== MY PROFILE ===");
                    Printer.println(teacher.toString());
                    Printer.println("School: " + teacher.getSchool());
                    Printer.println("Active courses: " + teacher.getSubjects().size());
                    TUIConsole.waitForEnter();
                }
                case "5" -> {
                    Research.Researcher resTeacher = (Research.Researcher) teacher;

                    if (resTeacher.getResearchDELO() != null) {
                        printResearchPanel(resTeacher, scanner);
                    } else {
                        TUIConsole.clearScreen();
                        Printer.printError(LangManager.get("research_access_denied"));
                        TUIConsole.waitForEnter();
                    }
                }
                default -> {
                    Printer.printError(LangManager.get("error_input"));
                    TUIConsole.waitForEnter();
                }
            }
        }
    }

    private static Vector<Student> getAllStudents() {
        Vector<Student> students = new Vector<>();
        Map<String, Adam> allPeople = DataStorage.loadAdamdar();
        for (Adam a : allPeople.values()) {
            if (a instanceof Student) {
                students.add((Student) a);
            }
        }
        return students;
    }

    private static void viewCoursesAndStudents(Teacher teacher, Scanner scanner) {
        TUIConsole.clearScreen();
        Vector<Subject> mySubs = teacher.getSubjects();

        if (mySubs == null || mySubs.isEmpty()) {
            Printer.printWarning(LangManager.get("teacher_no_courses"));
            TUIConsole.waitForEnter();
            return;
        }

        for (int i = 0; i < mySubs.size(); i++) {
            Printer.println(
                    String.format(" [%d] -> %s (%s)", (i + 1), mySubs.get(i).getTitle(), mySubs.get(i).getId()));
        }

        Printer.printAction("\n" + LangManager.get("teacher_select_course"), false);
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice > 0 && choice <= mySubs.size()) {
                Subject selected = mySubs.get(choice - 1);
                TUIConsole.clearScreen();
                teacher.viewStudents(selected, getAllStudents());
            }
        } catch (Exception e) {
            Printer.printError(LangManager.get("error_input"));
        }
        TUIConsole.waitForEnter();
    }

    private static void handlePutMarks(Teacher teacher, Scanner scanner) {
        TUIConsole.clearScreen();
        Vector<Subject> mySubs = teacher.getSubjects();
        if (mySubs.isEmpty()) {
            Printer.printWarning(LangManager.get("teacher_no_courses"));
            TUIConsole.waitForEnter();
            return;
        }

        for (int i = 0; i < mySubs.size(); i++) {
            Printer.println(String.format(" [%d] -> %s", (i + 1), mySubs.get(i).getTitle()));
        }

        try {
            Printer.printAction("\n" + LangManager.get("teacher_select_course_action"), false);
            int courseChoice = Integer.parseInt(scanner.nextLine().trim());
            if (courseChoice <= 0 || courseChoice > mySubs.size())
                return;
            Subject selectedCourse = mySubs.get(courseChoice - 1);

            Vector<Student> currentStudents = new Vector<>();
            for (Student s : getAllStudents()) {
                if (s.getRegisteredSubjects().contains(selectedCourse)) {
                    currentStudents.add(s);
                }
            }

            if (currentStudents.isEmpty()) {
                Printer.printWarning(LangManager.get("teacher_no_students"));
                TUIConsole.waitForEnter();
                return;
            }

            TUIConsole.clearScreen();
            Printer.println("=== STUDENTS ENROLLED IN " + selectedCourse.getTitle().toUpperCase() + " ===");
            for (int i = 0; i < currentStudents.size(); i++) {
                Printer.println(String.format(" [%d] -> %s (%s)", (i + 1), currentStudents.get(i).getFullName(),
                        currentStudents.get(i).getEmail()));
            }

            Printer.printAction("\n" + LangManager.get("teacher_select_student"), false);
            int studentChoice = Integer.parseInt(scanner.nextLine().trim());
            if (studentChoice <= 0 || studentChoice > currentStudents.size())
                return;
            Student targetStudent = currentStudents.get(studentChoice - 1);

            Printer.printAction(LangManager.get("mark_enter_att1") + " ", false);
            double att1 = Double.parseDouble(scanner.nextLine().trim());

            Printer.printAction(LangManager.get("mark_enter_att2") + " ", false);
            double att2 = Double.parseDouble(scanner.nextLine().trim());

            Printer.printAction(LangManager.get("mark_enter_final") + " ", false);
            double fExam = Double.parseDouble(scanner.nextLine().trim());

            teacher.putMarks(targetStudent, selectedCourse, att1, att2, fExam);

            Map<String, Adam> database = DataStorage.loadAdamdar();
            database.put(targetStudent.getEmail(), targetStudent);
            DataStorage.saveAdamdar(database);

        } catch (Exception e) {
            Printer.printError(LangManager.get("error_input"));
        }
        TUIConsole.waitForEnter();
    }

    private static void handleGenerateReport(Teacher teacher, Scanner scanner) {
        TUIConsole.clearScreen();
        Vector<Subject> mySubs = teacher.getSubjects();
        if (mySubs.isEmpty()) {
            Printer.printWarning(LangManager.get("teacher_no_courses"));
            TUIConsole.waitForEnter();
            return;
        }

        for (int i = 0; i < mySubs.size(); i++) {
            Printer.println(String.format(" [%d] -> %s", (i + 1), mySubs.get(i).getTitle()));
        }

        Printer.printAction("\n" + LangManager.get("teacher_select_course_action"), false);
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice > 0 && choice <= mySubs.size()) {
                Subject selected = mySubs.get(choice - 1);
                TUIConsole.clearScreen();
                teacher.generateReport(selected, getAllStudents());
            }
        } catch (Exception e) {
            Printer.printError(LangManager.get("error_input"));
        }
        TUIConsole.waitForEnter();
    }

    void printResearchPanel(Research.Researcher researcher, Scanner scanner) {
        if (researcher.getResearchDELO() == null) {
            Printer.printError(LangManager.get("research_access_denied"));
            TUIConsole.waitForEnter();
            return;
        }

        Research.ResearchDELO delo = researcher.getResearchDELO();

        List<Research.RecommendationLetter> myLetters = new ArrayList<>();
        List<Research.Startup> myStartups = new ArrayList<>();

        while (true) {
            TUIConsole.clearScreen();
            Printer.println("===========================================================================");
            Printer.println("                       " + LangManager.get("research_menu_title"));
            Printer.println("===========================================================================");
            Printer.println(" " + LangManager.get("research_view_papers"));
            Printer.println(" " + LangManager.get("research_view_projects"));
            Printer.println(" " + LangManager.get("research_view_stats"));
            Printer.println(" " + LangManager.get("research_view_letters"));
            Printer.println(" " + LangManager.get("research_view_startups"));
            Printer.println(" " + LangManager.get("cmd_exit"));

            Printer.printAction("\n" + LangManager.get("select_option"), false);
            String choice = scanner.nextLine().trim();

            if (choice.equals("0"))
                break;

            switch (choice) {
                case "1" -> {
                    TUIConsole.clearScreen();
                    Printer.println(LangManager.get("research_sort_citations"));
                    Printer.println(LangManager.get("research_sort_date"));
                    Printer.println(LangManager.get("research_sort_length"));
                    Printer.printAction("\n" + LangManager.get("select_option"), false);
                    String sortChoice = scanner.nextLine().trim();
                    TUIConsole.clearScreen();
                    switch (sortChoice) {
                        case "1" -> delo.printpapers(new Research.researchcomparator.CitationSort());
                        case "2" -> delo.printpapers(new Research.researchcomparator.DateSort());
                        case "3" -> delo.printpapers(new Research.researchcomparator.lengthSort());
                        default -> delo.printpapers(new Research.researchcomparator.CitationSort());
                    }
                    TUIConsole.waitForEnter();
                }
                case "2" -> {
                    TUIConsole.clearScreen();
                    Printer.println("=== " + LangManager.get("research_project_menu") + " ===");
                    Printer.println(" [1] -> View my active projects");
                    Printer.println(" [2] -> Start a new research project");
                    Printer.printAction("\n" + LangManager.get("select_option"), false);
                    String projOpt = scanner.nextLine().trim();

                    if (projOpt.equals("1")) {
                        TUIConsole.clearScreen();
                        if (delo.getResearchProjects().isEmpty()) {
                            Printer.printWarning("No active research projects.");
                        } else {
                            for (int i = 0; i < delo.getResearchProjects().size(); i++) {
                                Research.ResearchProject p = delo.getResearchProjects().get(i);
                                Printer.println(String.format("[%d] Topic: %s | Participants: %d | Papers: %d",
                                        i + 1, p.getRPtopic(), p.getParticipants().size(),
                                        p.getPublishedPapers().size()));
                            }
                        }
                    } else if (projOpt.equals("2")) {
                        TUIConsole.clearScreen();
                        Printer.printAction(LangManager.get("research_enter_project_topic") + " ", false);
                        String topic = scanner.nextLine().trim();

                        Research.ResearchProject newProject = new Research.ResearchProject(topic);

                        try {
                            newProject.addPartiicipant(researcher);
                        } catch (Exceptions.NotResearcherException e) {
                            Printer.printError(e.getMessage());
                        }

                        delo.addResearchProject(newProject);
                        Printer.printInfo("Project created and added to your portfolio!");
                    }
                    TUIConsole.waitForEnter();
                }
                case "3" -> {
                    TUIConsole.clearScreen();
                    Printer.println("=== ACADEMIC IMPACT STATISTICS ===");
                    Printer.println(" Total Papers published: " + delo.getPapercount());
                    Printer.println(" Total Citations score: " + delo.getTotalcitations());
                    Printer.println(" Calculated h-index: " + delo.getHindex());
                    TUIConsole.waitForEnter();
                }
                case "4" -> {
                    TUIConsole.clearScreen();
                    Printer.println("=== RECOMMENDATION LETTERS ===");
                    Printer.println(" [1] -> View existing letters");
                    Printer.println(" [2] -> Generate new auto-letter");
                    Printer.printAction("\n" + LangManager.get("select_option"), false);
                    String letterOption = scanner.nextLine().trim();

                    if (letterOption.equals("1")) {
                        TUIConsole.clearScreen();
                        if (myLetters.isEmpty()) {
                            Printer.printWarning("You haven't generated any letters yet.");
                        } else {
                            for (Research.RecommendationLetter letter : myLetters) {
                                letter.printLetter();
                                Printer.println("----------------------------------------");
                            }
                        }
                    } else if (letterOption.equals("2")) {
                        TUIConsole.clearScreen();
                        Printer.printAction(LangManager.get("research_enter_candidate") + " ", false);
                        String candidate = scanner.nextLine().trim();
                        Printer.printAction(LangManager.get("research_enter_purpose") + " ", false);
                        String purpose = scanner.nextLine().trim();

                        Research.RecommendationLetter newLetter = new Research.RecommendationLetter(researcher,
                                candidate, purpose);
                        myLetters.add(newLetter);
                        TUIConsole.clearScreen();
                        newLetter.printLetter();
                        Printer.printInfo("\n" + LangManager.get("research_letter_success"));
                    }
                    TUIConsole.waitForEnter();
                }
                case "5" -> {
                    TUIConsole.clearScreen();
                    Printer.println("=== ACADEMIC STARTUPS (KBTU INCUBATOR) ===");
                    Printer.println(" [1] -> List my startups data");
                    Printer.println(" [2] -> Launch a new Startup based on papers");
                    Printer.printAction("\n" + LangManager.get("select_option"), false);
                    String startupOpt = scanner.nextLine().trim();

                    if (startupOpt.equals("1")) {
                        TUIConsole.clearScreen();
                        if (myStartups.isEmpty()) {
                            Printer.printWarning("No startups launched yet.");
                        } else {
                            for (Research.Startup s : myStartups) {
                                s.data();
                                Printer.println("----------------------------------------");
                            }
                        }
                    } else if (startupOpt.equals("2")) {
                        TUIConsole.clearScreen();
                        Printer.printAction(LangManager.get("research_enter_startup_name") + " ", false);
                        String sName = scanner.nextLine().trim();
                        Printer.printAction(LangManager.get("research_enter_startup_desc") + " ", false);
                        String sDesc = scanner.nextLine().trim();
                        Printer.printAction(LangManager.get("research_enter_startup_ind") + " ", false); // Опечатка
                                                                                                         // скобки
                                                                                                         // исправлена
                        String sInd = scanner.nextLine().trim();
                        Printer.printAction(LangManager.get("research_enter_funding") + " ", false);
                        double sFunding = Double.parseDouble(scanner.nextLine().trim());

                        Research.Startup startup = new Research.Startup(sName, sDesc, sInd);
                        startup.setFunding(sFunding);
                        startup.addFounder(researcher);

                        if (!delo.getResearchPapers().isEmpty()) {
                            startup.addPaper(delo.getResearchPapers().get(0));
                        }

                        myStartups.add(startup);
                        TUIConsole.clearScreen();
                        Printer.println(startup.toString());
                        Printer.printInfo("\nStartup launched successfully in the system!");
                    }
                    TUIConsole.waitForEnter();
                }
                default -> {
                    Printer.printError(LangManager.get("error_input"));
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
            Printer.println(" [4]-> Create Teacher");
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
}