package com.example.gameproject;

import Controllers.PlayerController;
import Controllers.SQL.SQLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginPageController  {

    public TextField usernameTF;
    public TextField passwordTF;
    public Button confirmBT;

    public void signIn(ActionEvent event) throws SQLException, IOException {
        String username = usernameTF.getText();
        String password = passwordTF.getText();
        try {
            SQLController.loadPlayer(username, password);
        } catch (Exception e) {
            PageController.showAlert("Error", e.getMessage(), "", Alert.AlertType.ERROR);
        }
        if (PlayerController.getPlayer()==null) {
            PageController.showAlert("Error", "User Not Found!", "", Alert.AlertType.ERROR);
            return;
        }
        PageController.setstage(event, "HomePage.fxml");
    }
    public void backToFirstPage(MouseEvent event) throws IOException {
        PageController.setstage(event,"FirstPage.fxml");
    }
}
