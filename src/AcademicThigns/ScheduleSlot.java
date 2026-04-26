package AcademicThigns;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;

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

    @Override
    public String toString() {
        return dayOfWeek + " " + startTime + "-" + endTime + " room " + room;
    }
}
