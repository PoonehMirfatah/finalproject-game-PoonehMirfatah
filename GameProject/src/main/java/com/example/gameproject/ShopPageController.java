package com.example.gameproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ShopPageController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private Label coins;

    @FXML
    private Label coinsCountLB;

    @FXML
    private Label diamondsLB;

    @FXML
    private Label freeze;

    @FXML
    private Label freezeCountLB;

    @FXML
    private Label heart;

    @FXML
    private Label heartCountLB;

    @FXML
    private Label littleBoy;

    @FXML
    private Label littleBoyLB;

    @FXML
    private ImageView spellBoard;
    String spellID;

    @FXML
    void backToHome(MouseEvent event) {

    }

    @FXML
    void buy(MouseEvent event) {
        switch (spellID){
            case "heart":
                //new & add to player
                break;
            case "freeze":

            case "coins":

                break;
            case "littleBoy":

                break;
        }
    }

    @FXML
    void showSpell(MouseEvent event) {
        String imagePath=null;
        Button clickedButton = (Button) event.getSource();
        spellID= clickedButton.getId();
        switch (spellID){
            case "heart":
                imagePath="/Shop/HeartBox.jpg";
                break;
            case "freeze":
                imagePath="/Shop/Freeze.jpg";
                break;
            case "coins":
                imagePath="/Shop/GoldBag.jpg";
                break;
            case "littleBoy":
                imagePath="/Shop/littleBoy.jpg";
                break;
        }
        Image image=new Image(getClass().getResource(imagePath).toExternalForm());
        spellBoard.setImage(image);
    }

}
