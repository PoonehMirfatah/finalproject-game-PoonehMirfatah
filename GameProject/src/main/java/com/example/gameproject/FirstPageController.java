package com.example.gameproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.gameproject.SettingPageController.player;
import static com.example.gameproject.SettingPageController.setSound;

public class FirstPageController  implements Initializable{

    public Button signinBT;
    public Button signUpBT;
    @FXML
    private Button exitBT;

    @FXML
    private Button startBT;


    public void signIn(ActionEvent event) throws IOException {
        PageController.setstage(event,"LoginPage.fxml");
    }

    public void signUp(ActionEvent event) throws IOException {
        PageController.setstage(event,"SignUpPage.fxml");
    }

    public void exit(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            SettingPageController.setSound("Music/gamemusic.mp3");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        //player.setCycleCount(MediaPlayer.INDEFINITE);
    }
}
