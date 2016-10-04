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


public class ApplicationController {

    @FXML
    private Text welcomeMessage;

    @FXML
    private HBox welcomeHBox;

    @FXML
    private AnchorPane anchorPane;

    private MainApplication mainApplication;

    private User activeUser;

    @FXML
    private void initialize() {
        welcomeMessage.setText("Welcome!");
        welcomeMessage.setTextAlignment(TextAlignment.CENTER);
        welcomeHBox.setAlignment(Pos.CENTER);
    }

    public void setActiveUser(User user) {
        activeUser = user;
        welcomeMessage.setText("Welcome " + activeUser.getUserName() + "!");
    }
    public User getActiveUser() {
        return activeUser;
    }

    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    public void focus() {
        anchorPane.requestFocus();
    }

    @FXML
    private void handleSignOutPressed() {
        Model.getInstance().setLoggedUser(new User());
        mainApplication.initMainLayout();
    }

    @FXML
    private void handleMyProfilePressed() {
        mainApplication.showProfileScreen();
    }
}
