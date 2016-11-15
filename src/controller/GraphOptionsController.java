package controller;

import fxapp.MainApplication;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * The controller for the graph options dialog.
 *
 */
public class GraphOptionsController {

    /**
     * a link back to the main application class
     */
    private MainApplication mainApplication;


    /** references to the widgets in the fxml file */
    @FXML
    private ComboBox<String> yearField = new ComboBox<>();

    @FXML
    private CheckBox virusBox = new CheckBox();

    @FXML
    private CheckBox contaminantBox = new CheckBox();

    @FXML
    private AnchorPane anchorPane;


    /** the window for this dialog */
    private Stage dialogStage;

    /** flag to signal whether user successfully registered */
    private boolean submitted = false;



    /**
     * Initializes the controller class. This method is automatically called
     * after the constructor and after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

        String[] years = new String[20];

        for (int i = 0; i < 20; i++) {
            years[i] = Integer.toString(2016 - i);
        }
        ObservableList<String> options = FXCollections.observableArrayList(
                years);

        yearField.getItems().addAll(options);

        yearField.setValue("2016");

        virusBox.setSelected(true);
        contaminantBox.setSelected(true);
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
     * Sets the stage of this dialog.
     *
     * @param dialogStage the stage for this dialog
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    /**
     * Sets the default focus of the window
     */
    public void focus() {
        anchorPane.requestFocus();
    }

    /**
     *
     * @return  true if the user clicked OK, false otherwise
     */
    public boolean isSubmitted() {
        return submitted;
    }

    /**
     * Called when the user clicks submit.
     */
    @FXML
    private void handleOKPressed() {

        submitted = true;
        dialogStage.close();

        mainApplication.showQualityGraphScreen(
                Integer.valueOf(yearField.getValue()),
                virusBox.isSelected(),
                contaminantBox.isSelected());
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancelPressed() {
        dialogStage.close();
    }

}
