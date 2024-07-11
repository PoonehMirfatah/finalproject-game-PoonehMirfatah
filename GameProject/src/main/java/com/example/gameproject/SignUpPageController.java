package com.example.gameproject;

import Models.Player;
import Controllers.SQL.SQLController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpPageController  {
    public TextField usernameTF;
    public TextField passwordTF;
    public Button confirmBT;


    public void signUp(ActionEvent event) throws Exception {
        String username=usernameTF.getText();
        String password=passwordTF.getText();
        Player player=new Player(username,password);
        SQLController.insertPlayer(player);
        PageController.setstage(event,"FirstPage.fxml");
    }

}
