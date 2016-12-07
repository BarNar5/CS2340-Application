package controller;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.Button;

import javafx.beans.value.ObservableValue;

import model.User;
import model.OverallCondition;
import model.Model;

public class BanUserController {
	@FXML
	private Button banned = new Button("Ban");
	
	@FXML
    private ComboBox<String> userList = new ComboBox<>();
	
	@FXML
	private void initialize() {
        ObservableList<User> users = Model.getInstance().getUsers();
        userList.getItems().add(users.get(0).getUserName());
	}
}
