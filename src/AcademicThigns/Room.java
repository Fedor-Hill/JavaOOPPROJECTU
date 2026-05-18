package AcademicThigns;

import Enums.RoomType;

import java.io.Serializable;
import java.util.Objects;

public class Room implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String number;
    private final RoomType type;
    private final int capacity;

    public Room(String number, RoomType type, int capacity) {
        if (number == null || number.trim().isEmpty()) {
            throw new IllegalArgumentException("Room number is required");
        }
        if (type == null) {
            throw new IllegalArgumentException("Room type is required");
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException("Room capacity must be positive");
        }
        this.number = number;
        this.type = type;
        this.capacity = capacity;
    }

    public String getNumber() {
        return number;
    }

    public RoomType getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return number + " (" + type + ", capacity=" + capacity + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Room)) {
            return false;
        }
        Room other = (Room) obj;
        return Objects.equals(number, other.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
