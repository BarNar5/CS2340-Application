package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;


public class UserLoginController {


    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;

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


        nameField.setText(user.getName());
        passwordField.setText(user.getPassword());

    }


    public boolean isOkClicked() {
        return okClicked;
    }


    private void handleOKPressed() {

        if (isInputValid()) {

            user.setName(nameField.getText());
            user.setPassword(passwordField.getText());


            okClicked = true;
            dialogStage.close();
        } else {
            //TODO: incorrect username or password
        }
    }

    @FXML
    private void handleCancelPressed() {
        dialogStage.close();
    }


    private boolean isInputValid() {
        //TODO: must be one of registered users
        /*
        if user.equals(userFromUsers)
            user = userFromUsers
         */
        return true;
    }

}
