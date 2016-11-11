package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * This class serves as a Facade into the application model
 * Right now it is a Singleton so the controllers can get access easily.
 *
 */

public class Model implements Serializable {

    /** Set Model up as a singleton design pattern */
    private static Model instance = new Model();
    public static Model getInstance() {
        return instance;
    }

    /** a list of all the users */
    private List<User> users = new ArrayList<>();

    /** a list of all the reports */
    private List<Report> reports = new ArrayList<>();

    /** a list of all the water locations */
    private List<Location> locations = new ArrayList<>();

    /** a user currently logged in */
    private User loggedUser;

    private Integer waterReportCounter = 10000;


    /**
     * Make a new Model.
     * (Fill it with some users for testing reasons)
     */
    private Model () {
        /*
        waterReportCounter = 10000;
        users.add(new User("user1", "u", AccountType.USER));
        users.add(new User("user2", "u", AccountType.USER));
        users.add(new User("worker1", "w", AccountType.WORKER));
        users.add(new User("worker2", "w", AccountType.WORKER));
        users.add(new User("manager1", "m", AccountType.MANAGER));
        users.add(new User("manager2", "m", AccountType.MANAGER));
        User admin = new User("admin", "admin", AccountType.ADMIN);
        admin.setName("Admin Adminowicz");
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
        //Location l = new Location(1, 33.7756, -84.3963, "Test Marker", "<h2>Test </h2>  \nsome data");
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
     * @return a list of users
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
        ObservableList<WaterQualityReport> list = FXCollections.observableArrayList();
        //System.out.println("WOWOWOWOWOW " + reports.get(0));
        for (Report report : reports) {
            if (report instanceof WaterQualityReport) {
                list.add((WaterQualityReport) report);
            }
        }
        return list;
    }

    /**
     * Get a list of all quality reports from a given year.
     *
     * @param year a year to get reports from
     * @return a list of users
     */
    public List<WaterQualityReport> getQualityReportsByYear(int year) {
        List<WaterQualityReport> yearReports = new ArrayList<>();
        for (Report report : reports) {
            if (report instanceof WaterQualityReport && report.getDateYear() == year) {
                yearReports.add((WaterQualityReport) report);
            }
        }
        return yearReports;
    }

    /**
     * Save the data from the model to the file.
     *
     * @param file file to save the data to
     */
    public void saveToJson(File file) {
        String str;
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
//            Gson gson = new Gson();
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Report.class, new ReportAdapter());
            Gson gson = gsonBuilder.create();

            /*
            str = gson.toJson(users);
            pw.println(str);
            str = gson.toJson(reports);
            pw.println(str);
            str = gson.toJson(locations);
            pw.println(str);
            str = gson.toJson(waterReportCounter);
            pw.println(str);
            pw.close();
            */
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
            /*
            Type userType = new TypeToken<List<User>>(){}.getType();
            Type reportType = new TypeToken<List<Report>>(){}.getType();
            Type locationType = new TypeToken<List<Location>>(){}.getType();
            Type counterType = new TypeToken<Integer>(){}.getType();
            //Gson gson = new Gson();
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Report.class, new ReportAdapter());
            Gson gson = gsonBuilder.create();

            ct = br.readLine();
            users = gson.fromJson(ct, userType);
            ct = br.readLine();
            reports = gson.fromJson(ct, reportType);
            ct = br.readLine();
            locations = gson.fromJson(ct, locationType);
            ct = br.readLine();
            waterReportCounter = gson.fromJson(ct, counterType);
            */
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Report.class, new ReportAdapter());
            Gson gson = gsonBuilder.create();

            Type modelType = new TypeToken<Model>(){}.getType();

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
