package com.example.gameproject;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.Timer;
import java.util.TimerTask;

public class SettingPageController {
    public void backToHome(MouseEvent event) {
    }

    public void setMusic(MouseEvent event) {
    }
//    MediaPlayer player;
//    boolean playAdd=false;
//     Media media;
//    static Timer timer;
//    static TimerTask task;
//    static Boolean running=false;
//
//    @FXML
//    private TextField passwordTF;
//
//    @FXML
//    private ImageView sound;
//
//    @FXML
//    private TextField userNameTF;
//
//    @FXML
//    void backToHome(MouseEvent event) {
//
//    }
//
//    @FXML
//    void setMusic(MouseEvent event) {
//
//    }
//// public void playAudio(ActionEvent event) {
////        beginTimer();
////        player.play();
////    }
////    public void stopAudio(ActionEvent event){
////        player.stop();
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
