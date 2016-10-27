package controller;

import javafx.fxml.FXML;
import fxapp.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


import model.*;

/**
 * The controller for the quality report list view.
 *
 */
public class QualityReportListController {

    /** a link back to the main application class */
    private MainApplication mainApplication;

    @FXML
    private ListView<WaterQualityReport> reportList = new ListView<>();

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
        reportList.setItems(Model.getInstance().getQualityReports());
    }

    /**
     * Set the logged user and setup the profile information.
     *
     * @param user currently logged in user
     */
    public void setActiveUser(User user) {
        activeUser = user;
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
