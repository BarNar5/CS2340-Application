package controller;

import fxapp.MainApplication;
import javafx.fxml.FXML;
import model.User;
import model.Model;



public class WelcomeScreenController {

    private MainApplication mainApplication;


    @FXML
    private void initialize() {

    }


    public void setMainApp(MainApplication mainApplication) {

        this.mainApplication = mainApplication;

    }


    @FXML
    public void loginPressed() {

        User tempUser = new User();
        boolean okClicked = mainApplication.showLoginDialog(tempUser);
        if (okClicked) {
            Model.getInstance().setLoggedUser(tempUser);
            mainApplication.showApplicationScreen();
        }

    }

    @FXML
    public void registerPressed() {

        User tempUser = new User();
        boolean okClicked = mainApplication.showRegisterDialog(tempUser);
        if (okClicked) {
            Model.getInstance().addUser(tempUser);
            //TODO: registration successful message
        }

    }

}
