package controller;

import javafx.fxml.FXML;
import fxapp.MainApplication;
import javafx.scene.text.Text;

import model.Model;
import model.User;


public class ApplicationController {

    @FXML
    private Text welcomeMessage;

    private MainApplication mainApplication;

    private User activeUser;

    @FXML
    private void initialize() {
        welcomeMessage.setText("Welcome noob!");
    }

    public void setActiveUser(User user) {
        activeUser = user;
        welcomeMessage.setText("Welcome " + activeUser.getName() + "!");
    }
    public User getActiveUser() {
        return activeUser;
    }

    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    @FXML
    private void handleSignOutPressed() {
        Model.getInstance().setLoggedUser(new User());
        mainApplication.initMainLayout();
    }
}
