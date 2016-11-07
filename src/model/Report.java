package model;

import java.io.Serializable;

/**
 * Abstract class to represent a report in the system
 *
 * Information Holder
 */
public abstract class Report implements Serializable {

    /**
     * Getters and setters for all the properties.
     *
     */

    abstract int getReportID();

    abstract String getReporterUserName();

    abstract int getDateDay();

    abstract int getDateMonth();

    abstract int getDateYear();

    abstract String getLocationName();
    abstract void setLocationName(String locationName);

    abstract double getLocationX();
    abstract void setLocationX(double x);

    abstract double getLocationY();
    abstract void setLocationY(double y);
}
