package fxapp;

import controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import model.Model;
import model.User;

import java.io.IOException;


public class MainApplication extends Application {


    private Stage mainScreen;

    private AnchorPane mainLayout;

    @Override
    public void start(Stage primaryStage) {
        mainScreen = primaryStage;
        initMainLayout();
    }

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

            return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

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

            if (controller.isOkClicked()) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initOwner(dialogStage);
                alert.setHeaderText("Registration Successful!");
                alert.setContentText("You can now login to the application");

                alert.showAndWait();
            }

            return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

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

            if (controller.isOkClicked()) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initOwner(dialogStage);
                alert.setHeaderText("Password Changed!");
                //alert.setContentText("You can now login to the application");

                alert.showAndWait();
            }

            return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

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

    public static void main(String[] args) {
        launch(args);
    }
}
