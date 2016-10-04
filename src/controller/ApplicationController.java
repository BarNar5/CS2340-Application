package controller;

import javafx.fxml.FXML;
import fxapp.MainApplication;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import model.Model;
import model.User;

/**
* Main class for the application controller
*/
public class ApplicationController {

    @FXML
    private Text welcomeMessage;

    @FXML
    private HBox welcomeHBox;

    @FXML
    private AnchorPane anchorPane;

    private MainApplication mainApplication;

    private User activeUser;

    /**
    * initializes the application state
    */
    @FXML
    private void initialize() {
        welcomeMessage.setText("Welcome!");
        welcomeMessage.setTextAlignment(TextAlignment.CENTER);
        welcomeHBox.setAlignment(Pos.CENTER);
    }
    /**
    * Setter method for the active user
    * @param user the user being passed in as an active user
    */
    public void setActiveUser(User user) {
        activeUser = user;
        welcomeMessage.setText("Welcome " + activeUser.getUserName() + "!");
    }
    /**
    * Getter method for the active user
    * @return the currently active user
    */
    public User getActiveUser() {
        return activeUser;
    }
    /**
    * Setter method instantiates the main application
    * @param mainApplication the instance of the main application class
    */
    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    public void focus() {
        anchorPane.requestFocus();
    }
    /**
    * Method to sign out the user
    */
    @FXML
    private void handleSignOutPressed() {
        Model.getInstance().setLoggedUser(new User());
        mainApplication.initMainLayout();
    }
    /**
    * Method to display the user profile when profile is pressed
    */
    @FXML
    private void handleMyProfilePressed() {
        mainApplication.showProfileScreen();
    }
}
