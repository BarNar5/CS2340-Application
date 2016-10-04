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


public class UserRegisterController {


    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ComboBox<String> accountTypeField = new ComboBox<>();

    private Stage dialogStage;

    private User user;

    private boolean okClicked = false;

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

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    public void setUser(User user) {
        this.user = user;

        if (user == null) System.out.println("User was null in login!");

        nameField.setPromptText(user.getUserName());
        passwordField.setPromptText(user.getPassword());
        accountTypeField.setValue(user.getAccountType().toString());

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

        if (isInputValid(nameField.getText(), passwordField.getText())) {

            user.setUserName(nameField.getText());
            user.setPassword(passwordField.getText());
            user.setAccountType(AccountType.valueOf(accountTypeField.getValue()));
            Model.getInstance().addUser(user);

            okClicked = true;
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

    @FXML
    private void handleCancelPressed() {
        dialogStage.close();
    }


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
