package com.example.gameproject;

import Controllers.PlayerController;
import Controllers.SpellsController;
import Models.Spells.*;
import Controllers.SQL.SQLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ShopPageController implements Initializable {
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
    private Label littleBoyCountLB;

    @FXML
    private ImageView spellBoard;
    String spellID;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            SQLController.loadPlayerSpells(PlayerController.getPlayer().getID());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        updateLabels();
    }


    public void updateLabels(){
        diamondsLB.setText(String.valueOf(PlayerController.getPlayer().getDiamonds()));
        for (String spellName : PlayerController.getPlayer().getBackPack().keySet()) {
            int count = PlayerController.getPlayer().getBackPack().get(spellName);
            switch (spellName) {
                case "Health":
                    heartCountLB.setText(String.valueOf(count));
                    break;
                case "Freeze":
                    freezeCountLB.setText(String.valueOf(count));
                    break;
                case "Coins":
                    coinsCountLB.setText(String.valueOf(count));
                    break;
                case "LittleBoy":
                    littleBoyCountLB.setText(String.valueOf(count));
                    break;
            }
        }
    }
    @FXML
    void buy(MouseEvent event) throws IOException {
        Spell selectedSpell = null;
        switch (spellID) {
            case "heart":
                selectedSpell = new HealthSpell();
                break;
            case "freeze":
                selectedSpell = new FreezeSpell();
                break;
            case "coins":
                selectedSpell = new CoinSpell();
                break;
            case "littleBoy":
                selectedSpell = new LittleBoySpell();
                break;
            default:
                break;
        }
        assert selectedSpell != null;
        if (selectedSpell.getPrice() > PlayerController.getPlayer().getDiamonds()) {
            PageController.showAlert("Eror", "Your Diamonds Are Not Enough For Buying This Spell", "", Alert.AlertType.ERROR);
            return;
        }
        SpellsController.putSpellInBackPack(selectedSpell);
        updateLabels();
    }

    @FXML
    void showSpell(MouseEvent event) {
        String imagePath = null;
        Label clickedLabel = (Label) event.getSource();
        spellID = clickedLabel.getId();
        switch (spellID) {
            case "heart":
                imagePath = "/Shop/HeartBox.jpg";
                break;
            case "freeze":
                imagePath = "/Shop/Freeze.jpg";
                break;
            case "coins":
                imagePath = "/Shop/GoldBag.jpg";
                break;
            case "littleBoy":
                imagePath = "/Shop/littleBoy.jpg";
                break;
            default:
                break;
        }
        assert imagePath != null;
        Image image = new Image(getClass().getResource(imagePath).toExternalForm());
        spellBoard.setImage(image);
    }

    @FXML
    void backToHome(MouseEvent event) throws Exception {
        PlayerController.getInstance().updateSpells();
        PageController.setstage(event,"HomePage.fxml");
    }
}
