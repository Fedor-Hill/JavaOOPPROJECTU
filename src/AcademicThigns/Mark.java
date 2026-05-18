package AcademicThigns;

/**
 * Mark consists of first attestation, second attestation and final exam.
 */
public class Mark extends Attestation {
    private static final long serialVersionUID = 1L;

    public Mark() {
        super();
    }

    public Mark(double firstAttestation, double secondAttestation, double finalExam) {
        super(firstAttestation, secondAttestation, finalExam);
    }
}
