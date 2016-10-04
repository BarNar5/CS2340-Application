package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.AccountType;
import model.Model;
import model.User;

/**
* Controller class for changing the password
*/
public class ChangePasswordController {


    @FXML
    private PasswordField oldPasswordField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private AnchorPane anchorPane;

    private Stage dialogStage;

    private User user;

    private boolean okClicked = false;

    /**
    * Initialize method for the change password controller class
    */
    @FXML
    private void initialize() {

    }
    /**
    * Setter method for the dialogStage
    * @param dialogStage the stage instance being passed in
    */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
    * Setter method for the user
    * @param user the current user being passed in as a parameter
    */
    public void setUser(User user) {
        this.user = user;

        if (user == null) System.out.println("User was null in login!");

        oldPasswordField.setPromptText("old password");
        newPasswordField.setPromptText("new password");

    }

    public void focus() {
        anchorPane.requestFocus();
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return  true if the user clicked the OK button
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
    * Method to handle the input of the user when the submit button is pressed
    */
    @FXML
    private void handleSubmitPressed() {

        if (isInputValid(user.getUserName(), oldPasswordField.getText())) {

            user.setPassword(newPasswordField.getText());

            okClicked = true;
            dialogStage.close();

        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Password");
            alert.setHeaderText("Password entered is incorrect");
            alert.setContentText("Please enter correct password");

            alert.showAndWait();
        }
    }
    /**
    * Method to handle the cancel button being pressed
    */
    @FXML
    private void handleCancelPressed() {
        dialogStage.close();
    }

    /**
    * Validation method for the user and password input
    * @param name the username of the user
    * @param password the user password
    * @return the boolean value for the validity of the login information
    */
    private boolean isInputValid(String name, String password) {

        User tempUser = new User(name, password);
        if (user.equals(tempUser)) {
            return true;
        }
        return false;
    }

}
