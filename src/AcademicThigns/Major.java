package AcademicThigns;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Azimbay Zhanel
 */
public class Major implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private double yearOfStudy;

    public Major() {
        this("", "", 1);
    }

    public Major(String id, String title, double yearOfStudy) {
        this.id = id;
        this.title = title;
        this.yearOfStudy = yearOfStudy;
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

    public double getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(double yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    @Override
    public String toString() {
        return title + " (" + id + "), year " + yearOfStudy;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Major)) {
            return false;
        }
        Major other = (Major) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
