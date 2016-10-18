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
 * The controller for the main application window.
 *
 */
public class ApplicationController {

    /** a link back to the main application class */
    private MainApplication mainApplication;



    /** references to the widgets in the fxml file */
    @FXML
    private Text welcomeMessage;

    @FXML
    private HBox welcomeHBox;

    @FXML
    private AnchorPane anchorPane;


    /** user currently logged in */
    private User activeUser;



    /**
     * Initializes the controller class. This method is automatically called
     * after the constructor and after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        welcomeMessage.setText("Welcome!");
        welcomeMessage.setTextAlignment(TextAlignment.CENTER);
        welcomeHBox.setAlignment(Pos.CENTER);
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
     * Set the logged user and setup the welcome message.
     *
     * @param user currently logged in user
     */
    public void setActiveUser(User user) {
        activeUser = user;
        welcomeMessage.setText("Welcome " + activeUser.getUserName() + "!");
    }

    /**
     * Sets the default focus of the window
     */
    public void focus() {
        anchorPane.requestFocus();
    }

    /**
     * Called when the user clicks sign out.
     */
    @FXML
    private void handleSignOutPressed() {
        Model.getInstance().setLoggedUser(new User());
        mainApplication.initMainLayout();
    }

    @FXML
    private void handleAddWaterReportPressed() {
        mainApplication.showAddWaterSourceReportDialog();
    }

    /**
     * Called when the user clicks my profile.
     */
    @FXML
    private void handleMyProfilePressed() {
        mainApplication.showProfileScreen();
    }

    /**
     * Called when the user clicks view my reports.
     */
    @FXML
    private void handleViewReportsPressed() {
        mainApplication.showReportListScreen();
    }
}
