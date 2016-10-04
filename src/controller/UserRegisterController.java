package controller;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import model.AccountType;
import model.Model;
import model.User;


/**
 * The controller for the user register dialog.
 *
 */
public class UserRegisterController {


    /** references to the widgets in the fxml file */
    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ComboBox<String> accountTypeField = new ComboBox<>();


    /** the window for this dialog */
    private Stage dialogStage;

    /** temporary user object to edit used to validate register information */
    private User user;

    /** flag to signal whether user successfully registered */
    private boolean registered = false;



    /**
     * Initializes the controller class. This method is automatically called
     * after the constructor and after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        AccountType[] values = AccountType.values();

        String[] types = new String[values.length];

        for (int i = 0; i < values.length; i++) {
            types[i] = values[i].toString();
        }
        ObservableList<String> options = FXCollections.observableArrayList(
                types);

        accountTypeField.getItems().addAll(options);
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

        nameField.setPromptText(user.getUserName());
        passwordField.setPromptText(user.getPassword());
        accountTypeField.setValue(user.getAccountType().toString());

    }

    /**
     * Sets the default focus of the window
     */
    public void focus() {
        anchorPane.requestFocus();
    }

    /**
     *
     * @return  true if the user registered, false otherwise
     */
    public boolean isRegistered() {
        return registered;
    }

    /**
     * Called when the user clicks submit.
     */
    @FXML
    private void handleSubmitPressed() {

        if (isInputValid(nameField.getText(), passwordField.getText())) {

            user.setUserName(nameField.getText());
            user.setPassword(passwordField.getText());
            user.setAccountType(AccountType.valueOf(accountTypeField.getValue()));
            Model.getInstance().addUser(user);

            registered = true;
            dialogStage.close();

        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Registration");
            alert.setHeaderText("User with this username already exists");
            alert.setContentText("Please enter different username");

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
     * @param name username of the new user
     * @param password password of a new user
     * @return true if the input is valid
     */
    private boolean isInputValid(String name, String password) {

        User tempUser = new User(name, password);
        for (User user : Model.getInstance().getUsers()) {
            if (user.equalName(tempUser)) {
                return false;
            }
        }
        return true;
    }

}
