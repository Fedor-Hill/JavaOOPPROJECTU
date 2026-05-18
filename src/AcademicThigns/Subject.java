package AcademicThigns;

import Adamdar.Teacher;
import Enums.MAJOR;

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
    private List<MAJOR> acceptableMajors;
    private List<String> prerequisite;
    private LessonType lessonType;
    private List<ScheduleSlot> schedule;
    private List<Lesson> lessons;
    private int intendedYear;

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
        this.lessons = new ArrayList<>();
        this.intendedYear = 0;
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

    public List<MAJOR> getAcceptableMajors() {
        return acceptableMajors;
    }

    public void setAcceptableMajors(List<MAJOR> acceptableMajors) {
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

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons == null ? new ArrayList<>() : new ArrayList<>(lessons);
    }

    public int getIntendedYear() {
        return intendedYear;
    }

    public void setIntendedYear(int intendedYear) {
        this.intendedYear = intendedYear;
    }

    public void addTeacher(Teacher teacher) {
        if (teacher != null && !teachers.contains(teacher)) {
            teachers.add(teacher);
        }
    }

    public void addAcceptableMajor(MAJOR majorTitle) {
        this.acceptableMajors = acceptableMajors == null ? new ArrayList<>() : new ArrayList<>(acceptableMajors);
    }

    public void addPrerequisite(String prerequisiteSubjectId) {
        if (prerequisiteSubjectId != null && !prerequisiteSubjectId.trim().isEmpty()
                && !prerequisite.contains(prerequisiteSubjectId)) {
            prerequisite.add(prerequisiteSubjectId);
        }
    }

    public void addScheduleSlot(ScheduleSlot slot) {
        if (slot != null) {
            schedule.add(slot);
        }
    }

    public void addLesson(Lesson lesson) {
        if (lesson != null) {
            lessons.add(lesson);
        }
    }

    public boolean isAvailableForMajor(Major major) {
        if (acceptableMajors == null || acceptableMajors.isEmpty()) {
            return true;
        }
        if (major == null) {
            return false;
        }
        
        Enums.MAJOR studentMajorEnum = major.getName(); 
        
        return acceptableMajors.contains(studentMajorEnum);
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
