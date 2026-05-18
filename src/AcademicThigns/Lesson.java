package AcademicThigns;

import Adamdar.Teacher;

import java.io.Serializable;
import java.util.Objects;

public class Lesson implements Serializable {
    private static final long serialVersionUID = 1L;

    private final LessonType type;
    private final ScheduleSlot slot;
    private final Teacher instructor;
    private final String room;

    public Lesson(LessonType type, ScheduleSlot slot, Teacher instructor, String room) {
        this.type = Objects.requireNonNull(type, "type");
        this.slot = slot;
        this.instructor = instructor;
        this.room = room;
    }

    public LessonType getType() {
        return type;
    }

    public ScheduleSlot getSlot() {
        return slot;
    }

    public Teacher getInstructor() {
        return instructor;
    }

    public String getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return type + " at " + slot + " in " + room;
    }
}
