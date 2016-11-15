package controller;

import javafx.fxml.FXML;
import fxapp.MainApplication;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;


import model.WaterQualityReport;
import model.Model;

/**
 * The controller for the quality report list view.
 *
 */
public class QualityReportListController {

    /** a link back to the main application class */
    private MainApplication mainApplication;


    /** references to the widgets in the fxml file */
    @FXML
    private ListView<WaterQualityReport> reportList = new ListView<>();

    @FXML
    private AnchorPane anchorPane;


    /**
     * Initializes the controller class. This method is automatically called
     * after the constructor and after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        reportList.setItems(Model.getInstance().getQualityReports());
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
