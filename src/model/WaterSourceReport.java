package model;

import java.util.Calendar;

/**
 * Represents a single water source report in the system
 *
 * Information Holder
 */

public class WaterSourceReport {

    /**
     * Properties of a water source report.
     *
     */

    private int reportID;
    private String reporterUserName;
    private int dateDay;
    private int dateMonth;
    private int dateYear;
    private WaterType waterType;
    private WaterCondition waterCondition;
    private String locationName;
    private double locationX;
    private double locationY;


    /**
     * Getters and setters for all the properties.
     *
     */

    public int getReportID() {
        return reportID;
    }

    public String getReporterUserName() {
        return reporterUserName;
    }

    public int getDateDay() {
        return dateDay;
    }

    public int getDateMonth() {
        return dateMonth;
    }

    public int getDateYear() {
        return dateYear;
    }

    public WaterType getWaterType() {
        return waterType;
    }
    public void setWaterType(WaterType waterType) {
        this.waterType = waterType;
    }

    public WaterCondition getWaterCondition() {
        return waterCondition;
    }
    public void setWaterCondition(WaterCondition waterCondition) {
        this.waterCondition = waterCondition;
    }

    public String getLocationName() {
        return locationName;
    }
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public double getLocationX() {
        return locationX;
    }
    public void setLocationX(double x) {
        this.locationX = x;
    }

    public double getLocationY() {
        return locationY;
    }
    public void setLocationY(double y) {
        this.locationY = y;
    }

    /**
     * Create a new water source report.
     *
     * @param reportID  the id of this report
     * @param userName  a username of a reporter
     */
    public WaterSourceReport(int reportID, String userName) {
        Calendar calendar = Calendar.getInstance();
        dateDay = calendar.get(Calendar.DATE);
        dateMonth = calendar.get(Calendar.MONTH) + 1;
        dateYear = calendar.get(Calendar.YEAR);
        this.reportID = reportID;
        this.reporterUserName = userName;
    }

    @Override
    public String toString() {
        return locationName + " (" + reportID + ") on " + dateMonth + "/"
                + dateDay + "/" + dateYear;
    }
}
