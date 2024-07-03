package com.example.gameproject;

import Controllers.PlayerController;
import Models.Spells.CoinSpell;
import Models.Spells.FreezeSpell;
import Models.Spells.HealthSpell;
import Models.Spells.LittleBoySpell;
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
        diamondsLB.setText(String.valueOf(PlayerController.getInstance().player.getDiamonds()));
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
        System.out.println(spellID);
        switch (spellID){
            case "heart":
                HealthSpell healthSpell=new HealthSpell("health",350,5);

                break;
            case "freeze":
                FreezeSpell freezeSpell=new FreezeSpell("freeze",50,5);
                break;
            case "coins":
                CoinSpell coinsSpell=new CoinSpell("coins",850,200);
                break;
            case "littleBoy":
                LittleBoySpell littleBoySpell=new LittleBoySpell("littleBoy",999);
                break;
        }
    }

    @FXML
    void showSpell(MouseEvent event) {
        String imagePath=null;
        Label clickedButton = (Label) event.getSource();
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
