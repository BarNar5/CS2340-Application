package fxapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

// Our models
import model.User;

// Our controllers
import controller.UserLoginController;
import controller.WelcomeScreenController;
import controller.UserRegisterController;

import java.io.IOException;


public class MainApplication extends Application {


    private Stage mainScreen;

    private AnchorPane mainLayout;

    @Override
    public void start(Stage primaryStage) {
        mainScreen = primaryStage;
        initMainLayout(mainScreen);
    }

    private void initMainLayout(Stage mainScreen) {
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


        } catch (IOException e) {
            System.out.println("Failed to find the fxml file for MainScreen!!");
        }
    }

    public void showApplicationScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("../view/Application.fxml"));
            AnchorPane welcomeScreen = loader.load();

            //TODO: controller
            //WelcomeScreenController controller = loader.getController();
            //controller.setMainApp(this);

            mainScreen.setTitle("Welcome Screen");

            Scene scene = new Scene(welcomeScreen);
            mainScreen.setScene(scene);
            mainScreen.show();

            /*
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Welcome Screen");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainScreen);
            Scene scene = new Scene(welcomeScreen);
            dialogStage.setScene(scene);


            dialogStage.showAndWait();
            */



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


            dialogStage.showAndWait();

            return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
