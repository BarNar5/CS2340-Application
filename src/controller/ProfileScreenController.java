package controller;

import javafx.fxml.FXML;
import fxapp.MainApplication;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import model.User;


/**
 * The controller for the profile screen view
 *
 */
public class ProfileScreenController {

    /** a link back to the main application class */
    private MainApplication mainApplication;


    /** references to the widgets in the fxml file */
    @FXML
    private Label nameLabel;

    @FXML
    private Label accountTypeLabel;

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


    /** user currently logged in */
    private User activeUser;



    /**
     * Initializes the controller class. This method is automatically called
     * after the constructor and after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        activeUser = new User();
    }

    /**
     * Setup the main application link.
     *
     * @param mainApplication  a reference (link) to our main class
     */
    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    /**
     * Set the logged user and setup the profile information.
     *
     * @param user currently logged in user
     */
    public void setActiveUser(User user) {
        activeUser = user;
        nameLabel.setText(activeUser.getName());
        accountTypeLabel.setText(user.getAccountType().toString());
        if (user.getGender() != null) {
            genderLabel.setText(user.getGender().toString());
        }
        if (user.getDateYear() != null && user.getDateDay()
                != null && user.getDateMonth() != null) {
            dateLabel.setText((user.getDateMonth() + "/"
                    + user.getDateDay() + "/" + user.getDateYear()));
        }
        if (user.getPhoneNumber() != null) {
            phoneLabel.setText(user.getPhoneNumber());
        }
        emailLabel.setText(user.getEmail());
        address1Label.setText(user.getAddress1());
        address2Label.setText(user.getAddress2());
        address3Label.setText(user.getAddress3());
    }

    /**
     * Sets the default focus of the window
     */
    public void focus() {
        anchorPane.requestFocus();
    }

    /**
     * Called when the user clicks main screen button.
     */
    @FXML
    private void handleMainScreenPressed() {
        mainApplication.showApplicationScreen();
    }

    /**
     * Called when the user clicks edit.
     */
    @FXML
    private void handleEditPressed() {
        mainApplication.showEditProfileScreen();
    }

    /**
     * Called when the user clicks change password.
     */
    @FXML
    private void handleChangePasswordPressed() {
        mainApplication.showChangePasswordDialog();
    }

}
