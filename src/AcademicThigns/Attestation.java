package AcademicThigns;

import Enums.STATUS;

import java.io.Serializable;

/**
 * @author Azimbay Zhanel
 */
public class Attestation implements Serializable {
    private static final long serialVersionUID = 1L;

    private double first;
    private double second;
    private double finalExam;
    private double digitGrade;
    private String letterGrade;
    private String traditionalGrade;
    private STATUS status;

    public Attestation() {
        this(0, 0, 0);
    }

    public Attestation(double first, double second, double finalExam) {
        this.first = first;
        this.second = second;
        this.finalExam = finalExam;
        recalculate();
    }

    public double getFirst() {
        return first;
    }

    public void setFirst(double first) {
        this.first = first;
        recalculate();
    }

    public double getSecond() {
        return second;
    }

    public void setSecond(double second) {
        this.second = second;
        recalculate();
    }

    public double getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(double finalExam) {
        this.finalExam = finalExam;
        recalculate();
    }

    public double getDigitGrade() {
        return digitGrade;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public String getTraditionalGrade() {
        return traditionalGrade;
    }

    public STATUS getStatus() {
        return status;
    }

    public boolean isPassed() {
        return digitGrade >= 50;
    }

    public void recalculate() {
        this.digitGrade = this.normalize(this.first, (double) 30.0F) + this.normalize(this.second, (double) 30.0F)
                + this.normalize(this.finalExam, (double) 40.0F);
        letterGrade = calculateLetterGrade(digitGrade);
        traditionalGrade = isPassed() ? "Passed" : "Not passed";

        if (digitGrade == 0) {
            status = STATUS.ACTIVE;
        } else if (isPassed()) {
            status = STATUS.PASS;
        } else {
            status = STATUS.RUNNING;
        }
    }

    private double normalize(double var1, double var3) {
      return Math.max((double)0.0F, Math.min(var3, var1));
   }

    private String calculateLetterGrade(double grade) {
        if (grade >= 95)
            return "A";
        if (grade >= 90)
            return "A-";
        if (grade >= 85)
            return "B+";
        if (grade >= 80)
            return "B";
        if (grade >= 75)
            return "B-";
        if (grade >= 70)
            return "C+";
        if (grade >= 65)
            return "C";
        if (grade >= 60)
            return "C-";
        if (grade >= 55)
            return "D+";
        if (grade >= 50)
            return "D";
        return "F";
    }

    @Override
    public String toString() {
        return "first=" + first
                + ", second=" + second
                + ", final=" + finalExam
                + ", total=" + digitGrade
                + ", letter='" + letterGrade + '\''
                + ", status=" + status;
    }
}
