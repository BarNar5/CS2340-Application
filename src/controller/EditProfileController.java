package controller;

import javafx.fxml.FXML;
import fxapp.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import model.AccountType;
import model.Gender;
import model.User;


/**
 * The controller for the edit profile view.
 *
 */
public class EditProfileController {

    /** a link back to the main application class */
    private MainApplication mainApplication;


    /** references to the widgets in the fxml file */
    @FXML
    private TextField nameField = new TextField();

    @FXML
    private ComboBox<String> accountTypeField = new ComboBox<>();

    @FXML
    private ComboBox<String> genderField = new ComboBox<>();

    @FXML
    private TextField dateMonthField = new TextField();

    @FXML
    private TextField dateDayField = new TextField();

    @FXML
    private TextField dateYearField = new TextField();

    @FXML
    private TextField phoneField = new TextField();

    @FXML
    private TextField emailField = new TextField();

    @FXML
    private TextField address1Field = new TextField();

    @FXML
    private TextField address2Field = new TextField();

    @FXML
    private TextField address3Field = new TextField();

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
        Gender[] genderValues = Gender.values();

        String[] genders = new String[genderValues.length + 1];
        genders[0] = "";

        for (int i = 0; i < genderValues.length; i++) {
            genders[i + 1] = genderValues[i].toString();
        }
        ObservableList<String> genderOptions =
                FXCollections.observableArrayList(genders);

        genderField.getItems().addAll(genderOptions);

        AccountType[] typeValues = AccountType.values();

        String[] types = new String[typeValues.length];

        for (int i = 0; i < typeValues.length; i++) {
            types[i] = typeValues[i].toString();
        }
        ObservableList<String> typeOptions = FXCollections.observableArrayList(
                types);

        accountTypeField.getItems().addAll(typeOptions);
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
        nameField.setPromptText("name");
        accountTypeField.setValue(user.getAccountType().toString());
        if (user.getName() != null) {
            nameField.setText(user.getName());
        }
        if (user.getGender() != null) {
            genderField.setValue(user.getGender().toString());
        }

        dateMonthField.setPromptText("mm");
        dateDayField.setPromptText("dd");
        dateYearField.setPromptText("yyyy");

        if (user.getDateYear() != null && user.getDateDay() != null
                && user.getDateMonth() != null) {
            dateMonthField.setText((user.getDateMonth()));
            dateDayField.setText((user.getDateDay()));
            dateYearField.setText((user.getDateYear()));
        }
        phoneField.setPromptText("phone number");
        if (user.getPhoneNumber() != null) {
            phoneField.setText(user.getPhoneNumber());
        }
        emailField.setPromptText("email");
        if (user.getEmail() != null) {
            emailField.setText(user.getEmail());
        }
        address1Field.setPromptText("address line 1");
        if (user.getAddress1() != null) {
            address1Field.setText(user.getAddress1());
        }
        address2Field.setPromptText("address line 2");
        if (user.getAddress2() != null) {
            address2Field.setText(user.getAddress2());
        }
        address3Field.setPromptText("address line 3");
        if (user.getAddress3() != null) {
            address3Field.setText(user.getAddress3());
        }
    }

    /**
     * Sets the default focus of the window
     */
    public void focus() {
        anchorPane.requestFocus();
    }

    /**
     * Called when the user clicks save.
     */
    @FXML
    private void handleSavePressed() {
        activeUser.setName(nameField.getText());
        activeUser.setAccountType(AccountType.
                valueOf(accountTypeField.getValue()));
        if (genderField.getValue() != null
                && !genderField.getValue().equals("")) {
            activeUser.setGender(Gender.valueOf(genderField.getValue()));
        } else {
            activeUser.setGender(null);
        }
        if (dateMonthField.getText().length() > 0
                && dateDayField.getText().length() > 0
                && dateYearField.getText().length() > 0) {
            activeUser.setDateMonth(dateMonthField.getText());
            activeUser.setDateDay(dateDayField.getText());
            activeUser.setDateYear(dateYearField.getText());
        }
        if (dateMonthField.getText().length() == 0
                && dateDayField.getText().length() == 0
                && dateYearField.getText().length() == 0) {
            activeUser.setDateMonth(null);
            activeUser.setDateDay(null);
            activeUser.setDateYear(null);
        }
        activeUser.setPhoneNumber(phoneField.getText());
        activeUser.setEmail(emailField.getText());
        activeUser.setAddress1(address1Field.getText());
        activeUser.setAddress2(address2Field.getText());
        activeUser.setAddress3(address3Field.getText());

        mainApplication.showProfileScreen();
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancelPressed() {
        mainApplication.showProfileScreen();
    }

}
