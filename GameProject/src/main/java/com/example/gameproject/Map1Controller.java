package com.example.gameproject;
import Controllers.MapController;
import Controllers.PlayerController;
import Controllers.SpellsController;
import Models.Map;
import Models.Position;
import Models.Raiders.Raider;
import Models.Raiders.ShieldRaider;
import Models.Spells.*;
import Models.Towers.*;
import Models.Wave;
import Controllers.SQL.SQLController;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class Map1Controller implements Initializable {
    @FXML
    private GridPane UpgradeBox;

    @FXML
    private Label coinsLB;

    @FXML
    private Label destroyLootLB;

    @FXML
    private Label heartLB;

    @FXML
    private AnchorPane pane;

    @FXML
    private QuadCurve ps1;

    @FXML
    private QuadCurve ps2;

    @FXML
    private QuadCurve ps3;

    @FXML
    private QuadCurve ps4;

    @FXML
    private QuadCurve ps5;

    @FXML
    private Button startBT;

    @FXML
    private Button t1;

    @FXML
    private Button t2;


    @FXML
    private Button t3;

    @FXML
    private Button t4;
    @FXML
    private Button upgradBT;

    @FXML
    private ImageView towerPoint1;

    @FXML
    private ImageView towerPoint2;

    @FXML
    private ImageView towerPoint3;

    @FXML
    private GridPane towersBox;

    @FXML
    private Label upgradedCostLB;

    @FXML
    private ImageView upgradedTower;

    @FXML
    private Label waveLB;
    @FXML
    private Label coinsCountLB;
    @FXML
    private Label freezeCountLB;
    @FXML
    private Label heartCountLB;
    @FXML
    private Label littleBoyCountLB;
    @FXML
    private AnchorPane spellsBox;
    @FXML
    private ImageView damagePoint1;

    @FXML
    private ImageView damagePoint2;

    @FXML
    private ImageView damagePoint3;

    @FXML
    private ImageView damagePoint4;


    int playerCoins;
    int playerHealth;
    ImageView point;
    String newPath;
    Path path = new Path();
    private boolean firstAttack = true;
    int waveIndex;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Map map1;
        try {
            SQLController.loadPlayerSpells(PlayerController.getInstance().player.getID());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        setSpellCounts();
        towersBox.setVisible(false);
        ArrayList<Position> towersPosition = new ArrayList<>();
        Position p1 = new Position(towerPoint1.getX(), towerPoint1.getY());
        Position p2 = new Position(towerPoint2.getX(), towerPoint2.getY());
        Position p3 = new Position(towerPoint3.getX(), towerPoint3.getY());
        Position end = new Position(409, 634);
        towersPosition.add(p1);
        towersPosition.add(p2);
        towersPosition.add(p3);

        ArrayList<Wave> attackWaves = new ArrayList<>();
        ShieldRaider shieldRaider1 = new ShieldRaider();
        ShieldRaider shieldRaider2 = new ShieldRaider();
        ShieldRaider shieldRaider3 = new ShieldRaider();
        ShieldRaider shieldRaider4 = new ShieldRaider();
        ShieldRaider shieldRaider5 = new ShieldRaider();
        Wave wave1 = new Wave(shieldRaider1, 3);
        Wave wave2 = new Wave(shieldRaider2, 6);
        Wave wave3 = new Wave(shieldRaider3, 8);
        Wave wave4 = new Wave(shieldRaider4, 10);
        Wave wave5 = new Wave(shieldRaider5, 13);

        attackWaves.add(wave1);
        attackWaves.add(wave2);
        attackWaves.add(wave3);
        attackWaves.add(wave4);
        attackWaves.add(wave5);

        Position DP1 = new Position(damagePoint1.getLayoutX(), damagePoint1.getLayoutY());
        Position DP2 = new Position(damagePoint2.getLayoutX(), damagePoint2.getLayoutY());
        Position DP3 = new Position(damagePoint3.getLayoutX(), damagePoint3.getLayoutY());
        Position DP4 = new Position(damagePoint4.getLayoutX(), damagePoint4.getLayoutY());

        map1 = new Map(towersPosition, path, end, attackWaves, 300, 20);

        map1.getDamagePoints().add(DP1);
        map1.getDamagePoints().add(DP2);
        map1.getDamagePoints().add(DP3);
        map1.getDamagePoints().add(DP4);



        playerCoins = 1000;
        playerHealth = 20;
        MapController.setMap(map1);
        heartLB.setText(String.format("%s/20", playerHealth));
        coinsLB.setText(String.valueOf(playerCoins));
        waveLB.setText(String.format("Wave %s/5", map1.getWaveCounter()));
    }


    public void setSpellCounts() {
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
    void dropBomb(MouseEvent event) throws InterruptedException {
        LittleBoySpell bombSpell = new LittleBoySpell();
        SpellsController.setSpell(bombSpell);

        if (SpellsController.getInstance().drop()) {
            MapController.getInstance().bombAttacks(pane,waveIndex);
            setSpellCounts();
        }
    }


    @FXML
    void dropCoins(MouseEvent event) throws Exception {
        CoinSpell coinSpell = new CoinSpell();
        SpellsController.setSpell(coinSpell);
        if (SpellsController.getInstance().drop()) {
            playerCoins += coinSpell.getCoinIncrease();
            coinsLB.setText(String.valueOf(playerCoins));
            setSpellCounts();
        }
        //PlayerController.getInstance().updateSpells(); update after game finish
    }

    @FXML
    void dropFreeze(MouseEvent event) {
        FreezeSpell freezeSpell = new FreezeSpell();
        SpellsController.setSpell(freezeSpell);
        if (SpellsController.getInstance().drop()) {
            MapController.getInstance().freezeTransitions();
            MapController.getInstance().freezeTimelines();
            setSpellCounts();
        }
    }


    @FXML
    void dropHealth(MouseEvent event) {
        HealthSpell healthSpell = new HealthSpell();
        SpellsController.setSpell(healthSpell);
        if (SpellsController.getInstance().drop()) {
            playerHealth += healthSpell.getHealthIncrease();
            if (playerHealth > 20) {
                playerHealth = 20;
            }
            heartLB.setText(String.valueOf(playerHealth));
            setSpellCounts();
        }

    }

    @FXML
    void showTowers(MouseEvent event) {
        spellsBox.setVisible(false);
        ImageView clickedImage = (ImageView) event.getSource();
        String towerID = clickedImage.getId();
        switch (towerID) {
            case "towerPoint1":
                point = towerPoint1;
                break;
            case "towerPoint2":
                point = towerPoint2;
                break;
            case "towerPoint3":
                point = towerPoint3;
                break;
        }
        towersBox.setVisible(true);
    }

    @FXML
    void buildTower(MouseEvent event) {
        spellsBox.setVisible(true);
        Button clickedButton = (Button) event.getSource();
        if (clickedButton.getId() == null) {
            return;
        }
        Tower selectedTower1 = null;
        switch (clickedButton.getId()) {
            case "t1":
                selectedTower1 = new ArcherTower(100, 70, 200);
                if (checkCoins(selectedTower1)) {
                    return;
                }
                setTowerOnPosition("/Towers/1ArcherTower.png");
                break;
            case "t2":
                selectedTower1 = new Artillery(400, 125, 200);
                if (checkCoins(selectedTower1)) {
                    return;
                }
                setTowerOnPosition("/Towers/1Artillery.png");
                break;
            case "t3":
                selectedTower1 = new WizardTower(300, 100, 200);
                if (checkCoins(selectedTower1)) {
                    return;
                }
                setTowerOnPosition("/Towers/1WizardTower.png");
                break;
            case "t4":
                selectedTower1 = new AirTower(200, 100, 200);
                if (checkCoins(selectedTower1)) {
                    return;
                }
                setTowerOnPosition("/Towers/1ArmyPlace.png");
                break;
            default:
                return;
        }
        //assert selectedTower1 != null;
        MapController.getMap().getTowersList().put(point, selectedTower1);
        playerCoins -= selectedTower1.getBulidCost();
        coinsLB.setText(String.valueOf(playerCoins));
        towersBox.setVisible(false);
    }

    public void setTowerOnPosition(String towerPath) {
        Image image = new Image(getClass().getResource(towerPath).toExternalForm());
        point.setImage(image);
        point.setOnMouseClicked(event -> {
            showTowers(event);
            towersBox.setVisible(false);
            UpgradeBox.setVisible(true);
            newPath=MapController.getInstance().getUpdateTowerPath(towerPath);
            Tower newTower = MapController.getInstance().getTower(newPath);
            upgradedCostLB.setText(String.valueOf(newTower.getBulidCost()));
            Tower lastTower = MapController.getInstance().getTower(towerPath);
            destroyLootLB.setText(String.valueOf(lastTower.getBulidCost() / 2));
            Image upgradedImage = new Image(getClass().getResource(newPath).toExternalForm());
            upgradedTower.setImage(upgradedImage);
        });
    }

    @FXML
    void startAttack(MouseEvent event) throws Exception {
        if (firstAttack) {
            firstAttack = false;
            initiateAttack();
        } else {
            initiateAttack();
            playerCoins += 70;
            coinsLB.setText(String.valueOf(playerCoins));
        }
    }

    private void initiateAttack() throws Exception {
        setPath();
        if(MapController.getInstance().checkWin()){
            waveLB.setText(String.format("Wave %s/5", MapController.getMap().getWaveCounter()));
        }else {
            startBT.setVisible(false);
        }
        waveIndex = MapController.getMap().getWaveCounter() - 1;
        Wave currentWave = MapController.getMap().getAttackWave().get(waveIndex);

        int i = 0;
        for (i = 0; i < currentWave.getRaiderCount(); i++) {
            int delay = i * 1000;
            VBox vBox=MapController.getInstance().addRaiderVbox(currentWave,i);

            PauseTransition pauseTransition = new PauseTransition(Duration.millis(delay));
            Raider currentRaider = currentWave.getRaiders().get(i);
            currentRaider.setvBox(vBox);
            MapController.getMap().getAliveRaiders().add(currentRaider);
            int raiderHealth = currentRaider.getHealth();

            pauseTransition.setOnFinished(e -> {
                PathTransition pathTransition = new PathTransition();
                currentRaider.setPathTransition(pathTransition);
                attackTimeLine(currentRaider, vBox, pathTransition, raiderHealth);
                pane.getChildren().add(vBox);
                double duration = 1d / currentRaider.getSpeed();
                pathTransition.setDuration(Duration.seconds(duration));
                pathTransition.setPath(path);
                pathTransition.setNode(vBox);
                pathTransition.setAutoReverse(false);

                pathTransition.setOnFinished(event2 -> {
                    pane.getChildren().remove(vBox);
                    playerHealth -= 1;
                    heartLB.setText(String.format("%s/20", playerHealth));
                    if (playerHealth == 0) {
                        try {
                            MapController.getInstance().checkLost();
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    MapController.getMap().getPathTransitions().remove(pathTransition);
                    if (MapController.getMap().getPathTransitions().isEmpty()) {
                        startNextAttack();
                    }
                });
                pathTransition.play();
                MapController.getMap().getPathTransitions().add(pathTransition);
            });
            pauseTransition.play();
        }
    }

    public void attackTimeLine(Raider currentRaider, VBox vBox, PathTransition pathTransition, int health) {
        Timeline attackTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            currentRaider.setHealth(health);
            for (ImageView point : MapController.getMap().getTowersList().keySet()) {
                Tower tower = MapController.getMap().getTowersList().get(point);
                double distance = Math.hypot(vBox.getTranslateX() - point.getLayoutX(), vBox.getTranslateY() - point.getLayoutY());
                if (distance <= tower.getRange() && !MapController.getMap().getActiveTowers().contains(point)) {
                    if (tower instanceof ArcherTower) {
                        MapController.getMap().getActiveTowers().add(point);
                        MapController.getInstance().archerTowerAttack(point, vBox,pane);
                        if (currentRaider instanceof ShieldRaider) {
                            currentRaider.setHealth(currentRaider.getHealth() - tower.getDestroyPower() / 2);
                        } else {
                            currentRaider.setHealth(currentRaider.getHealth() - tower.getDestroyPower());
                        }
                    } else if (tower instanceof Artillery) {
                        MapController.getMap().getActiveTowers().add(point);
                        MapController.getInstance().artilleryTowerAttack(point, vBox,pane);
                        //List<Raider>nears=getNearbyRaiders(vBox,50);
                        //System.out.println(nears.size());
                        //List<Raider> nearRaiders = getNearbyRaiders(vBox, 100);
                        //checkHealthTimeLine(nearRaiders);
                        currentRaider.setHealth(currentRaider.getHealth() - tower.getDestroyPower());

                    } else if (tower instanceof WizardTower) {
                        MapController.getMap().getActiveTowers().add(point);
                        MapController.getInstance().wizardTowerAttack(point, vBox,pane);
                        currentRaider.setHealth(currentRaider.getHealth() - tower.getDestroyPower());
                        if (currentRaider instanceof ShieldRaider && currentRaider.getHealth() <= 100) {
                            vBox.getChildren().get(1).setVisible(true);
                        }
                    }
                    if (currentRaider.getHealth() <= 0) {
                        pane.getChildren().remove(vBox);
                        MapController.getMap().getAliveRaiders().remove(currentRaider);
                        currentRaider.setDead(true);
                        playerCoins += currentRaider.getLoot();
                        coinsLB.setText(String.valueOf(playerCoins));
                        pathTransition.stop();
                        MapController.getMap().getPathTransitions().remove(pathTransition);
                        if (MapController.getMap().getPathTransitions().isEmpty()) {
                            startNextAttack();
                        }
                        return;
                    }
                }
            }

        }));
        attackTimeline.setCycleCount(Timeline.INDEFINITE);
        attackTimeline.play();
    }

    public void setPath() {
        path.getElements().clear();

        double xStart = ps1.getStartX() + ps1.getLayoutX();
        double yStart = ps1.getStartY() + ps1.getLayoutY();
        path.getElements().add(new MoveTo(xStart, yStart));

        double xControl1 = ps1.getControlX() + ps1.getLayoutX();
        double yControl1 = ps1.getControlY() + ps1.getLayoutY();
        double xEnd1 = ps1.getEndX() + ps1.getLayoutX();
        double yEnd1 = ps1.getEndY() + ps1.getLayoutY();
        double xControl2 = ps2.getControlX() + ps2.getLayoutX();
        double yControl2 = ps2.getControlY() + ps2.getLayoutY();
        double xEnd2 = ps2.getEndX() + ps2.getLayoutX();
        double yEnd2 = ps2.getEndY() + ps2.getLayoutY();
        double xControl3 = ps3.getControlX() + ps3.getLayoutX();
        double yControl3 = ps3.getControlY() + ps3.getLayoutY();
        double xEnd3 = ps3.getEndX() + ps3.getLayoutX();
        double yEnd3 = ps3.getEndY() + ps3.getLayoutY();
        double xControl4 = ps4.getControlX() + ps4.getLayoutX();
        double yControl4 = ps4.getControlY() + ps4.getLayoutY();
        double xEnd4 = ps4.getEndX() + ps4.getLayoutX();
        double yEnd4 = ps4.getEndY() + ps4.getLayoutY();

        path.getElements().add(new QuadCurveTo(xControl1, yControl1, xEnd1, yEnd1));
        path.getElements().add(new QuadCurveTo(xControl2, yControl2, xEnd2, yEnd2));
        path.getElements().add(new QuadCurveTo(xControl3, yControl3, xEnd3, yEnd3));
        path.getElements().add(new QuadCurveTo(xControl4, yControl4, xEnd4, yEnd4));

    }

    private void startNextAttack() {
        PauseTransition nextWavePause = new PauseTransition(Duration.seconds(5));
        nextWavePause.setOnFinished(e -> {
            try {
                initiateAttack();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        nextWavePause.play();
    }


    @FXML
    void destroyTower(ActionEvent event) {
        Image image = new Image(getClass().getResource("/Towers/Pointer.png").toExternalForm());
        point.setImage(image);
        point.setOnMouseClicked(
                this::showTowers
        );
        UpgradeBox.setVisible(false);
        MapController.getMap().getTowersList().remove(point);
        playerCoins += Integer.parseInt(destroyLootLB.getText());
        coinsLB.setText(String.valueOf(playerCoins));
    }

    @FXML
    void upgradeTower(ActionEvent event) {
        UpgradeBox.setVisible(false);
        Tower selectedTower = MapController.getInstance().getTower(newPath);
        if (checkCoins(selectedTower)) {
            return;
        } else {
            playerCoins -= selectedTower.getBulidCost();
            coinsLB.setText(String.valueOf(playerCoins));
        }
        setTowerOnPosition(newPath);
        for (ImageView image : MapController.getMap().getTowersList().keySet()) {
            if (image == point) {
                MapController.getMap().getTowersList().replace(point, selectedTower);
            }
        }
    }

    public boolean checkCoins(Tower tower) {
        if (playerCoins < tower.getBulidCost()) {
            PageController.showAlert("Error", "You don't have enough coins to upgrade this tower!!", "", Alert.AlertType.ERROR);
            UpgradeBox.setVisible(false);
            towersBox.setVisible(false);
            return true;
        }
        return false;
    }
}
