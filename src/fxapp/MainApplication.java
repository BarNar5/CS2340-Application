package fxapp;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import model.Model;
import model.User;

import controller.*;

import netscape.javascript.JSObject;

import java.io.IOException;


/**
 * Main application class.
 *
 * This class handles all the scene switching.
 *
 */

public class MainApplication extends Application {


    /** the main container for the application window */
    private Stage mainScreen;

    /** the main layout for the main window */
    private AnchorPane mainLayout;

    @Override
    public void start(Stage primaryStage) {
        mainScreen = primaryStage;
        initMainLayout();
    }

    /**
     * Initialize the main screen for the application and display the welcome view.
     * Most other views will be shown in this screen.
     * This view is shown on the application startup.
     *
     */
    public void initMainLayout() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("../view/WelcomeScreen.fxml"));
            mainLayout = loader.load();

            WelcomeScreenController controller = loader.getController();
            controller.setMainApp(this);

            mainScreen.setTitle("Main Screen");

            Scene scene = new Scene(mainLayout);
            mainScreen.setScene(scene);
            mainScreen.show();

            controller.focus();


        } catch (IOException e) {
            System.out.println("Failed to find the fxml file for MainScreen!!");
        }
    }

    /**
     * Setup the default application view that is shown after successful login.
     *
     */
    public void showApplicationScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("../view/Application.fxml"));
            AnchorPane welcomeScreen = loader.load();

            ApplicationController controller = loader.getController();
            controller.setMainApp(this);
            controller.setActiveUser(Model.getInstance().getLoggedUser());

            mainScreen.setTitle("Welcome Screen");

            Scene scene = new Scene(welcomeScreen);
            mainScreen.setScene(scene);
            mainScreen.show();

            controller.focus();

        } catch (IOException e) {
            System.out.println("Failed to find the fxml file for WelcomeScreen!!");
        }
    }

    /**
     * Opens a dialog to login. If the user clicks SUBMIT and authentication is successful
     * the user is logged in to the application.
     *
     * @param user the user object to be edited
     * @return true if the user logged in, false otherwise.
     */
    public boolean showLoginDialog(User user) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("../view/LoginScreen.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Login");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainScreen);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            UserLoginController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setUser(user);

            controller.focus();


            dialogStage.showAndWait();

            return controller.isLoggedIn();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Opens a dialog to register. If the user clicks SUBMIT and username is valid
     * a new user is added to the list of registered users.
     *
     * @param user the user object to be edited
     * @return true if the user registered, false otherwise.
     */
    public boolean showRegisterDialog(User user) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("../view/RegisterScreen.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Register");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainScreen);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            UserRegisterController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setUser(user);

            controller.focus();


            dialogStage.showAndWait();

            if (controller.isRegistered()) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initOwner(dialogStage);
                alert.setHeaderText("Registration Successful!");
                alert.setContentText("You can now login to the application");

                alert.showAndWait();
            }

            return controller.isRegistered();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Opens a dialog to change password. If the old password is valid
     * a password is is changed to a new one.
     *
     * @return true if the user changed password, false otherwise.
     */
    public boolean showChangePasswordDialog() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("../view/ChangePasswordScreen.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Change Password");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainScreen);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ChangePasswordController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setUser(Model.getInstance().getLoggedUser());

            controller.focus();


            dialogStage.showAndWait();

            if (controller.isPasswordChanged()) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initOwner(dialogStage);
                alert.setHeaderText("Password Changed!");
                //alert.setContentText("You can now login to the application");

                alert.showAndWait();
            }

            return controller.isPasswordChanged();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Setup the view of user's profile.
     * Shown after clicking the PROFILE button in the app.
     *
     */
    public void showProfileScreen() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("../view/ProfileScreen.fxml"));
            AnchorPane profileScreen = loader.load();

            ProfileScreenController controller = loader.getController();
            controller.setMainApp(this);
            controller.setActiveUser(Model.getInstance().getLoggedUser());

            mainScreen.setTitle("Profile");

            Scene scene = new Scene(profileScreen);
            mainScreen.setScene(scene);
            mainScreen.show();

            controller.focus();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Setup the view editing user's profile.
     * Shown after clicking the EDIT button in the profile's view
     *
     */
    public void showEditProfileScreen() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("../view/EditProfileScreen.fxml"));
            AnchorPane editProfile = loader.load();

            EditProfileController controller = loader.getController();
            controller.setMainApp(this);
            controller.setActiveUser(Model.getInstance().getLoggedUser());

            mainScreen.setTitle("Profile");

            Scene scene = new Scene(editProfile);
            mainScreen.setScene(scene);
            mainScreen.show();

            controller.focus();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a dialog to add a water source report. If the user clicks SUBMIT
     * and enters all the required data a new report is added to the system.
     *
     * @return true if the report was added, false otherwise.
     */
    public boolean showAddWaterSourceReportDialog() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("../view/SubmitReportScreen.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("New Water Source Report");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainScreen);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            AddWaterSourceReportController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setActiveUser(Model.getInstance().getLoggedUser());

            controller.focus();


            dialogStage.showAndWait();

            if (controller.isReportAdded()) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initOwner(dialogStage);
                alert.setHeaderText("Report Submitted Successfully!");

                alert.showAndWait();
            }

            return controller.isReportAdded();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Setup the view of reports for an active user
     * Shown after clicking the Show My Reports button.
     *
     */
    public void showReportListScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("../view/ReportListScreen.fxml"));
            AnchorPane reportListScreen = loader.load();

            WaterReportListController controller = loader.getController();
            controller.setMainApp(this);
            controller.setActiveUser(Model.getInstance().getLoggedUser());

            mainScreen.setTitle("My Reports");

            Scene scene = new Scene(reportListScreen);
            mainScreen.setScene(scene);
            mainScreen.show();

            controller.focus();

        } catch (IOException e) {
            System.out.println("Failed to find the fxml file for WelcomeScreen!!");
        }
    }

    /**
     * Setup the map view
     * Shown after clicking the Show Map button.
     *
     */
    public void showMapAvailability() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("../view/MapScreen.fxml"));
            AnchorPane mapScreen = loader.load();


            MapController controller = loader.getController();
            controller.setMainApp(this);

            mainScreen.setTitle("Water Availability");

            Scene scene = new Scene(mapScreen);
            mainScreen.setScene(scene);
            mainScreen.show();


        } catch (IOException e) {
            System.out.println("Failed to find the fxml file for MapScreen!!");
        }
    }

    public static void main(String[] args) {
        System.setProperty("java.net.useSystemProxies", "true");
        launch(args);
    }
}
