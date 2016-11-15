package model;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Represents a single water quality report in the system
 *
 * Information Holder
 */

public class WaterQualityReport extends Report implements Serializable {

    /**
     * Properties of a water quality report.
     *
     */

    private int reportID;
    private String reporterUserName;
    private int dateDay;
    private int dateMonth;
    private int dateYear;
    private OverallCondition overallCondition;
    private String locationName;
    private double locationX;
    private double locationY;
    private double virusPPM;
    private double contaminantPPM;


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

    public OverallCondition getOverallCondition() {
        return overallCondition;
    }
    public void setOverallCondition(OverallCondition overallCondition) {
        this.overallCondition = overallCondition;
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

    public double getVirusPPM() {
        return virusPPM;
    }
    public void setVirusPPM(double x) {
        this.virusPPM = x;
    }

    public double getContaminantPPM() {
        return contaminantPPM;
    }
    public void setContaminantPPM(double y) {
        this.contaminantPPM = y;
    }

    /**
     * No arg constructor for gson compatibility.
     */
    public WaterQualityReport() {

    }

    /**
     * Create a new water quality source report.
     *
     * @param reportID  the id of this report
     * @param userName  a username of a reporter
     */
    public WaterQualityReport(int reportID, String userName) {
        Calendar calendar = Calendar.getInstance();
        dateDay = calendar.get(Calendar.DATE);
        dateMonth = calendar.get(Calendar.MONTH) + 1;
        dateYear = calendar.get(Calendar.YEAR);
        this.reportID = reportID;
        this.reporterUserName = userName;
    }

    @Override
    public String toString() {
        return "Water Quality Report: " + locationName + " (" + reportID
                + ") by " + reporterUserName
                + " on " + dateMonth + "/" + dateDay + "/" + dateYear;
    }
}
