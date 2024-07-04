package com.example.gameproject;
import Models.Towers.*;
import Models.Map;
import Models.Position;
import Models.Raiders.ShieldRaider;
import Models.Towers.ArcherTower;
import Models.Towers.Artillery;
import Models.Towers.Tower;
import Models.Towers.WizardTower;
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
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import Controllers.PlayerController.*;

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
    private Button t21;

    @FXML
    private Button t211;

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

    String towerID;
    Position clickedPosition;
    int coins;
    int health;
    ImageView point;
    String newPath;
    Path path = new Path();
    List<Timeline> timelines = new ArrayList<>();
    Map map1;
    private boolean firstAttack = true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        ShieldRaider shieldRaider = new ShieldRaider();
        Wave wave1 = new Wave(shieldRaider, 3);
        Wave wave2 = new Wave(shieldRaider, 6);
        Wave wave3 = new Wave(shieldRaider, 8);
        Wave wave4 = new Wave(shieldRaider, 10);
        Wave wave5 = new Wave(shieldRaider, 13);

        attackWaves.add(wave1);
        attackWaves.add(wave2);
        attackWaves.add(wave3);
        attackWaves.add(wave4);
        attackWaves.add(wave5);
        map1 = new Map(towersPosition, path, end, attackWaves, 300, 20);
        coins = 300;
        health = 20;
        heartLB.setText(String.format("%s/20", health));
        coinsLB.setText(String.valueOf(300));
        waveLB.setText(String.format("Wave %s/5", map1.getWaveCounter()));
    }


    @FXML
    void showTowers(MouseEvent event) {
        ImageView clickedImage = (ImageView) event.getSource();
        towerID = clickedImage.getId();
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
        Button clickedButton = (Button) event.getSource();
        if (clickedButton.getId() == null) {
            return;
        }
        Tower selectedTower1 = null;
        switch (clickedButton.getId()) {
            case "t1":
                selectedTower1 = new ArcherTower(100, 70, 20);
                if(checkCoins(selectedTower1)){
                    return;
                }
                setTowerOnPosition("/Towers/1ArcherTower.png");
                break;
            case "t2":
                selectedTower1 = new Artillery(200, 125, 30);
                if(checkCoins(selectedTower1)){
                    return;
                }
                setTowerOnPosition("/Towers/1Artillery.png");
                break;
            case "t3":

                selectedTower1 = new WizardTower(300, 100, 30);
                if(checkCoins(selectedTower1)){
                    return;
                }
                setTowerOnPosition("/Towers/WizardTower.png");
                break;
            case "t4":
                setTowerOnPosition("/Towers/ArmyPlace.png");

                break;
            default:
                return;
        }
        assert selectedTower1 != null;
        System.out.println(selectedTower1.getBulidCost());
        map1.getTowersList().put(point, selectedTower1);
        coins -= selectedTower1.getBulidCost();
        coinsLB.setText(String.valueOf(coins));
        towersBox.setVisible(false);
    }

    public void setTowerOnPosition(String towerPath) {
        Image image = new Image(getClass().getResource(towerPath).toExternalForm());
        point.setImage(image);
        point.setOnMouseClicked(event -> {
            showTowers(event);
            towersBox.setVisible(false);
            UpgradeBox.setVisible(true);
            int level = 0;
            char digitChar = 0;
            for (Character chr : towerPath.toCharArray()) {
                if (Character.isDigit(chr)) {
                    digitChar = chr;
                    level = Integer.parseInt(String.valueOf(digitChar));
                }
            }
            level += 1;
            String newLevel = String.valueOf(level);
            newPath = towerPath.replaceAll("\\d", newLevel);
            System.out.println(newPath);
            Tower tower=getTower(newPath);
            upgradedCostLB.setText(String.valueOf(tower.getBulidCost()));
            destroyLootLB.setText(String.valueOf(tower.getBulidCost()/2));
            Image upgradedImage = new Image(getClass().getResource(newPath).toExternalForm());
            upgradedTower.setImage(upgradedImage);
        });
    }

    private void timeline(VBox vBox, ArrayList<Image> heroImages) {
        vBox.setTranslateX(vBox.getTranslateX() - 100);
        vBox.setTranslateY(vBox.getTranslateY() - 50);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            ImageView imageView = (ImageView) vBox.getChildren().get(0);
            int index = heroImages.indexOf(imageView.getImage()) + 1;

            if (index >= heroImages.size()) {
                index = 0;
            }

            ImageView walkKnight = new ImageView(heroImages.get(index));
            walkKnight.setFitHeight(50);
            walkKnight.setPreserveRatio(true);
            vBox.getChildren().setAll(walkKnight);
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        timelines.add(timeline);
    }

    @FXML
    void startAttack(MouseEvent event) throws IOException {
        if (firstAttack) {
            firstAttack = false;
            initiateAttack();
        } else {
            initiateAttack();
            coins += 70;
            coinsLB.setText(String.valueOf(coins));
        }
    }

    private void initiateAttack() throws IOException {
        path.getElements().clear();
        if (map1.getWaveCounter() < 5) {
            map1.setWaveCounter(map1.getWaveCounter() + 1);
            waveLB.setText(String.format("Wave %s/5", map1.getWaveCounter()));
        } else {
            startBT.setVisible(false);
        }
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

        int waveIndex = map1.getWaveCounter() - 1;
        Wave currentWave = map1.getAttackWave().get(waveIndex);

        List<PathTransition> pathTransitions = new ArrayList<>();

        for (int i = 0; i < currentWave.getRaiderCount(); i++) {
            int delay = i * 1000;

            VBox vBox = new VBox();
            ArrayList<Image> heroImages = currentWave.getRaiders().getHeroImages();
            Image image1 = heroImages.get(0);
            ImageView walkKnight = new ImageView(image1);
            walkKnight.setFitHeight(50);
            walkKnight.setPreserveRatio(true);
            vBox.getChildren().add(walkKnight);
            timeline(vBox, heroImages);

            PauseTransition pauseTransition = new PauseTransition(Duration.millis(delay));
            pauseTransition.setOnFinished(e -> {
                pane.getChildren().add(vBox);
                PathTransition pathTransition = new PathTransition();
                pathTransition.setDuration(Duration.seconds(17));
                pathTransition.setPath(path);
                pathTransition.setNode(vBox);
                pathTransition.setAutoReverse(false);
                pathTransition.setOnFinished(event2 -> {
                    pane.getChildren().remove(vBox);
                    health -= 1;
                    heartLB.setText(String.format("%s/20", health));
                    if (health == 0) {
                        PageController.showAlert("Finished", "GAME OVER", " ", Alert.AlertType.INFORMATION);
                    }
                    pathTransitions.remove(pathTransition);
                    if (pathTransitions.isEmpty()) {
                        startNextAttack();
                    }
                });
                pathTransition.play();
                pathTransitions.add(pathTransition);
            });
            pauseTransition.play();
        }
    }

    private void startNextAttack() {
        PauseTransition nextWavePause = new PauseTransition(Duration.seconds(5));
        nextWavePause.setOnFinished(e -> {
            try {
                initiateAttack();
            } catch (IOException ex) {
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
        map1.getTowersList().remove(point);
        coins+=Integer.parseInt(destroyLootLB.getText());
        coinsLB.setText(String.valueOf(coins));
    }

    @FXML
    void upgradeTower(ActionEvent event) {
        UpgradeBox.setVisible(false);
        Tower selectedTower = getTower(newPath);
       if(checkCoins(selectedTower)){
           return;
       }else {
           coins-=selectedTower.getBulidCost();
           coinsLB.setText(String.valueOf(coins));
       }
        setTowerOnPosition(newPath);
        for (ImageView image : map1.getTowersList().keySet()) {
            if (image == point) {
                map1.getTowersList().replace(point, selectedTower);
            }
        }
    }

    public Tower getTower(String path){
        Tower selectedTower = null;
        switch (newPath) {
            case "/Towers/2ArcherTower.png":
                selectedTower = new ArcherTower(150, 130, 30);
                break;
            case "/Towers/3ArcherTower.png":
                selectedTower = new ArcherTower(200, 200, 40);
                break;
            case "/Towers/4ArcherTower.png":
                selectedTower = new ArcherTower(250, 250, 50);
                break;
            case "/Towers/2Artillery.png":
                selectedTower = new ArcherTower(225, 150, 35);
                break;
            case "/Towers/3Artillery.png":
                selectedTower = new ArcherTower(250, 200, 40);
                break;
            case "/Towers/4Artillery.png":
                selectedTower = new ArcherTower(280, 250, 45);
                break;
            default:
        }
        return selectedTower;
    }
    public boolean checkCoins(Tower tower) {
        if (coins < tower.getBulidCost()) {
            PageController.showAlert("Error", "You don't have enough coins to upgrade this tower!!", "", Alert.AlertType.ERROR);
            UpgradeBox.setVisible(false);
            towersBox.setVisible(false);
            return true;
        }
        return false;
    }
}
