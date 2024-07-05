package com.example.gameproject;
import Models.Map;
import Models.Position;
import Models.Raiders.Raider;
import Models.Raiders.ShieldRaider;
import Models.Towers.ArcherTower;
import Models.Towers.Artillery;
import Models.Towers.Tower;
import Models.Towers.WizardTower;
import Models.Wave;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    List<PathTransition> pathTransitions = new ArrayList<>();
    int waveIndex;

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
        map1 = new Map(towersPosition, path, end, attackWaves, 300, 20);
        coins = 1000;
        health = 20;
        heartLB.setText(String.format("%s/20", health));
        coinsLB.setText(String.valueOf(coins));
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
                selectedTower1 = new ArcherTower(100, 70, 200);
                if(checkCoins(selectedTower1)){
                    return;
                }
                setTowerOnPosition("/Towers/1ArcherTower.png");
                break;
            case "t2":
                selectedTower1 = new Artillery(500, 125, 200);
                if(checkCoins(selectedTower1)){
                    return;
                }
                setTowerOnPosition("/Towers/1Artillery.png");
                break;
            case "t3":
                selectedTower1 = new WizardTower(300, 100, 200);
                if(checkCoins(selectedTower1)){
                    return;
                }
                setTowerOnPosition("/Towers/1WizardTower.png");
                break;
            case "t4":
                setTowerOnPosition("/Towers/ArmyPlace.png");

                break;
            default:
                return;
        }
        assert selectedTower1 != null;
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
            Tower newTower=getTower(newPath);
            upgradedCostLB.setText(String.valueOf(newTower.getBulidCost()));
            Tower lastTower=getTower(towerPath);
            destroyLootLB.setText(String.valueOf(lastTower.getBulidCost()/2));
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
            //Sheild
            Image image=new Image(getClass().getResource("/Weapon/Sheild.png").toExternalForm());
            ImageView sheild=new ImageView(image);
            sheild.setFitWidth(30);
            sheild.setPreserveRatio(true);
            sheild.setVisible(false);
            //
            ImageView walkKnight = new ImageView(heroImages.get(index));
            walkKnight.setFitHeight(50);
            walkKnight.setPreserveRatio(true);
            vBox.getChildren().setAll(walkKnight,sheild);
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

    Set<ImageView> activeTowers = new HashSet<>();

    private void initiateAttack() throws IOException {
        setPath();
         waveIndex = map1.getWaveCounter() - 1;
        Wave currentWave = map1.getAttackWave().get(waveIndex);

        int i = 0;
        for (i = 0; i < currentWave.getRaiderCount(); i++) {
            int delay = i * 1000;
            VBox vBox = new VBox();
            ArrayList<Image> heroImages = currentWave.getRaiders().get(i).getHeroImages();
            Image image1 = heroImages.get(0);
            ImageView walkKnight = new ImageView(image1);
            walkKnight.setFitHeight(50);
            walkKnight.setPreserveRatio(true);
            vBox.getChildren().add(walkKnight);
            timeline(vBox, heroImages);

            PauseTransition pauseTransition = new PauseTransition(Duration.millis(delay));
            Raider currentRaider = currentWave.getRaiders().get(i);
            currentRaider.setvBox(vBox);
            //

            //
            int raiderHealth=currentRaider.getHealth();
            pauseTransition.setOnFinished(e -> {
                PathTransition pathTransition = new PathTransition();
                currentRaider.setPathTransition(pathTransition);
                attackTimeLine(currentRaider,vBox,pathTransition,raiderHealth);
                pane.getChildren().add(vBox);
                double duration = 1d / currentRaider.getSpeed();
                pathTransition.setDuration(Duration.seconds(duration));
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


    public void attackTimeLine(Raider currentRaider,VBox vBox,PathTransition pathTransition,int health){
        Timeline attackTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            currentRaider.setHealth(health);
//                    Image image = new Image(getClass().getResource("/Raiders/dyedSheildRaider.png").toExternalForm());
//                    ImageView dyedRaider = new ImageView(image);
//                    dyedRaider.setPreserveRatio(true);
//                    dyedRaider.setFitWidth(30);
            for (ImageView point : map1.getTowersList().keySet()) {
                Tower tower = map1.getTowersList().get(point);
                double distance = Math.hypot(vBox.getTranslateX() - point.getLayoutX(), vBox.getTranslateY() - point.getLayoutY());
                if (distance <= tower.getRange() && !activeTowers.contains(point)) {
                    //System.out.println(currentRaider.getHealth());
                    if (tower instanceof ArcherTower) {
                        activeTowers.add(point);
                        archerTowerAttack(point, vBox);
                        if(currentRaider instanceof ShieldRaider) {
                            currentRaider.setHealth(currentRaider.getHealth() - tower.getDestroyPower()/2);
                        }else{
                            currentRaider.setHealth(currentRaider.getHealth() - tower.getDestroyPower());
                        }

                    } else if (tower instanceof Artillery) {
                        activeTowers.add(point);
                        artilleryTowerAttack(point, vBox);
                        currentRaider.setHealth(currentRaider.getHealth() - tower.getDestroyPower());

                    } else if (tower instanceof WizardTower) {
                        activeTowers.add(point);
                        wizardTowerAttack(point, vBox);
                        currentRaider.setHealth(currentRaider.getHealth() - tower.getDestroyPower());
                        if(currentRaider instanceof ShieldRaider &&(currentRaider.getHealth()<=100)){
                            vBox.getChildren().get(1).setVisible(true);
                        }
                    }
                    if(currentRaider.getHealth()<=0){
                        pane.getChildren().remove(vBox);
                        currentRaider.setDead(true);
                        coins+=currentRaider.getLoot();
                        coinsLB.setText(String.valueOf(coins));
                        pathTransition.stop();
                        pathTransitions.remove(pathTransition);
                        if (pathTransitions.isEmpty()) {
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
    private List<Raider> getNearbyRaiders(VBox vBox, double radius) {
        List<Raider> nearbyRaiders = new ArrayList<>();
        for (Raider raider : map1.getAttackWave().get(waveIndex).getRaiders()) {
            double distance = Math.hypot(vBox.getTranslateX() - raider.getvBox().getTranslateX(), vBox.getTranslateY() - raider.getvBox().getTranslateY());
            if (distance <= radius) {
                nearbyRaiders.add(raider);
            }
        }
        return nearbyRaiders;
    }
    public void wizardTowerAttack(ImageView point, VBox target){
        Path path1 = new Path();
        Image image = new Image(getClass().getResource("/Weapon/fireball.png").toExternalForm());
        ImageView ray = new ImageView(image);
        ray.setFitWidth(30);
        ray.setPreserveRatio(true);

        pane.getChildren().add(ray);

        double xStart = point.getLayoutX()+50;
        double yStart = point.getLayoutY()+50;

        path1.getElements().add(new MoveTo(xStart, yStart));


        double xEnd = target.getTranslateX();
        double yEnd = target.getTranslateY();
        path1.getElements().add(new LineTo(xEnd, yEnd));

        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(0.5));
        pathTransition.setPath(path1);
        pathTransition.setNode(ray);
        pathTransition.setAutoReverse(false);
        pathTransition.play();

        pathTransition.setOnFinished(event -> {
            pane.getChildren().remove(ray);
            activeTowers.remove(point);
        });
    }
    public void artilleryTowerAttack(ImageView point, VBox target){
        Path path1 = new Path();
        Image image = new Image(getClass().getResource("/Weapon/bomb.png").toExternalForm());
        ImageView ray = new ImageView(image);
        ray.setFitWidth(30);
        ray.setPreserveRatio(true);

        pane.getChildren().add(ray);

        double xStart = point.getLayoutX()+50;
        double yStart = point.getLayoutY()+20;

        path1.getElements().add(new MoveTo(xStart, yStart));


        double xEnd = target.getTranslateX();
        double yEnd = target.getTranslateY();

        double xControl = (xEnd+xStart)/2;
        double yControl = 50;

        path1.getElements().add(new QuadCurveTo(xControl,yControl,xEnd,yEnd));

        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(1));
        pathTransition.setPath(path1);
        pathTransition.setNode(ray);
        pathTransition.setAutoReverse(false);
        pathTransition.play();

        pathTransition.setOnFinished(event -> {
            pane.getChildren().remove(ray);
            activeTowers.remove(point);
        });
    }
    public void archerTowerAttack(ImageView point, VBox target) {
        Path path1 = new Path();
        Image image = new Image(getClass().getResource("/Weapon/arrow.png").toExternalForm());
        ImageView arrow = new ImageView(image);
        arrow.setFitWidth(30);
        arrow.setPreserveRatio(true);

        pane.getChildren().add(arrow);

        double xStart = point.getLayoutX()+50;
        double yStart = point.getLayoutY()+50;

        path1.getElements().add(new MoveTo(xStart, yStart));


        double xEnd = target.getTranslateX();
        double yEnd = target.getTranslateY();
        path1.getElements().add(new LineTo(xEnd, yEnd));


        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(0.5));
        pathTransition.setPath(path1);
        pathTransition.setNode(arrow);
        pathTransition.setAutoReverse(false);
        pathTransition.play();

        pathTransition.setOnFinished(event -> {
            pane.getChildren().remove(arrow);
            activeTowers.remove(point);
        });
    }


    public void setPath(){
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
                selectedTower = new ArcherTower(150, 130, 220);
                break;
            case "/Towers/3ArcherTower.png":
                selectedTower = new ArcherTower(200, 200, 240);
                break;
            case "/Towers/4ArcherTower.png":
                selectedTower = new ArcherTower(250, 250, 250);
                break;
            case "/Towers/2Artillery.png":
                selectedTower = new Artillery(225, 150, 220);
                break;
            case "/Towers/3Artillery.png":
                selectedTower = new Artillery(250, 200, 240);
                break;
            case "/Towers/4Artillery.png":
                selectedTower = new Artillery(280, 250, 245);
                break;
            case "/Towers/2WizardTower.png":
                selectedTower = new WizardTower(350, 150, 220);
                break;
            case "/Towers/3WizardTower.png":
                selectedTower = new WizardTower(400, 200, 240);
                break;
            case "/Towers/4WizardTower.png":
                selectedTower = new WizardTower(280, 250, 245);
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
    public void archerTowerAttack(Tower tower,ImageView point){
        //if(point.getLayoutX()+tower.getRange()<)
    }
}
