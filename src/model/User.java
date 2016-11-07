package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single user in the system
 *
 * Information Holder
 */

public class User implements Serializable {

    /**
     * Properties of a user.
     *
     */

    private String userName;
    private String password;
    private AccountType accountType;
    private String name;
    private Gender gender;
    private String dateDay;
    private String dateMonth;
    private String dateYear;
    private String phoneNumber;
    private String email;
    private String address1;
    private String address2;
    private String address3;
    //private ObservableList<Report> waterReports;
    private List<Report> waterReports;


    /**
     * Getters and setters for all the properties.
     *
     */

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getDateDay() {
        return dateDay;
    }
    public void setDateDay(String dateDay) {
        this.dateDay = dateDay;
    }

    public String getDateMonth() {
        return dateMonth;
    }
    public void setDateMonth(String dateMonth) {
        this.dateMonth = dateMonth;
    }

    public String getDateYear() {
        return dateYear;
    }
    public void setDateYear(String dateYear) {
        this.dateYear = dateYear;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress1() {
        return address1;
    }
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }
    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public AccountType getAccountType() {
        return accountType;
    }
    public void setAccountType(AccountType type) {
        this.accountType = type;
    }

    public ObservableList<Report> getWaterReports() {
        return FXCollections.observableArrayList(waterReports);
    }
    /**
     * Create a new user.
     *
     * @param userName  a name of a new user
     * @param password  a password of a new user
     * @param type      a type of a new user
     */
    public User(String userName, String password, AccountType type) {
        this.userName = userName;
        this.password = password;
        this.accountType = type;
        waterReports = new ArrayList<>();
    }

    /**
     * Create a new user.
     *
     * @param userName  a name of a new user
     * @param password  a password of a new user
     */
    public User(String userName, String password) {
        this(userName, password, AccountType.USER);
    }

    /**
     * No arg constructor for JSON compatibility
     */
    public User() {
    }

    /**
     * compares if this user is the same as a passed object
     *
     * @param that Object to compare to
     * @return true if two users are equal
     */
    public boolean equals(Object that) {
        if (that == null) {
            return false;
        }
        if (that == this) {
            return true;
        }
        if (!(that instanceof User)) {
            return false;
        }
        User user = (User) that;
        return userName.equals(user.getUserName()) && password.equals(user.getPassword());
    }

    /**
     * compares if this user has the same name as a passed object
     *
     * @param that Object to compare to
     * @return true if two users have the same name
     */
    public boolean equalName(Object that) {
        if (that == null) {
            return false;
        }
        if (that == this) {
            return true;
        }
        if (!(that instanceof User)) {
            return false;
        }
        User user = (User) that;
        return userName.equals(user.getUserName());
    }

    /**
     * adds a new water source report
     *
     * @param reportID new report's ID number
     * @param waterType new report's water type
     * @param waterCondition new report's water condition
     * @param locationName new report's location name
     * @param locationX new report's NS location
     * @param locationY new report's EW location
     */
    public void addWaterReport(int reportID,
                               WaterType waterType,
                               WaterCondition waterCondition,
                               String locationName,
                               double locationX,
                               double locationY) {
        WaterSourceReport waterReport = new WaterSourceReport(reportID,
                userName);
        waterReport.setWaterType(waterType);
        waterReport.setWaterCondition(waterCondition);
        waterReport.setLocationName(locationName);
        waterReport.setLocationX(locationX);
        waterReport.setLocationY(locationY);

        waterReports.add(waterReport);
        Model.getInstance().addReport(waterReport);

        String str = "<h2>" + locationName + "</h2>"
                + waterType + "<br>" + waterCondition;
        Location location = new Location(reportID, locationX, locationY, locationName, str);
        Model.getInstance().addLocation(location);
    }

    /**
     * adds a new water source report
     *
     * @param reportID new report's ID number
     * @param overallCondition new report's water condition
     * @param locationName new report's location name
     * @param locationX new report's NS location
     * @param locationY new report's EW location
     */
    public void addQualityReport(int reportID,
                                 OverallCondition overallCondition,
                                 String locationName,
                                 double locationX,
                                 double locationY,
                                 double virusPPM,
                                 double contaminantPPM) {
        WaterQualityReport waterReport = new WaterQualityReport(reportID,
                userName);
        waterReport.setOverallCondition(overallCondition);
        waterReport.setVirusPPM(virusPPM);
        waterReport.setContaminantPPM(contaminantPPM);
        waterReport.setLocationName(locationName);
        waterReport.setLocationX(locationX);
        waterReport.setLocationY(locationY);

        waterReports.add(waterReport);
        Model.getInstance().addReport(waterReport);

        String str = "<h2>" + locationName + "</h2>"
                + overallCondition + "<br>Virus PPM: " + virusPPM
                + "<br>Contaminant PPM: " + contaminantPPM;
        Location location = new Location(reportID, locationX, locationY, locationName, str);
        Model.getInstance().addLocation(location);
    }

    /**
     *
     * @return a string representation of a user
     */
    public String toString() {
        return userName + " " + password + " " + accountType;
    }

}