package controller;

import javafx.fxml.FXML;
import fxapp.MainApplication;
import javafx.scene.layout.AnchorPane;

import model.User;
import model.Model;


/**
 * The controller for the default welcome screen window.
 *
 */
public class WelcomeScreenController {

    /** a link back to the main application class */
    private MainApplication mainApplication;


    /** references to the widgets in the fxml file */
    @FXML
    private AnchorPane anchorPane;



    /**
     * Initializes the controller class. This method is automatically called
     * after the constructor and after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

    }

    /**
     * Setup the main application link.
     *
     * @param mainApplication  a reference (link) to our main class
     */
    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    /**
     * Sets the default focus of the window
     */
    public void focus() {
        anchorPane.requestFocus();
    }

    /**
     * Called when the user clicks login.
     */
    @FXML
    public void loginPressed() {

        User tempUser = new User();
        boolean okClicked = mainApplication.showLoginDialog(tempUser);
        if (okClicked) {
            //Model.getInstance().setLoggedUser(tempUser);
            mainApplication.showApplicationScreen();
        }

    }

    /**
     * Called when the user clicks register.
     */
    @FXML
    public void registerPressed() {

        User tempUser = new User();
        boolean okClicked = mainApplication.showRegisterDialog(tempUser);
        if (okClicked) {
            Model.getInstance().addUser(tempUser);
        }

    }

}
