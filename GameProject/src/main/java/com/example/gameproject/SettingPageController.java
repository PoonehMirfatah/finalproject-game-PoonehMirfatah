package com.example.gameproject;

import Controllers.PlayerController;
import Controllers.SQL.SQLController;
import Models.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SettingPageController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(!player.isMute()){
            soundOffImage.setVisible(true);
            soundOnImage.setVisible(false);
        }else {
            soundOffImage.setVisible(false);
            soundOnImage.setVisible(true);
        }
    }
    @FXML
    private TextField passwordTF;

    @FXML
    private ImageView soundOffImage;

    @FXML
    private ImageView soundOnImage;

    @FXML
    private TextField userNameTF;
    public static MediaPlayer player;


    @FXML
    void backToHome(MouseEvent event) throws IOException {
        PageController.setstage(event,"HomePage.fxml");
    }

    @FXML
    void muteMusic(MouseEvent event) {
        soundOffImage.setVisible(false);
        soundOnImage.setVisible(true);
        player.setMute(true);
        player.stop();
    }

    @FXML
    void playSound(MouseEvent event) {
        soundOffImage.setVisible(true);
        soundOnImage.setVisible(false);
        player.setMute(false);
        player.play();
    }

    @FXML
    void saveEdits(MouseEvent event) throws Exception {
        if(userNameTF.getText().isEmpty() && passwordTF.getText().isEmpty()){
            PageController.showAlert("Error","Please fill in Your Informations!"," ",
                    Alert.AlertType.ERROR);
            return;
        }
        SQLController.updatePlayerInfo(userNameTF.getText(),passwordTF.getText(),
                PlayerController.getPlayer().getID());
    }

    public static void setSound(String soundName) throws URISyntaxException {
        if(player!=null) {
            if (player.isMute()) {
                return;
            }
        }
        String fileName = Main.class.getResource(soundName).toURI().toString();
        Media media = new Media(fileName);
        player= new MediaPlayer(media);
        player.play();
    }

}
