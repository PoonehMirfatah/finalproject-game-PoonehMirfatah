package com.example.gameproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static com.example.gameproject.SettingPageController.player;
import static com.example.gameproject.SettingPageController.setSound;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
       setRoot(stage,"FirstPage.fxml",850,600);
    }

    public static void setRoot(Stage stage,String fxml,int width,int height) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setTitle(fxml);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}