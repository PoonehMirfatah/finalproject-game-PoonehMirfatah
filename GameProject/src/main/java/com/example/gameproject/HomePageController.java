package com.example.gameproject;

import Controllers.PlayerController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    @FXML
    private Label diamondLB;

    @FXML
    private Label starLB;
    Event event;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
                PageController.setstage(event, "map1.fxml");
                break;
            case "map2":

            case "map3":

            case "map4":
                PageController.setstage(event, "map4.fxml");
                break;

        }

    }


}

