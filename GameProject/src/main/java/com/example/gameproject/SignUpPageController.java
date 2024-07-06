package com.example.gameproject;

import Models.Player;
import Controllers.SQL.SQLController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpPageController implements Initializable {
    public TextField usernameTF;
    public TextField passwordTF;
    public Button confirmBT;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void signUp(ActionEvent event) throws Exception {
        String username=usernameTF.getText();
        String password=passwordTF.getText();
        Player player=new Player(username,password);
        SQLController.insertPlayer(player);

    }

}
