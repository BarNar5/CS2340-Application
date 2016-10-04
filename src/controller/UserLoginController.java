package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Model;
import model.User;


public class UserLoginController {


    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private AnchorPane anchorPane;

    private Stage dialogStage;

    private User user;

    private boolean okClicked = false;

    @FXML
    private void initialize() {

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


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

    public void focus() {
        anchorPane.requestFocus();
    }


    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleSubmitPressed() {

        if (isInputValid(nameField.getText(), passwordField.getText())) {

            okClicked = true;
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

    @FXML
    private void handleCancelPressed() {
        dialogStage.close();
    }


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
