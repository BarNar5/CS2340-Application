package controller;

import javafx.fxml.FXML;
import fxapp.MainApplication;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;


import model.User;
import model.Report;

/**
 * The controller for the water report list view.
 *
 */
public class WaterReportListController {

    /** a link back to the main application class */
    private MainApplication mainApplication;

    @FXML
    private ListView<Report> reportList;

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
    }

    /**
     * Set the logged user and setup the profile information.
     *
     * @param user currently logged in user
     */
    public void setActiveUser(User user) {
        activeUser = user;
        reportList.setItems(FXCollections.observableArrayList(activeUser.getWaterReports()));
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
     * Called when the user clicks back.
     */
    @FXML
    private void handleBackPressed() {
        mainApplication.showApplicationScreen();
    }
}
