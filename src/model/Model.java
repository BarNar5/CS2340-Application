package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.Serializable;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class serves as a Facade into the application model
 * Right now it is a Singleton so the controllers can get access easily.
 *
 */
public class Model implements Serializable {

    /** Set Model up as a singleton design pattern */
    private static Model instance = new Model();
    /** Get an instance of this singleton
     * @return this singleton
     */
    public static Model getInstance() {
        return instance;
    }

    /** a list of all the users */
    private final List<User> users = new ArrayList<>();

    /** a list of all the reports */
    private final List<Report> reports = new ArrayList<>();

    /** a list of all the water locations */
    private final List<Location> locations = new ArrayList<>();

    /** a user currently logged in */
    private User loggedUser;

    /** a count for water report id*/
    private Integer waterReportCounter = 10000;


    /**
     * Make a new Model.
     * (Fill it with some users for testing reasons)
     */
    private Model() {
        /*
        waterReportCounter = 10000;
        users.add(new User("user1", "u", AccountType.USER));
        users.add(new User("user2", "u", AccountType.USER));
        users.add(new User("worker1", "w", AccountType.WORKER));
        users.add(new User("worker2", "w", AccountType.WORKER));
        users.add(new User("manager1", "m", AccountType.MANAGER));
        users.add(new User("manager2", "m", AccountType.MANAGER));
        User admin = new User("admin", "admin", AccountType.ADMIN);
        admin.setName("Admin Admin");
        admin.setGender(Gender.Male);
        admin.setDateDay("01");
        admin.setDateMonth("01");
        admin.setDateYear("1969");
        admin.setPhoneNumber("7777777777");
        admin.setEmail("admin@admin.com");
        admin.setAddress1("Admin Bld 123");
        admin.setAddress2("Georgia Tech Dr 69");
        admin.setAddress3("Atlanta, GA 33333");
        users.add(admin);
        //Location l = new Location(1, 33.7756, -84.3963,
            "Test Marker", "<h2>Test </h2>  \n some data");
        //locations.add(l);
        */
    }

    /**
     * Get a currently logged user.
     *
     * @return currently logged user
     */
    public User getLoggedUser() {
        return loggedUser;
    }

    /**
     * Set a currently logged user.
     *
     * @param user a user to set as a logged one
     */
    public void setLoggedUser(User user) {
        loggedUser = user;
    }

    /**
     * Get a next ID for a water report
     *
     * @return the ID for a new water report
     */
    public int getWaterReportCounter() {
        return waterReportCounter++;
    }

    /**
     * Get a list of all registered users.
     *
     * @return a list of users
     */
    public ObservableList<User> getUsers() {
        return FXCollections.observableArrayList(users);
    }

    /**
     * Get a list of all locations.
     *
     * @return a list of locations
     */
    public List<Location> getLocations() {
        return locations;
    }


    /**
     * add a new user to a list of registered users.
     *
     * @param user a new user to add
     * @return true if the user was added, false if not added
     */
    public boolean addUser(User user) {
        return users.add(user);
    }

    /**
     * Get a list of all quality reports.
     *
     * @return a list of users
     */
    public ObservableList<WaterQualityReport> getQualityReports() {
        ObservableList<WaterQualityReport> list =
            FXCollections.observableArrayList();
        list.addAll(reports.stream().filter(report -> report
            instanceof WaterQualityReport).map(report ->
            (WaterQualityReport) report).collect(Collectors.toList()));
        return list;
    }

    /**
     * Get a list of all quality reports from a given year.
     *
     * @param year a year to get reports from
     * @return a list of users
     */
    public List<WaterQualityReport> getQualityReportsByYear(int year) {
        return reports.stream().filter(report -> report
            instanceof WaterQualityReport && report.getDateYear() == year).
            map(report -> (WaterQualityReport) report).
            collect(Collectors.toList());
    }

    /**
     * Save the data from the model to the file.
     *
     * @param file file to save the data to
     */
    public void saveToJson(File file) {
        String str;
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Report.class, new ReportAdapter());
            Gson gson = gsonBuilder.create();

            str = gson.toJson(instance);
            pw.println(str);

        } catch (IOException e) {
            System.out.println("Exception working with Json Save File");
        }
    }

    /**
     * Load the data from the file to the model.
     *
     * @param file file to load the data from
     */
    public void loadFromJson(File file) {
        String ct;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Report.class, new ReportAdapter());
            Gson gson = gsonBuilder.create();

            Type modelType = new TypeToken<Model>() { }.getType();

            ct = br.readLine();
            instance = gson.fromJson(ct, modelType);

            br.close();
        } catch (IOException ex) {
            System.out.println("Exception working with Json Load File");
        }
    }

    /**
     * add a new location to the model.
     *
     * @param location location to add
     * @return true if location was successfully added
     */
    public boolean addLocation(Location location) {
    	if (location == null) {
    		return false;
    	}
        return locations.add(location);
    }

    /**
     * add a new report to the model.
     *
     * @param report report to add
     * @return true if report was successfully added
     */
    public boolean addReport(Report report) {
        return reports.add(report);
    }
}
