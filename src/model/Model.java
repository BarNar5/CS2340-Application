package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * This class serves as a Facade into the application model
 * Right now it is a Singleton so the controllers can get access easily.
 *
 */

public class Model {

    /** Set Model up as a singleton design pattern */
    private static final Model instance = new Model();
    public static Model getInstance() {
        return instance;
    }

    /** a list of all the users */
    private final ObservableList<User> users = FXCollections.observableArrayList();

    /** a list of all the water locations */
    private final List<Location> locations = new ArrayList<>();

    /** a user currently logged in */
    private User loggedUser;

    private int waterReportCounter;


    /**
     * Make a new Model.
     * Fill it with some users for testing reasons.
     */
    private Model () {
        waterReportCounter = 10000000;
        users.add(new User("Bartek", "qwerty"));
        users.add(new User("a", "a"));
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
        Location l = new Location(1, 33.7756, -84.3963, "Test Marker", "<h2>Test </h2>  \nsome data");
        locations.add(l);
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
        return users;
    }

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

    public boolean addLocation(Location location) {
        return locations.add(location);
    }
}
