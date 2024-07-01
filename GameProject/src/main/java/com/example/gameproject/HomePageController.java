package com.example.gameproject;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class HomePageController {

    @FXML
    private Label diamondLB;

    @FXML
    private Label starLB;

    @FXML
    void goToSetting(MouseEvent event) {

    }

    @FXML
    void goToShop(MouseEvent event) {

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


        }

    }
}

