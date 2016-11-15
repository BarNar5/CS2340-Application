package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import model.User;


/**
 * The controller for the password change dialog.
 *
 */
public class ChangePasswordController {


    /** references to the widgets in the fxml file */
    @FXML
    private PasswordField oldPasswordField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private AnchorPane anchorPane;


    /** the window for this dialog */
    private Stage dialogStage;

    /** user currently logged in */
    private User activeUser;

    /** flag to signal whether password was successfully changed */
    private boolean passwordChanged = false;



    /**
     * Initializes the controller class. This method is automatically called
     * after the constructor and after the fxml file has been loaded.
     */
    /*
    @FXML
    private void initialize() {

    }
    */

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage the stage for this dialog
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Set the logged user and setup password fields.
     *
     * @param user currently logged in user
     */
    public void setUser(User user) {
        this.activeUser = user;

        if (activeUser == null) {
            System.out.println("User was null in login!");
        }

        oldPasswordField.setPromptText("old password");
        newPasswordField.setPromptText("new password");

    }

    /**
     * Sets the default focus of the window
     */
    public void focus() {
        anchorPane.requestFocus();
    }

    /**
     *
     * @return  true if password was changed, false otherwise
     */
    public boolean isPasswordChanged() {
        return passwordChanged;
    }

    /**
     * Called when the user clicks submit.
     */
    @FXML
    private void handleSubmitPressed() {

        if (isInputValid(activeUser.getUserName(),
                oldPasswordField.getText())) {

            activeUser.setPassword(newPasswordField.getText());

            passwordChanged = true;
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
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancelPressed() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @param name username of a user
     * @param password password of a user
     * @return true if the input is valid
     */
    private boolean isInputValid(String name, String password) {
        User tempUser = new User(name, password);
        return activeUser.equals(tempUser);
    }

}
