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

    @FXML
    private void initialize() {

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


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

    @FXML
    private void handleCancelPressed() {
        dialogStage.close();
    }


    private boolean isInputValid(String name, String password) {

        User tempUser = new User(name, password);
        if (user.equals(tempUser)) {
            return true;
        }
        return false;
    }

}
