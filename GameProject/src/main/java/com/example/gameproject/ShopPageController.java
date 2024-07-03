package com.example.gameproject;

import Controllers.PlayerController;
import Models.Spells.*;
import com.example.gameproject.SQL.SQLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.IDN;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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
            SQLController.loadPlayerSpells(PlayerController.getInstance().player.getID());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        updateLabels();
    }


    public void updateLabels(){
        diamondsLB.setText(String.valueOf(PlayerController.getInstance().player.getDiamonds()));
        for (String spellName : PlayerController.getInstance().player.getBackPack().keySet()) {
            int count = PlayerController.getInstance().player.getBackPack().get(spellName);
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
        AbstractSpell selectedSpell = null;
        switch (spellID) {
            case "heart":
                selectedSpell = new HealthSpell("Health", 350, 5);
                break;
            case "freeze":
                selectedSpell = new FreezeSpell("Freeze", 50, 5);
                break;
            case "coins":
                selectedSpell = new CoinSpell("Coins", 850, 200);
                break;
            case "littleBoy":
                selectedSpell = new LittleBoySpell("LittleBoy", 999);
                break;
            default:
                break;
        }
        assert selectedSpell != null;
        if (selectedSpell.getPrice() > PlayerController.getInstance().player.getDiamonds()) {
            PageController.showAlert("Eror", "Your Diamonds Are Not Enough For Buying This Spell", "", Alert.AlertType.ERROR);
            return;
        }
        if (PlayerController.getInstance().player.getBackPack().containsKey(selectedSpell.getName())) {
            int count = (int) PlayerController.getInstance().player.getBackPack().get(selectedSpell.getName()) + 1;
            PlayerController.getInstance().player.getBackPack().put(selectedSpell.getName(), count);
        } else {
            PlayerController.getInstance().player.getBackPack().put(selectedSpell.getName(), 1);
        }
        int primaryDiamonds = PlayerController.getInstance().player.getDiamonds();
        PlayerController.getInstance().player.setDiamonds(primaryDiamonds - selectedSpell.getPrice());
        updateLabels();
    }

    @FXML
    void showSpell(MouseEvent event) {
        String imagePath = null;
        Label clickedButton = (Label) event.getSource();
        spellID = clickedButton.getId();
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
        SQLController.deletePlayerSpells(PlayerController.getInstance().player.getID());
        for (String spellName : PlayerController.getInstance().player.getBackPack().keySet()) {
            int count = PlayerController.getInstance().player.getBackPack().get(spellName);
            SQLController.insertSpell(spellName, count);
        }
        SQLController.updatePlayer(PlayerController.getInstance().player.getID());
        PageController.setstage(event,"HomePage.fxml");
    }
}
