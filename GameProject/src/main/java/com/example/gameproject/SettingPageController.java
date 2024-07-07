package com.example.gameproject;

import Controllers.PlayerController;
import Controllers.SQL.SQLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SettingPageController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(running){
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
    static MediaPlayer player;
    Media media;
    static Boolean running=true;

    @FXML
    void backToHome(MouseEvent event) throws IOException {
        PageController.setstage(event,"HomePage.fxml");
    }

    @FXML
    void muteMusic(MouseEvent event) {
        soundOffImage.setVisible(false);
        soundOnImage.setVisible(true);
        player.stop();
        running=true;
    }

    @FXML
    void playSound(MouseEvent event) {
        soundOffImage.setVisible(true);
        soundOnImage.setVisible(false);
        player.play();
        running=true;
    }

    @FXML
    void saveEdits(MouseEvent event) throws Exception {
        if(userNameTF.getText().isEmpty() && passwordTF.getText().isEmpty()){
            PageController.showAlert("Error","Please fill in Your Informations!"," ",
                    Alert.AlertType.ERROR);
            return;
        }
        SQLController.updatePlayerInfo(userNameTF.getText(),passwordTF.getText(),
                PlayerController.getInstance().player.getID());
    }



//// public void playAudio(ActionEvent event) {
////        beginTimer();
////
////    }
////    public void stopAudio(ActionEvent event){
////
////    }
////    public void beginTimer() {
////        timer=new Timer();
////        task=new TimerTask(){
////
////            @Override
////            public void run() {
////                running = true;
////                if(player==null) {
////                    setAudio(DataBase.getDataBase().getAudioList().getFirst().getAudioLink());
////                }
////                    double current = player.getCurrentTime().toSeconds();
////                    double end = media.getDuration().toSeconds();
////                    progressBar.setProgress(current / end);
////
////                    if (current / end == 1) {
////                        cancelTimer();
////
////                }
////            }
////        };
////        timer.scheduleAtFixedRate(task,1000,1000);
////    }
////
////    public void cancelTimer() {
////        running=false;
////        timer.cancel();
////    }
}
