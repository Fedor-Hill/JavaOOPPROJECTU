package AcademicThigns;

import Adamdar.Teacher;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Azimbay Zhanel
 */
public class Subject implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private List<Teacher> teachers;
    private int credits;
    private List<String> acceptableMajors;
    private List<String> prerequisite;
    private LessonType lessonType;
    private List<ScheduleSlot> schedule;

    public Subject() {
        this("", "", 0, LessonType.LECTURE);
    }

    public Subject(String id, String title, int credits, LessonType lessonType) {
        this.id = id;
        this.title = title;
        this.credits = credits;
        this.lessonType = lessonType;
        this.teachers = new ArrayList<>();
        this.acceptableMajors = new ArrayList<>();
        this.prerequisite = new ArrayList<>();
        this.schedule = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers == null ? new ArrayList<>() : new ArrayList<>(teachers);
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public List<String> getAcceptableMajors() {
        return acceptableMajors;
    }

    public void setAcceptableMajors(List<String> acceptableMajors) {
        this.acceptableMajors = acceptableMajors == null ? new ArrayList<>() : new ArrayList<>(acceptableMajors);
    }

    public List<String> getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(List<String> prerequisite) {
        this.prerequisite = prerequisite == null ? new ArrayList<>() : new ArrayList<>(prerequisite);
    }

    public LessonType getLessonType() {
        return lessonType;
    }

    public void setLessonType(LessonType lessonType) {
        this.lessonType = lessonType;
    }

    public List<ScheduleSlot> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<ScheduleSlot> schedule) {
        this.schedule = schedule == null ? new ArrayList<>() : new ArrayList<>(schedule);
    }

    public void addTeacher(Teacher teacher) {
        if (teacher != null && !teachers.contains(teacher)) {
            teachers.add(teacher);
        }
    }

    public void addAcceptableMajor(String majorTitle) {
        if (majorTitle != null && !majorTitle.isBlank() && !acceptableMajors.contains(majorTitle)) {
            acceptableMajors.add(majorTitle);
        }
    }

    public void addPrerequisite(String prerequisiteSubjectId) {
        if (prerequisiteSubjectId != null && !prerequisiteSubjectId.isBlank() && !prerequisite.contains(prerequisiteSubjectId)) {
            prerequisite.add(prerequisiteSubjectId);
        }
    }

    public void addScheduleSlot(ScheduleSlot slot) {
        if (slot != null) {
            schedule.add(slot);
        }
    }

    public boolean isAvailableForMajor(Major major) {
        if (major == null || acceptableMajors.isEmpty()) {
            return true;
        }
        return acceptableMajors.contains(major.getTitle()) || acceptableMajors.contains(major.getId());
    }

    @Override
    public String toString() {
        return id + " - " + title
                + " [" + lessonType + ", credits=" + credits + ", schedule=" + schedule + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Subject)) {
            return false;
        }
        Subject other = (Subject) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
