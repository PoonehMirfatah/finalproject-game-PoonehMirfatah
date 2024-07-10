package com.example.gameproject;

import Controllers.MapController;
import Controllers.PlayerController;
import Controllers.SQL.SQLController;
import Controllers.SpellsController;
import Models.Map;
import Models.Position;
import Models.Raiders.*;
import Models.Spells.CoinSpell;
import Models.Spells.FreezeSpell;
import Models.Spells.HealthSpell;
import Models.Spells.LittleBoySpell;
import Models.Towers.*;
import Models.Wave;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
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
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.QuadCurveTo;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.gameproject.SettingPageController.setSound;

public class Map3Controller implements Initializable {
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
    private QuadCurve ps6;

    @FXML
    private QuadCurve ps7;


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
    private ImageView towerPoint4;

    @FXML
    private ImageView towerPoint5;

    @FXML
    private ImageView towerPoint6;

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


    ImageView point;
    String newPath;
    Path path = new Path();
    Path path2 = new Path();
    private boolean firstAttack = true;
    int waveIndex;
    boolean isFinished=false;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            SettingPageController.player.stop();
            SettingPageController.setSound("Music/gamemusic.wav");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        Map map1;
        try {
            SQLController.loadPlayerSpells(PlayerController.getPlayer().getID());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        setSpellCounts();
        towersBox.setVisible(false);
        ArrayList<Position> towersPosition = new ArrayList<>();
        Position p1 = new Position(towerPoint1.getX(), towerPoint1.getY());
        Position p2 = new Position(towerPoint2.getX(), towerPoint2.getY());
        Position p3 = new Position(towerPoint3.getX(), towerPoint3.getY());
        Position p4 = new Position(towerPoint4.getX(), towerPoint4.getY());
        Position p5 = new Position(towerPoint5.getX(), towerPoint5.getY());
        Position p6 = new Position(towerPoint6.getX(), towerPoint6.getY());

        Position end = new Position(0, 403);
        towersPosition.add(p1);
        towersPosition.add(p2);
        towersPosition.add(p3);
        towersPosition.add(p4);
        towersPosition.add(p5);
        towersPosition.add(p6);


        ArrayList<Wave> attackWaves = new ArrayList<>();
        SpeedyRaider shieldRaider1 = new SpeedyRaider();
        ShieldRaider shieldRaider2 = new ShieldRaider();
        WizardRaider shieldRaider3 = new WizardRaider();
        FlyerRaider shieldRaider4 = new FlyerRaider();
        SpeedyRaider shieldRaider5 = new SpeedyRaider();
        ShieldRaider shieldRaider6 = new ShieldRaider();
        WizardRaider shieldRaider7 = new WizardRaider();
        FlyerRaider shieldRaider8 = new FlyerRaider();
        SpeedyRaider shieldRaider9 = new SpeedyRaider();
        ShieldRaider shieldRaider10 = new ShieldRaider();

        Wave wave1 = new Wave(shieldRaider1, 3);
        Wave wave2 = new Wave(shieldRaider2, 4);
        Wave wave3 = new Wave(shieldRaider3, 6);
        Wave wave4 = new Wave(shieldRaider4, 8);
        Wave wave5 = new Wave(shieldRaider5, 8);
        Wave wave6 = new Wave(shieldRaider6, 8);
        Wave wave7 = new Wave(shieldRaider7, 10);
        Wave wave8 = new Wave(shieldRaider8, 10);
        Wave wave9 = new Wave(shieldRaider9, 10);
        Wave wave10 = new Wave(shieldRaider10, 10);

        attackWaves.add(wave1);
        attackWaves.add(wave2);
        attackWaves.add(wave3);
        attackWaves.add(wave4);
        attackWaves.add(wave5);
        attackWaves.add(wave6);
        attackWaves.add(wave7);
        attackWaves.add(wave8);
        attackWaves.add(wave9);
        attackWaves.add(wave10);


        Position DP1 = new Position(damagePoint1.getLayoutX(), damagePoint1.getLayoutY());
        Position DP2 = new Position(damagePoint2.getLayoutX(), damagePoint2.getLayoutY());
        Position DP3 = new Position(damagePoint3.getLayoutX(), damagePoint3.getLayoutY());
        Position DP4 = new Position(damagePoint4.getLayoutX(), damagePoint4.getLayoutY());

        map1 = new Map(towersPosition, path, end, attackWaves, 1000, 20);

        map1.getDamagePoints().add(DP1);
        map1.getDamagePoints().add(DP2);
        map1.getDamagePoints().add(DP3);
        map1.getDamagePoints().add(DP4);

        PlayerController.getPlayer().setCoins(1000);
        PlayerController.getPlayer().setHealth(20);
        MapController.setMap(map1);
        heartLB.setText(String.format("%s/20", PlayerController.getPlayer().getHealth()));
        coinsLB.setText(String.valueOf(PlayerController.getPlayer().getCoins()));
        waveLB.setText(String.format("Wave %s/10", map1.getWaveCounter()));
    }


    public void setSpellCounts() {
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
    void dropBomb(MouseEvent event) throws InterruptedException, URISyntaxException {
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
            PlayerController.getPlayer().setCoins(PlayerController.getPlayer().getCoins()+coinSpell.getCoinIncrease());
            setSound("Music/getCoins.mp3");
            coinsLB.setText(String.valueOf(PlayerController.getPlayer().getCoins()));
            setSpellCounts();
        }
    }

    @FXML
    void dropFreeze(MouseEvent event) throws URISyntaxException {
        FreezeSpell freezeSpell = new FreezeSpell();
        SpellsController.setSpell(freezeSpell);
        if (SpellsController.getInstance().drop()) {
            setSound("Music/freeze.mp3");
            MapController.getInstance().freezeTransitions();
            MapController.getInstance().freezeTimelines();
            setSpellCounts();
        }
    }


    @FXML
    void dropHealth(MouseEvent event) throws URISyntaxException {
        HealthSpell healthSpell = new HealthSpell();
        SpellsController.setSpell(healthSpell);
        if (SpellsController.getInstance().drop()) {
            PlayerController.getPlayer().setHealth(healthSpell.getHealthIncrease()+ PlayerController.getPlayer().getHealth());
            if (PlayerController.getPlayer().getHealth ()> 20) {
                PlayerController.getPlayer().setHealth (20);
            }
            setSound("Music/health2.mp3");
            heartLB.setText(String.format("%s/20", PlayerController.getPlayer().getHealth ()));
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
            case "towerPoint4":
                point = towerPoint4;
                break;
            case "towerPoint5":
                point = towerPoint5;
                break;
            case "towerPoint6":
                point = towerPoint6;
                break;
        }
        towersBox.setVisible(true);
        UpgradeBox.setVisible(false);
    }

    @FXML
    void buildTower(MouseEvent event) throws URISyntaxException {
        spellsBox.setVisible(true);
        Button clickedButton = (Button) event.getSource();
        if (clickedButton.getId() == null) {
            return;
        }
        Tower selectedTower1 = null;
        switch (clickedButton.getId()) {
            case "t1":
                selectedTower1 = new ArcherTower(100, 70, 200,1);
                if (checkCoins(selectedTower1)) {
                    return;
                }
                setTowerOnPosition("/Towers/1ArcherTower.png");
                break;
            case "t2":
                selectedTower1 = new Artillery(500, 125, 200,1);
                if (checkCoins(selectedTower1)) {
                    return;
                }
                setTowerOnPosition("/Towers/1Artillery.png");
                break;
            case "t3":
                selectedTower1 = new WizardTower(150, 100, 200,1);
                if (checkCoins(selectedTower1)) {
                    return;
                }
                setTowerOnPosition("/Towers/1WizardTower.png");
                break;
            case "t4":
                selectedTower1 = new AirTower(150, 100, 200,1);
                if (checkCoins(selectedTower1)) {
                    return;
                }
                setTowerOnPosition("/Towers/1ArmyPlace.png");
                break;
            default:
                return;
        }
        //assert selectedTower1 != null;
        setSound("Music/build.wav");
        MapController.getMap().getTowersList().put(point, selectedTower1);
        setSound("Music/costCoin.mp3");
        PlayerController.getPlayer().setCoins(PlayerController.getPlayer().getCoins()- selectedTower1.getBulidCost());
        coinsLB.setText(String.valueOf(PlayerController.getPlayer().getCoins()));
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
            if(newPath.equals(towerPath)){
                upgradBT.setVisible(false);
                upgradedTower.setVisible(false);
            }else{
                upgradBT.setVisible(true);
                upgradedTower.setVisible(true);
            }
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
            setSound("Music/getCoins.mp3");
            PlayerController.getPlayer().setCoins(PlayerController.getPlayer().getCoins()+ 70);
            coinsLB.setText(String.valueOf(PlayerController.getPlayer().getCoins()));
        }
    }

    private void initiateAttack() throws Exception {
        setPath();
        if(MapController.getInstance().checkWin(15)){
            waveLB.setText(String.format("Wave %s/10", MapController.getMap().getWaveCounter()));
        }else {
            startBT.setVisible(false);
        }
        waveIndex = MapController.getMap().getWaveCounter() - 1;
        Wave currentWave = MapController.getMap().getAttackWave().get(waveIndex);

        int i = 0;
        for (i = 0; i < currentWave.getRaiderCount(); i++) {
            if(isFinished){
                return;
            }
            int delay = i * 1000;
            VBox vBox=MapController.getInstance().addRaiderVbox(currentWave,i);

            PauseTransition pauseTransition = new PauseTransition(Duration.millis(delay));
            Raider currentRaider = currentWave.getRaiders().get(i);
            currentRaider.setvBox(vBox);
            MapController.getMap().getAliveRaiders().add(currentRaider);
            int index=i;
            int raiderHealth = currentRaider.getHealth();
            pauseTransition.setOnFinished(e -> {
                PathTransition pathTransition=null;
                if(index%2==0) {
                    pathTransition = setPathForNextRaider(path, currentRaider, vBox, raiderHealth);
                }else{
                    pathTransition = setPathForNextRaider(path2, currentRaider, vBox, raiderHealth);
                }
                PathTransition finalPathTransition = pathTransition;
                pathTransition.setOnFinished(event2 -> {
                    removeEndPathRaider(vBox, finalPathTransition);
                });
                pathTransition.play();
                MapController.getMap().getPathTransitions().add(pathTransition);
            });
            pauseTransition.play();
        }
    }
    public PathTransition setPathForNextRaider(Path path,Raider currentRaider,VBox vBox,int raiderHealth){
        PathTransition pathTransition = new PathTransition();
        currentRaider.setPathTransition(pathTransition);
        attackTimeLine(currentRaider, vBox, pathTransition, raiderHealth);
        pane.getChildren().add(vBox);
        double duration = 1d / currentRaider.getSpeed();
        pathTransition.setDuration(Duration.seconds(duration));
        pathTransition.setPath(path);
        pathTransition.setNode(vBox);
        pathTransition.setAutoReverse(false);
        return pathTransition;
    }

    public void removeEndPathRaider(VBox vBox,PathTransition pathTransition){
        pane.getChildren().remove(vBox);
        PlayerController.getPlayer().setHealth (PlayerController.getPlayer().getHealth ()-1);
        heartLB.setText(String.format("%s/20", PlayerController.getPlayer().getHealth ()));
        if (PlayerController.getPlayer().getHealth () == 0) {
            try {
                MapController.getInstance().checkLost();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        MapController.getMap().getPathTransitions().remove(pathTransition);
        if (MapController.getMap().getPathTransitions().isEmpty()&&PlayerController.getPlayer().getHealth()>0) {
            startNextAttack();
        }
    }
    public void attackTimeLine(Raider currentRaider, VBox vBox, PathTransition pathTransition, int health) {
        Timeline attackTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if(isFinished){
                return;
            }
            currentRaider.setHealth(health);
            for (ImageView point : MapController.getMap().getTowersList().keySet()) {
                Tower tower = MapController.getMap().getTowersList().get(point);
                double distance = Math.hypot(vBox.getTranslateX() - point.getLayoutX(), vBox.getTranslateY() - point.getLayoutY());
                if (distance <= tower.getRange() && !MapController.getMap().getActiveTowers().contains(point)) {
                    if (tower instanceof ArcherTower && (!(currentRaider instanceof WizardRaider))) {
                        MapController.getInstance().archerTowerAttack(tower,currentRaider,point, vBox,pane);
                    } else if (tower instanceof Artillery && (!(currentRaider instanceof FlyerRaider))) {
                        MapController.getInstance().artilleryTowerAttack(tower,currentRaider,point, vBox,pane);
                    } else if (tower instanceof WizardTower) {
                        MapController.getInstance().wizardTowerAttack(tower,currentRaider,point, vBox,pane);
                    }else if (tower instanceof AirTower && (currentRaider instanceof FlyerRaider)) {
                        MapController.getInstance().airTowerAttack(tower,currentRaider,point,vBox,pane);
                    }
                    if (currentRaider.getHealth() <= 0) {
                        removeRaider(currentRaider,vBox,pathTransition);
                        return;
                    }
                }
            }

        }));
        attackTimeline.setCycleCount(Timeline.INDEFINITE);
        attackTimeline.play();
    }

    public void removeRaider(Raider currentRaider,VBox vBox,PathTransition pathTransition) {
        pane.getChildren().remove(vBox);
        MapController.getMap().getAliveRaiders().remove(currentRaider);
        currentRaider.setDead(true);
        pathTransition.stop();
        MapController.getMap().getPathTransitions().remove(pathTransition);
        if (MapController.getMap().getPathTransitions().isEmpty()) {
            startNextAttack();
        }
        PlayerController.getPlayer().setCoins(currentRaider.getLoot() + PlayerController.getPlayer().getCoins());
        coinsLB.setText(String.valueOf(PlayerController.getPlayer().getCoins()));
    }

    public void setPath() {
        path.getElements().clear();
        path2.getElements().clear();

        double xStart = ps1.getStartX() + ps1.getLayoutX();
        double yStart = ps1.getStartY() + ps1.getLayoutY();
        path.getElements().add(new MoveTo(xStart, yStart));

        double xStart2 = ps5.getStartX() + ps5.getLayoutX();
        double yStart2 = ps5.getStartY() + ps5.getLayoutY();
        path2.getElements().add(new MoveTo(xStart2, yStart2));

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
        double yEnd3= ps3.getEndY() + ps3.getLayoutY();

        double xControl4 = ps4.getControlX() + ps4.getLayoutX();
        double yControl4 = ps4.getControlY() + ps4.getLayoutY();
        double xEnd4 = ps4.getEndX() + ps4.getLayoutX();
        double yEnd4 = ps4.getEndY() + ps4.getLayoutY();

        double xControl5 = ps5.getControlX() + ps5.getLayoutX();
        double yControl5 = ps5.getControlY() + ps5.getLayoutY();
        double xEnd5 = ps5.getEndX() + ps5.getLayoutX();
        double yEnd5 = ps5.getEndY() + ps5.getLayoutY();

        double xControl6 = ps6.getControlX() + ps6.getLayoutX();
        double yControl6 = ps6.getControlY() + ps6.getLayoutY();
        double xEnd6 = ps6.getEndX() + ps6.getLayoutX();
        double yEnd6 = ps6.getEndY() + ps6.getLayoutY();

        double xControl7 = ps7.getControlX() + ps7.getLayoutX();
        double yControl7 = ps7.getControlY() + ps7.getLayoutY();
        double xEnd7 = ps7.getEndX() + ps7.getLayoutX();
        double yEnd7= ps7.getEndY() + ps7.getLayoutY();

        path.getElements().add(new QuadCurveTo(xControl1, yControl1, xEnd1, yEnd1));
        path.getElements().add(new QuadCurveTo(xControl2, yControl2, xEnd2, yEnd2));
        path.getElements().add(new QuadCurveTo(xControl3, yControl3, xEnd3, yEnd3));
        path.getElements().add(new QuadCurveTo(xControl4, yControl4, xEnd4, yEnd4));

        path2.getElements().add(new QuadCurveTo(xControl5, yControl5, xEnd5, yEnd5));
        path2.getElements().add(new QuadCurveTo(xControl6, yControl6, xEnd6, yEnd6));
        path2.getElements().add(new QuadCurveTo(xControl7, yControl7, xEnd7, yEnd7));
        path2.getElements().add(new QuadCurveTo(xControl4, yControl4, xEnd4, yEnd4));

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
    void destroyTower(ActionEvent event) throws URISyntaxException {
        Image image = new Image(getClass().getResource("/Towers/Pointer.png").toExternalForm());
        point.setImage(image);
        point.setOnMouseClicked(
                this::showTowers
        );
        UpgradeBox.setVisible(false);
        setSound("Music/destroy.mp3");
        MapController.getMap().getTowersList().remove(point);
        setSound("Music/getCoins.mp3");
        PlayerController.getPlayer().setCoins(PlayerController.getPlayer().getCoins()+ Integer.parseInt(destroyLootLB.getText()) );
        coinsLB.setText(String.valueOf(PlayerController.getPlayer().getCoins()));
    }

    @FXML
    void upgradeTower(ActionEvent event) throws URISyntaxException {
        UpgradeBox.setVisible(false);
        Tower selectedTower = MapController.getInstance().getTower(newPath);
        if(MapController.getInstance().checkTowerLevelForUpgrade(selectedTower)){
            return;
        }
        if (checkCoins(selectedTower)) {
            return;
        } else {
            setSound("Music/costCoin.mp3");
            PlayerController.getPlayer().setCoins(PlayerController.getPlayer().getCoins()-selectedTower.getBulidCost()) ;
            setSound("Music/upgrade2.wav");
            coinsLB.setText(String.valueOf(PlayerController.getPlayer().getCoins()));
        }
        setTowerOnPosition(newPath);
        for (ImageView image : MapController.getMap().getTowersList().keySet()) {
            if (image == point) {
                MapController.getMap().getTowersList().replace(point, selectedTower);
            }
        }
    }

    public boolean checkCoins(Tower tower) {
        if (PlayerController.getPlayer().getCoins() < tower.getBulidCost()) {
            PageController.showAlert("Error", "You don't have enough coins to upgrade this tower!!", "", Alert.AlertType.ERROR);
            UpgradeBox.setVisible(false);
            towersBox.setVisible(false);
            return true;
        }
        return false;
    }

    public void quitMap(MouseEvent event) throws Exception {
        PlayerController.getInstance().updateSpells();
        SettingPageController.player.stop();
        SettingPageController.setSound("Music/gamemusic.mp3");
        isFinished=true;
        PageController.setstage(event,"HomePage.fxml");
    }

    public void closeTowersBox(MouseEvent event) {
        towersBox.setVisible(false);
        spellsBox.setVisible(true);
    }

    public void closeUpgradeBox(MouseEvent event) {
        UpgradeBox.setVisible(false);
        spellsBox.setVisible(true);
    }
}

