package AcademicThigns;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Azimbay Zhanel
 */
public class Enrollment implements Serializable {
    private static final long serialVersionUID = 1L;

    private Subject takedSubject;
    private Attestation attestation;
    private String semester;
    private int academicYear;

    public Enrollment() {
        this(null, new Attestation(), "", 0);
    }

    public Enrollment(Subject takedSubject, Attestation attestation, String semester, int academicYear) {
        this.takedSubject = takedSubject;
        this.attestation = attestation;
        this.semester = semester;
        this.academicYear = academicYear;
    }

    public Subject getTakedSubject() {
        return takedSubject;
    }

    public void setTakedSubject(Subject takedSubject) {
        this.takedSubject = takedSubject;
    }

    public Attestation getAttestation() {
        return attestation;
    }

    public void setAttestation(Attestation attestation) {
        this.attestation = attestation;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(int academicYear) {
        this.academicYear = academicYear;
    }

    public boolean isPassed() {
        return attestation != null && attestation.isPassed();
    }

    @Override
    public String toString() {
        return "Enrollment{subject=" + takedSubject
                + ", semester='" + semester + '\''
                + ", year=" + academicYear
                + ", attestation=" + attestation + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Enrollment)) {
            return false;
        }
        Enrollment other = (Enrollment) obj;
        return academicYear == other.academicYear
                && Objects.equals(semester, other.semester)
                && Objects.equals(takedSubject, other.takedSubject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(takedSubject, semester, academicYear);
    }
}
