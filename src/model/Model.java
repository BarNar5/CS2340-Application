package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {

    private static final Model instance = new Model();

    private User loggedUser;

    public static Model getInstance() {
        return instance;
    }

    public User getLoggedUser() {
        return loggedUser;
    }
    public void setLoggedUser(User user) {
        loggedUser = user;
    }


    private final ObservableList<User> users = FXCollections.observableArrayList();


    private Model () {
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
    }


    public ObservableList<User> getUsers() {
        return users;
    }


    public boolean addUser(User user) {
        return users.add(user);
    }
}
