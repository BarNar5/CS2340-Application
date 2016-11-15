package controller;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.beans.value.ObservableValue;

import model.User;
import model.OverallCondition;
import model.Model;


/**
 * The controller for the add quality report view.
 *
 */
public class AddQualityReportController {

    /** references to the widgets in the fxml file. */
    @FXML
    private TextField locationNameField = new TextField();

    @FXML
    private TextField locationXField = new TextField();

    @FXML
    private TextField locationYField = new TextField();

    @FXML
    private TextField virusPPM = new TextField();

    @FXML
    private TextField contaminantPPM = new TextField();

    @FXML
    private ComboBox<String> overallConditionField = new ComboBox<>();

    @FXML
    private ComboBox<String> NS = new ComboBox<>();

    @FXML
    private ComboBox<String> EW = new ComboBox<>();

    @FXML
    private AnchorPane anchorPane;


    /** the window for this dialog .*/
    private Stage dialogStage;

    /** user currently logged in. */
    private User activeUser;

    /** flag to signal whether user successfully added a report. */
    private boolean reportAdded = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the constructor and after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

        //Make location fields accept only non-negative doubles
        locationXField.textProperty().addListener((ObservableValue
            <? extends String> observable, String oldValue, String newValue)
            -> {
            if (!newValue.matches("\\d*\\.?\\d*")) {
                newValue = newValue.substring(0, newValue.length() - 1);
                newValue = newValue.replaceAll("[^(\\d|\\.)]", "");
            }
            locationXField.setText(newValue);
        });
        locationYField.textProperty().addListener((ObservableValue
            <? extends String> observable, String oldValue, String newValue)
            -> {
            if (!newValue.matches("\\d*\\.?\\d*")) {
                newValue = newValue.substring(0, newValue.length() - 1);
                newValue = newValue.replaceAll("[^(\\d|\\.)]", "");
            }
            locationYField.setText(newValue);
        });
        virusPPM.textProperty().addListener((ObservableValue
            <? extends String> observable, String oldValue, String newValue)
            -> {
            if (!newValue.matches("\\d*\\.?\\d*")) {
                newValue = newValue.substring(0, newValue.length() - 1);
                newValue = newValue.replaceAll("[^(\\d|\\.)]", "");
            }
            virusPPM.setText(newValue);
        });
        contaminantPPM.textProperty().addListener((ObservableValue
            <? extends String> observable, String oldValue, String newValue)
            -> {
            if (!newValue.matches("\\d*\\.?\\d*")) {
                newValue = newValue.substring(0, newValue.length() - 1);
                newValue = newValue.replaceAll("[^(\\d|\\.)]", "");
            }
            contaminantPPM.setText(newValue);
        });
        OverallCondition[] overallConditionValues = OverallCondition.values();

        String[] conditions = new String[overallConditionValues.length];

        for (int i = 0; i < overallConditionValues.length; i++) {
            conditions[i] = overallConditionValues[i].name();
        }
        ObservableList<String> conditionOptions =
                FXCollections.observableArrayList(conditions);
        overallConditionField.getItems().addAll(conditionOptions);

        String[] ns = {"N", "S"};
        ObservableList<String> nsOptions = FXCollections.observableArrayList(
                ns);
        NS.getItems().addAll(nsOptions);
        NS.setValue("N");

        String[] ew = {"E", "W"};
        ObservableList<String> ewOptions = FXCollections.observableArrayList(
                ew);
        EW.getItems().addAll(ewOptions);
        EW.setValue("E");

        locationXField.setPromptText("latitude");
        locationYField.setPromptText("longitude");
        locationNameField.setPromptText("location name");
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage the stage for this dialog
     */
    public final void setDialogStage(final Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    /**
     * Set the logged user.
     *
     * @param user currently logged in user
     */
    public void setActiveUser(User user) {
        activeUser = user;
    }

    /**
     * Sets the default focus of the window
     */
    public void focus() {
        anchorPane.requestFocus();
    }

    /**
     *
     * @return  true if the report was added, false otherwise
     */
    public boolean isReportAdded() {
        return reportAdded;
    }

    /**
     * Called when the user clicks submit.
     */
    @FXML
    private void handleSubmitPressed() {


        if (locationNameField.getText().length() > 0
                && locationXField.getText().length() > 0
                && locationYField.getText().length() > 0
                && virusPPM.getText().length() > 0
                && contaminantPPM.getText().length() > 0
                && overallConditionField.getValue() != null) {

            if (Double.valueOf(locationXField.getText()) <= 90
                    && Double.valueOf(locationYField.getText()) <= 180) {
                Double ns = Double.valueOf(locationXField.getText());
                Double ew = Double.valueOf(locationYField.getText());
                if ("S".equals(NS.getValue())) {
                    ns *= -1;
                }
                if ("W".equals(EW.getValue())) {
                    ew *= -1;
                }
                activeUser.addQualityReport(Model.getInstance().
                        getWaterReportCounter(),
                        OverallCondition.valueOf(overallConditionField.
                        getValue()), locationNameField.getText(),
                        ns, ew,
                        Double.valueOf(virusPPM.getText()),
                        Double.valueOf(contaminantPPM.getText()));

                reportAdded = true;
                dialogStage.close();
            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Invalid Data");
                alert.setHeaderText("The location coordinates "
                        + "entered are incorrect");
                alert.setContentText("Please input correct coordinates");

                alert.showAndWait();
            }
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Data");
            alert.setHeaderText("All the fields must be filled");
            alert.setContentText("Please fill all the fields to submit"
                    + "a water report");

            alert.showAndWait();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancelPressed() {
        dialogStage.close();
    }
}
