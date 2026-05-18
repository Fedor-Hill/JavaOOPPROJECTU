package AcademicThigns;

import Adamdar.Teacher;

import java.io.Serializable;
import java.util.Objects;

public class ScheduledLesson implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String id;
    private Subject course;
    private Teacher teacher;
    private LessonType lessonType;
    private ScheduleSlot slot;
    private Room room;

    public ScheduledLesson(String id, Subject course, Teacher teacher, LessonType lessonType,
            ScheduleSlot slot, Room room) {
        this.id = id;
        this.course = course;
        this.teacher = teacher;
        this.lessonType = lessonType;
        this.slot = slot;
        this.room = room;
    }

    public String getId() {
        return id;
    }

    public Subject getCourse() {
        return course;
    }

    public void setCourse(Subject course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public LessonType getLessonType() {
        return lessonType;
    }

    public void setLessonType(LessonType lessonType) {
        this.lessonType = lessonType;
    }

    public ScheduleSlot getSlot() {
        return slot;
    }

    public void setSlot(ScheduleSlot slot) {
        this.slot = slot;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public boolean conflictsWith(ScheduledLesson other) {
        if (other == null || slot == null || other.slot == null || !slot.overlaps(other.slot)) {
            return false;
        }
        boolean sameRoom = room != null && other.room != null && room.equals(other.room);
        boolean sameTeacher = teacher != null && teacher.equals(other.teacher);
        boolean sameCourse = course != null && course.equals(other.course);
        return sameRoom || sameTeacher || sameCourse;
    }

    @Override
    public String toString() {
        return id + ": " + course + " " + lessonType + " / " + teacher + " / " + slot;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ScheduledLesson)) {
            return false;
        }
        ScheduledLesson other = (ScheduledLesson) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
