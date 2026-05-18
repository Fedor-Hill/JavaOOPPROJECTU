package AcademicThigns;

import java.io.Serializable;
import java.util.Objects;

import Enums.MAJOR;

/**
 * @author Azimbay Zhanel
 */
public class Major implements Serializable {
    private static final long serialVersionUID = 1L;

    private MAJOR name;
    private String title;
    private double yearOfStudy;

    public Major() {
        this(MAJOR.VTiPO, "", 1);
    }

    public Major(MAJOR name, String title, double yearOfStudy) {
        this.name = name; 
        this.title = title;
        this.yearOfStudy = yearOfStudy;
    }

    public MAJOR getName() {
        return name;
    }

    public void setName(MAJOR name) {
        this.name = name;
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
        return title + " (" + name.getTranslatedName() + "), year " + yearOfStudy;
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
        return Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
