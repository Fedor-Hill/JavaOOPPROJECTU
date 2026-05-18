package AcademicThigns;

/**
 * Compatibility name for the assignment: in the original project courses were
 * named Subject.
 */
public class Course extends Subject {
    private static final long serialVersionUID = 1L;

    public Course() {
        super();
    }

    public Course(String id, String title, int credits, LessonType lessonType) {
        super(id, title, credits, lessonType);
    }
}
