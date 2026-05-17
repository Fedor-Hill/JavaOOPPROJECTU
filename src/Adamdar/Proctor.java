package Adamdar;

import Enums.GENDER;

import java.time.LocalDate;
import java.util.Random;

/**
 * Funny easter-egg class
 * 
 * @author YourTeam
 */
public class Proctor extends Employee {

    private int caughtCheaters;

    private int sleepingStudentsCaught;

    /**
     * Constructor
     */
    public Proctor(String f_name,
                   String l_name,
                   String email,
                   String phoneNumber,
                   LocalDate birthday,
                   GENDER gender,
                   String password) {

        super(
                f_name,
                l_name,
                email,
                phoneNumber,
                birthday,
                gender,
                password,
                "Proctor"
        );

        this.caughtCheaters = 0;
        this.sleepingStudentsCaught = 0;
    }

    // ===== EXAM ACTIVITIES =====

    /**
     * Catch cheating student
     */
    public void catchCheater(Student student) {

        caughtCheaters++;

        System.out.println(
                "🚨 "
                + student.getFullName()
                + " was caught cheating!"
        );
    }

    /**
     * Wake sleeping student
     */
    public void wakeUpStudent(Student student) {

        sleepingStudentsCaught++;

        System.out.println(
                "😴 "
                + student.getFullName()
                + " fell asleep during exam."
        );

        System.out.println(
                "Proctor woke them up."
        );
    }

    /**
     * Patrol exam room
     */
    public void patrolExamRoom() {

        String[] actions = {
                "checking cheat sheets...",
                "watching suspicious students...",
                "dramatically walking between desks...",
                "staring into souls of students..."
        };

        Random random = new Random();

        System.out.println(
                "👀 Proctor is "
                + actions[random.nextInt(actions.length)]
        );
    }

    /**
     * Confiscate cheat sheet
     */
    public void confiscateCheatSheet(Student student) {

        System.out.println(
                "📄 Cheat sheet confiscated from "
                + student.getFullName()
        );
    }

    // ===== GETTERS =====

    public int getCaughtCheaters() {
        return caughtCheaters;
    }

    public int getSleepingStudentsCaught() {
        return sleepingStudentsCaught;
    }

    // ===== OBJECT METHODS =====

    @Override
    public String toString() {

        return "Proctor "
                + getFullName()
                + " | Cheaters caught: "
                + caughtCheaters;
    }
}