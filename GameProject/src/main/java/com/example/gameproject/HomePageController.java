package com.example.gameproject;

import Controllers.MapController;
import Controllers.PlayerController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.gameproject.SettingPageController.player;
import static com.example.gameproject.SettingPageController.setSound;

public class HomePageController implements Initializable {
    @FXML
    private Label diamondLB;

    @FXML
    private Label starLB;

    @FXML
    private ImageView map1;

    @FXML
    private ImageView map2;

    @FXML
    private ImageView map3;

    @FXML
    private ImageView map4;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image(getClass().getResource("/Photos/flag.png").toExternalForm());
        switch (PlayerController.getPlayer().getLevel()){
            case 1:
                map2.setImage(image);
                map3.setImage(image);
                map4.setImage(image);
                break;
            case 2:
                map3.setImage(image);
                map4.setImage(image);
                break;
            case 3:
                map4.setImage(image);
                break;
        }
        if(SettingPageController.player==null){
            try {
                SettingPageController.setSound("Music/startGame.mp3");
                player.setCycleCount(MediaPlayer.INDEFINITE);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }else {
            player.play();
        }
        String diamonds=String.valueOf(PlayerController.getPlayer().getDiamonds());
        diamondLB.setText(diamonds);
        String level=String.valueOf(PlayerController.getPlayer().getLevel());
        starLB.setText(level+" / 4 ");
    }

    @FXML
    void goToSetting(MouseEvent event) throws IOException {
        PageController.setstage(event,"SettingPage.fxml");
    }

    @FXML
    void goToShop(MouseEvent event) throws IOException {
        PageController.setstage(event,"ShopPage.fxml");
    }


    @FXML
    void showMap(MouseEvent event) throws IOException {
        ImageView clickedImageView = (ImageView) event.getSource();
        switch (clickedImageView.getId()) {
            case "map1":
                PageController.setstage(event, "Map1.fxml");
                break;
            case "map2":
                PageController.setstage(event, "Map2.fxml");
                break;
            case "map3":
                PageController.setstage(event, "Map3.fxml");
                break;
            case "map4":
                PageController.setstage(event, "Map4.fxml");
                break;

        }

    }

    public void backToFirstPage(MouseEvent event) throws IOException {
        PageController.setstage(event,"FirstPage.fxml");
    }

}

