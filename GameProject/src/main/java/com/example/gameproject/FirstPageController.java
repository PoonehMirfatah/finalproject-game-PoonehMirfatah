package com.example.gameproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FirstPageController implements Initializable {

    public Button signinBT;
    public Button signUpBT;
    @FXML
    private Button exitBT;

    @FXML
    private Button startBT;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void signIn(ActionEvent event) throws IOException {
        PageController.setstage(event,"LoginPage.fxml");
    }

    public void signUp(ActionEvent event) throws IOException {
        PageController.setstage(event,"SignUpPage.fxml");
    }

    public void exit(ActionEvent event) {
        System.exit(0);
    }
}
