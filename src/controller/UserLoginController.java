package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import model.Model;
import model.User;


/**
 * The controller for the user login dialog.
 *
 */
public class UserLoginController {


    /** references to the widgets in the fxml file */
    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private AnchorPane anchorPane;


    /** the window for this dialog */
    private Stage dialogStage;

    /** temporary user object to edit used to validate login information */
    private User user;

    /** flag to signal whether user successfully logged in */
    private boolean loggedIn = false;



    /**
     * Initializes the controller class. This method is automatically called
     * after the constructor and after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

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
     * Sets the user to be edited in the dialog.
     *
     * @param user  the user who will be edited
     */
    public void setUser(User user) {
        this.user = user;

        if (user == null) System.out.println("User was null in login!");

        /*
        nameField.setText(user.getName());
        passwordField.setText(user.getPassword());
        */

        nameField.setPromptText(user.getUserName());
        passwordField.setPromptText(user.getPassword());

    }

    /**
     * Sets the default focus of the window
     */
    public void focus() {
        anchorPane.requestFocus();
    }

    /**
     *
     * @return  true if the user logged in, false otherwise
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Called when the user clicks submit.
     */
    @FXML
    private void handleSubmitPressed() {

        if (isInputValid(nameField.getText(), passwordField.getText())) {

            loggedIn = true;
            dialogStage.close();

        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Login");
            alert.setHeaderText("Wrong username or password");
            alert.setContentText("Please enter correct information");

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
        for (User user : Model.getInstance().getUsers()) {
            if (user.equals(tempUser)) {
                this.user = user;
                Model.getInstance().setLoggedUser(user);
                return true;
            }
        }
        return false;
    }

}
