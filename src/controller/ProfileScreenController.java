package controller;

import javafx.fxml.FXML;
import fxapp.MainApplication;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import model.Model;
import model.User;

public class ProfileScreenController {

    @FXML
    private Label nameLabel;

    @FXML
    private Label genderLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label address1Label;

    @FXML
    private Label address2Label;

    @FXML
    private Label address3Label;

    @FXML
    private AnchorPane anchorPane;


    private MainApplication mainApplication;

    private User activeUser;


    @FXML
    private void initialize() {

    }


    public void setActiveUser(User user) {
        activeUser = user;
        nameLabel.setText(user.getName());
        if (user.getGender() != null) {
            genderLabel.setText(user.getGender().toString());
        }
        if (user.getDateYear() != null && user.getDateDay() != null && user.getDateMonth() != null) {
            dateLabel.setText((user.getDateMonth() + "/" + user.getDateDay() + "/" + user.getDateYear()));
        }
        if (user.getPhoneNumber() != null) {
            phoneLabel.setText(user.getPhoneNumber().toString());
        }
        emailLabel.setText(user.getEmail());
        address1Label.setText(user.getAddress1());
        address2Label.setText(user.getAddress2());
        address3Label.setText(user.getAddress3());
    }
    public User getActiveUser() {
        return activeUser;
    }

    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    public void focus() {
        anchorPane.requestFocus();
    }

    //TODO: change password button

    @FXML
    private void handleMainScreenPressed() {
        mainApplication.showApplicationScreen();
    }

    @FXML
    private void handleEditPressed() {
        mainApplication.showEditProfileScreen();
    }

}
