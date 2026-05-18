package Application;

import java.util.*;
import AcademicThigns.Subject;
import Adamdar.Adam;
import Adamdar.Student;

/**
 * For Controlling Subject Registration
 */
public class SubjectRegistrationManager {

    /**
     * Adds subject
     */
    public static void processRegistration(Student student, Scanner scanner) {
        Printer.debugPrintInfo("-> selected: Register for Subject");
        TUIConsole.clearScreen();
        Printer.println("===========================================================================");
        Printer.println("                       " + LangManager.get("subject_registration_title"));
        Printer.println("===========================================================================");

        Map<String, Subject> allSubjectsMap = DataStorage.loadSubjects();
        
        List<Subject> availableForMe = new ArrayList<>();
        for (Subject sub : allSubjectsMap.values()) {
            if (sub.isAvailableForMajor(student.getMajor())) {
                availableForMe.add(sub);
            }
        }

        if (availableForMe.isEmpty()) {
            Printer.printWarning(LangManager.get("subject_no_available"));
            TUIConsole.waitForEnter();
            return;
        }

        for (int i = 0; i < availableForMe.size(); i++) {
            Subject s = availableForMe.get(i);
            
            String status = student.getRegisteredSubjects().contains(s) ? "[ALREADY REGISTERED]" : "";
            
            Printer.println(String.format(" [%d] -> %s (%s) | Credits: %d %s", 
                    (i + 1), s.getTitle(), s.getId(), s.getCredits(), status));
        }

        Printer.printAction("\n" + LangManager.get("subject_enter_number"), false);
        String input = scanner.nextLine().trim();

        if (input.equals("0")) return;

        try {
            int choice = Integer.parseInt(input);
            if (choice >= 1 && choice <= availableForMe.size()) {
                Subject selectedSubject = availableForMe.get(choice - 1);
                
                student.registerSubject(selectedSubject);
                
                Map<String, Adam> ada = DataStorage.loadAdamdar();
                ada.put(student.getEmail(), student);
                DataStorage.saveAdamdar(ada);
                
            } else {
                Printer.printError(LangManager.get("error_input"));
            }
        } catch (NumberFormatException e) {
            Printer.printError(LangManager.get("error_input"));
        }
        TUIConsole.waitForEnter();
    }

    /**
     * Drop Subject
     */
    public static void processDrop(Student student, Scanner scanner) {
        Printer.debugPrintInfo("-> selected: Drop Subject");
        TUIConsole.clearScreen();
        Printer.println("===========================================================================");
        Printer.println("                       " + LangManager.get("subject_drop_title"));
        Printer.println("===========================================================================");

        Vector<Subject> mySubs = student.getRegisteredSubjects();

        if (mySubs == null || mySubs.isEmpty()) {
            Printer.printWarning(LangManager.get("subject_no_registered"));
            TUIConsole.waitForEnter();
            return;
        }

        for (int i = 0; i < mySubs.size(); i++) {
            Printer.println(String.format(" [%d] -> %s (%s) | Credits: %d", 
                    (i + 1), mySubs.get(i).getTitle(), mySubs.get(i).getId(), mySubs.get(i).getCredits()));
        }

        Printer.printAction("\n" + LangManager.get("subject_enter_drop"), false);
        String input = scanner.nextLine().trim();

        if (input.equals("0")) return;

        try {
            int choice = Integer.parseInt(input);
            if (choice >= 1 && choice <= mySubs.size()) {
                Subject toDrop = mySubs.get(choice - 1);
                
                student.dropSubject(toDrop);
                
                Map<String, Adam> ada = DataStorage.loadAdamdar();
                ada.put(student.getEmail(), student);
                DataStorage.saveAdamdar(ada);
                
            } else {
                Printer.printError(LangManager.get("error_input"));
            }
        } catch (NumberFormatException e) {
            Printer.printError(LangManager.get("error_input"));
        }
        TUIConsole.waitForEnter();
    }
}