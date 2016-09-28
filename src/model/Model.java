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
    }


    public ObservableList<User> getUsers() {
        return users;
    }


    public boolean addUser(User user) {
        return users.add(user);
    }
}
