package controller;

import javafx.fxml.FXML;
import fxapp.MainApplication;
import javafx.scene.layout.AnchorPane;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.AccountType;
import model.User;
import model.Model;

import java.io.File;


/**
 * The controller for the default welcome screen window.
 *
 */
public class WelcomeScreenController {

    /** a link back to the main application class ans stage */
    private MainApplication mainApplication;
    private Stage mainStage;


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
     * Setup the main stage for this view.
     * @param mainStage
     */
    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
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

        User tempUser = new User("enter username", "enter password", AccountType.USER);
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

        User tempUser = new User("enter username", "enter password", AccountType.USER);
        boolean okClicked = mainApplication.showRegisterDialog(tempUser);
        if (okClicked) {
            Model.getInstance().addUser(tempUser);
        }

    }

    /**
     * Called when the user clicks load data.
     */
    @FXML
    public void onLoadData() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Open JSON File");
        File file  = fc.showOpenDialog(mainStage);
        if (file != null)
            Model.getInstance().loadFromJson(file);
    }

    /**
     * Called when the user clicks sava data.
     */
    @FXML
    public void onSaveData() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Save JSON File");
        File file  = fc.showSaveDialog(mainStage);
        if (file != null)
            Model.getInstance().saveToJson(file);
    }
}
