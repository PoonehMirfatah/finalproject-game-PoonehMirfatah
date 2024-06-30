package com.example.gameproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {

    public TextField usernameTF;
    public TextField passwordTF;
    public Button confirmBT;
    @FXML
    private Button exitBT;

    @FXML
    private Button startBT;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void signIn(ActionEvent event) {
    }
}
