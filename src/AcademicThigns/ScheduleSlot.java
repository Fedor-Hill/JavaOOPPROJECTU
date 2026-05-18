package AcademicThigns;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

/**
 * @author Azimbay Zhanel
 */
public class ScheduleSlot implements Serializable {
    private static final long serialVersionUID = 1L;

    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private String room;

    public ScheduleSlot(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime, String room) {
        if (dayOfWeek == null) {
            throw new IllegalArgumentException("Day of week is required");
        }
        if (startTime == null || endTime == null || !startTime.isBefore(endTime)) {
            throw new IllegalArgumentException("Start time must be before end time");
        }
        if (room == null || room.trim().isEmpty()) {
            throw new IllegalArgumentException("Room is required");
        }
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getRoom() {
        return room;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        if (dayOfWeek == null) {
            throw new IllegalArgumentException("Day of week is required");
        }
        this.dayOfWeek = dayOfWeek;
    }

    public void setStartTime(LocalTime startTime) {
        if (startTime == null || !startTime.isBefore(endTime)) {
            throw new IllegalArgumentException("Start time must be before end time");
        }
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        if (endTime == null || !startTime.isBefore(endTime)) {
            throw new IllegalArgumentException("Start time must be before end time");
        }
        this.endTime = endTime;
    }

    public void setRoom(String room) {
        if (room == null || room.trim().isEmpty()) {
            throw new IllegalArgumentException("Room is required");
        }
        this.room = room;
    }

    public boolean overlaps(ScheduleSlot other) {
        if (other == null || dayOfWeek != other.dayOfWeek) {
            return false;
        }
        return startTime.isBefore(other.endTime) && endTime.isAfter(other.startTime);
    }

    @Override
    public String toString() {
        return dayOfWeek + " " + startTime + "-" + endTime + " room " + room;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ScheduleSlot)) {
            return false;
        }
        ScheduleSlot other = (ScheduleSlot) obj;
        return dayOfWeek == other.dayOfWeek
                && Objects.equals(startTime, other.startTime)
                && Objects.equals(endTime, other.endTime)
                && Objects.equals(room, other.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayOfWeek, startTime, endTime, room);
    }
}
