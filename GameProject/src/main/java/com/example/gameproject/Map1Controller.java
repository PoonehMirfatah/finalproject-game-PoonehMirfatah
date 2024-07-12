

package com.example.gameproject;
import Controllers.MapController;
import Controllers.PlayerController;
import Controllers.SpellsController;
import Models.Map;
import Models.Position;
import Models.Raiders.*;
import Models.Spells.*;
import Models.Towers.*;
import Models.Wave;
import Controllers.SQL.SQLController;
import javafx.animation.*;
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
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

import static com.example.gameproject.SettingPageController.*;

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
    private QuadCurve ps6;

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

<<<<<<< HEAD
    int playerCoins;
    int playerHealth;
    ImageView point;
    String newPath;
    Path path = new Path();
    Map map1;


    ImageView point;
    String newPath;
    Path path = new Path();
    private boolean firstAttack = true;
    int waveIndex;
    boolean isFinished=false;
    List<VBox> vboxesList=new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            SettingPageController.player.stop();
            SettingPageController.setSound("Music/gameMusic.wav");
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

        Position end = new Position(0, 549);
        towersPosition.add(p1);
        towersPosition.add(p2);
        towersPosition.add(p3);
        towersPosition.add(p4);

        ArrayList<Wave> attackWaves = new ArrayList<>();
<<<<<<< HEAD
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
=======

        ShieldRaider raider1 = new ShieldRaider();
        WizardRaider raider2 = new WizardRaider();
        ShieldRaider raider3 = new ShieldRaider();
        WizardRaider raider4 = new WizardRaider();
        ShieldRaider raider5 = new ShieldRaider();

        Wave wave1 = new Wave(raider1, 3);
        Wave wave2 = new Wave(raider2, 6);
        Wave wave3 = new Wave(raider3, 8);
        Wave wave4 = new Wave(raider4, 10);
        Wave wave5 = new Wave(raider5, 10);
>>>>>>> b01

        attackWaves.add(wave1);
        attackWaves.add(wave2);
        attackWaves.add(wave3);
        attackWaves.add(wave4);
        attackWaves.add(wave5);

<<<<<<< HEAD
        Position DP1=new Position(damagePoint1.getLayoutX(),damagePoint1.getLayoutY());
        Position DP2=new Position(damagePoint2.getLayoutX(),damagePoint2.getLayoutY());
        Position DP3=new Position(damagePoint3.getLayoutX(),damagePoint3.getLayoutY());
        Position DP4=new Position(damagePoint4.getLayoutX(),damagePoint4.getLayoutY());
        map1 = new Map(towersPosition, path, end, attackWaves, 300, 20);
=======
        Position DP1 = new Position(damagePoint1.getLayoutX(), damagePoint1.getLayoutY());
        Position DP2 = new Position(damagePoint2.getLayoutX(), damagePoint2.getLayoutY());
        Position DP3 = new Position(damagePoint3.getLayoutX(), damagePoint3.getLayoutY());
        Position DP4 = new Position(damagePoint4.getLayoutX(), damagePoint4.getLayoutY());

        map1 = new Map(towersPosition, path, end, attackWaves, 300, 20,1);

>>>>>>> b01
        map1.getDamagePoints().add(DP1);
        map1.getDamagePoints().add(DP2);
        map1.getDamagePoints().add(DP3);
        map1.getDamagePoints().add(DP4);

<<<<<<< HEAD
        MapController.setMap(map1);
        playerCoins = 1000;
        playerHealth = 20;
        heartLB.setText(String.format("%s/20", playerHealth));
        coinsLB.setText(String.valueOf(playerCoins));
=======
        PlayerController.getPlayer().setCoins(300);
        PlayerController.getPlayer().setHealth(20);
        MapController.setMap(map1);
        heartLB.setText(String.format("%s/20", PlayerController.getPlayer().getHealth()));
        coinsLB.setText(String.valueOf(PlayerController.getPlayer().getCoins()));
>>>>>>> b01
        waveLB.setText(String.format("Wave %s/5", map1.getWaveCounter()));
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
            MapController.getInstance().bombAttacks(pane);
            for(Raider raider:MapController.getMap().getAliveRaiders()){
                int index=MapController.getMap().getAliveRaiders().indexOf(raider);
                VBox vBox=vboxesList.get(index);
                PathTransition pathTransition=MapController.getMap().getPathTransitions().get(index);
                removeRaider(raider, vBox, pathTransition);
            }

            MapController.getMap().getAliveRaiders().clear();
            setSpellCounts();
<<<<<<< HEAD
        }
    }
    public void bombAttacks() throws InterruptedException {
        for(Position pointDamage: map1.getDamagePoints()) {
            Image image = new Image(getClass().getResource("/Weapon/bullet.png").toExternalForm());
            ImageView bomb = new ImageView(image);
            Path path1 = new Path();
            bomb.setFitWidth(30);
            bomb.setPreserveRatio(true);

            pane.getChildren().add(bomb);
            double xStart = 300;
            double yStart = 0;

            path1.getElements().add(new MoveTo(xStart, yStart));


            double xEnd = pointDamage.getX();
            double yEnd = pointDamage.getY();
            path1.getElements().add(new LineTo(xEnd, yEnd));

            PathTransition pathTransition = new PathTransition();
            pathTransition.setDuration(Duration.seconds(1));
            pathTransition.setPath(path1);
            pathTransition.setNode(bomb);
            pathTransition.setAutoReverse(false);
            pathTransition.play();
            pathTransition.setOnFinished(event2 -> {
                pane.getChildren().remove(bomb);
                pane.getChildren().removeIf(node -> node instanceof VBox);
            });

        }
        Wave currentWave = map1.getAttackWave().get(map1.getWaveIndex());
           for(Raider raider:currentWave.getRaiders()) {
               raider.setHealth(0);
           }

=======

        }
>>>>>>> b01
    }

    @FXML
    void dropCoins(MouseEvent event) throws Exception {
<<<<<<< HEAD
       CoinSpell coinSpell= new CoinSpell();
       SpellsController.setSpell(coinSpell);
        if(SpellsController.getInstance().drop()){
            playerCoins +=coinSpell.getCoinIncrease();
            coinsLB.setText(String.valueOf(playerCoins));
=======
        CoinSpell coinSpell = new CoinSpell();
        SpellsController.setSpell(coinSpell);
        if (SpellsController.getInstance().drop()) {
            PlayerController.getPlayer().setCoins(PlayerController.getPlayer().getCoins()+coinSpell.getCoinIncrease());
            setSound("Music/getCoins.mp3");
            coinsLB.setText(String.valueOf(PlayerController.getPlayer().getCoins()));
>>>>>>> b01
            setSpellCounts();
        }
    }

    @FXML
    void dropFreeze(MouseEvent event) throws URISyntaxException {
        FreezeSpell freezeSpell = new FreezeSpell();
        SpellsController.setSpell(freezeSpell);
<<<<<<< HEAD
        if(SpellsController.getInstance().drop()){
=======
        if (SpellsController.getInstance().drop()) {
            setSound("Music/freeze.mp3");
>>>>>>> b01
            MapController.getInstance().freezeTransitions();
            MapController.getInstance().freezeTimelines();
            setSpellCounts();
        }
    }


    @FXML
    void dropHealth(MouseEvent event) throws URISyntaxException {
        if(PlayerController.getPlayer().getHealth()==20){
            return;
        }
        HealthSpell healthSpell = new HealthSpell();
        SpellsController.setSpell(healthSpell);
<<<<<<< HEAD
        if(SpellsController.getInstance().drop()){
            playerHealth +=healthSpell.getHealthIncrease();
            if(playerHealth >20){
                playerHealth =20;
            }
            heartLB.setText(String.valueOf(playerHealth));
=======
        if (SpellsController.getInstance().drop()) {
            PlayerController.getPlayer().setHealth(healthSpell.getHealthIncrease()+ PlayerController.getPlayer().getHealth());
            if (PlayerController.getPlayer().getHealth ()> 20) {
                PlayerController.getPlayer().setHealth (20);
            }
            setSound("Music/health2.mp3");
            heartLB.setText(String.format("%s/20", PlayerController.getPlayer().getHealth ()));
>>>>>>> b01
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
        }
        towersBox.setVisible(true);
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
                selectedTower1 = new ArcherTower(100, 70, 180,1);
                if (checkCoins(selectedTower1)) {
                    return;
                }
                setTowerOnPosition("/Towers/1ArcherTower.png");
                break;
            case "t2":
                selectedTower1 = new Artillery(500, 125, 180,1,70);
                if (checkCoins(selectedTower1)) {
                    return;
                }
                setTowerOnPosition("/Towers/1Artillery.png");
                break;
            case "t3":
                selectedTower1 = new WizardTower(150, 100, 180,1);
                if (checkCoins(selectedTower1)) {
                    return;
                }
                setTowerOnPosition("/Towers/1WizardTower.png");
                break;
            case "t4":
                selectedTower1 = new AirTower(150, 100, 180,1);
                if (checkCoins(selectedTower1)) {
                    return;
                }
                setTowerOnPosition("/Towers/1ArmyPlace.png");
                break;
            default:
                return;
        }
        //assert selectedTower1 != null;
<<<<<<< HEAD
        map1.getTowersList().put(point, selectedTower1);
        playerCoins -= selectedTower1.getBulidCost();
        coinsLB.setText(String.valueOf(playerCoins));
=======
        setSound("Music/build.wav");
        MapController.getMap().getTowersList().put(point, selectedTower1);
        setSound("Music/costCoin.mp3");
        PlayerController.getPlayer().setCoins(PlayerController.getPlayer().getCoins()- selectedTower1.getBulidCost());
        coinsLB.setText(String.valueOf(PlayerController.getPlayer().getCoins()));
>>>>>>> b01
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
<<<<<<< HEAD
        if (MapController.getMap().isFirstAttack()) {
            MapController.getMap().setFirstAttack(false);
            initiateAttack();
        } else {
            initiateAttack();
            playerCoins += 70;
            coinsLB.setText(String.valueOf(playerCoins));
=======
        SettingPageController.setSound("Music/start.mp3");
        if (firstAttack) {
            firstAttack = false;
            initiateAttack();
        } else {
            initiateAttack();
            setSound("Music/getCoins.mp3");
            PlayerController.getPlayer().setCoins(PlayerController.getPlayer().getCoins()+ 70);
            coinsLB.setText(String.valueOf(PlayerController.getPlayer().getCoins()));
>>>>>>> b01
        }
    }

    private void initiateAttack() throws Exception {
        setPath();
<<<<<<< HEAD
        if (map1.getWaveCounter() < 5) {
            map1.setWaveCounter(map1.getWaveCounter() + 1);
            waveLB.setText(String.format("Wave %s/5", map1.getWaveCounter()));
        } else{
            checkWin();
            return;
        }
=======
        if(MapController.getInstance().checkWin(5)){
            waveLB.setText(String.format("Wave %s/5", MapController.getMap().getWaveCounter()));
        }else {
            startBT.setVisible(false);
            return;
        }
        waveIndex = MapController.getMap().getWaveCounter() - 1;
        Wave currentWave = MapController.getMap().getAttackWave().get(waveIndex);
>>>>>>> b01

        map1.setWaveIndex(map1.getWaveCounter() - 1);
        Wave currentWave = map1.getAttackWave().get(map1.getWaveIndex());
        int i = 0;
        for (i = 0; i < currentWave.getRaiderCount(); i++) {
            if(isFinished){
                return;
            }
            int delay = i * 1000;
<<<<<<< HEAD
            VBox vBox = new VBox();

            ArrayList<Image> heroImages = currentWave.getRaiders().get(i).getHeroImages();
            Image image1 = heroImages.get(0);
            ImageView walkKnight = new ImageView(image1);
            walkKnight.setFitHeight(50);
            walkKnight.setPreserveRatio(true);
            vBox.getChildren().add(walkKnight);
            MapController.getInstance().timeline(vBox, heroImages);

            PauseTransition pauseTransition = new PauseTransition(Duration.millis(delay));
            Raider currentRaider = currentWave.getRaiders().get(i);
            currentRaider.setvBox(vBox);
            map1.getAliveRaiders().add(currentRaider);
=======
            VBox vBox=MapController.getInstance().addRaiderVbox(currentWave,i);
            PauseTransition pauseTransition = new PauseTransition(Duration.millis(delay));
            Raider currentRaider = currentWave.getRaiders().get(i);
            vboxesList.add(vBox);
            MapController.getMap().getAliveRaiders().add(currentRaider);
>>>>>>> b01

            int raiderHealth = currentRaider.getHealth();
            pauseTransition.setOnFinished(e -> {
                PathTransition pathTransition = setPathForNextRaider(path, currentRaider, vBox, raiderHealth);
                pathTransition.setOnFinished(event2 -> {
<<<<<<< HEAD
                    pane.getChildren().remove(vBox);
                        playerHealth -= 1;
                        heartLB.setText(String.format("%s/20", playerHealth));
                        if (playerHealth == 0) {
                            try {
                                checkLost();
                                return;
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        map1.getPathTransitions().remove(pathTransition);
                    if (map1.getPathTransitions().isEmpty()) {
                        startNextAttack();
                    }
                });
                pathTransition.play();
                map1.getPathTransitions().add(pathTransition);
=======
                    removeEndPathRaider(vBox, pathTransition);
                    MapController.getMap().getAliveRaiders().remove(currentRaider);
                });
                pathTransition.play();
                MapController.getMap().getPathTransitions().add(pathTransition);

>>>>>>> b01
            });
            pauseTransition.play();
        }
    }
<<<<<<< HEAD
    public void checkWin() throws Exception {
            PageController.showAlert("Finished", "YOU WON!", " ", Alert.AlertType.INFORMATION);
            PlayerController.getInstance().getPlayer().setDiamonds(PlayerController.getInstance().player.getDiamonds()+100);
            PlayerController.getInstance().updateSpells();
            SQLController.updatePlayer(PlayerController.getInstance().player.getID());
=======
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
>>>>>>> b01
            try {
                MapController.getInstance().checkLost();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
<<<<<<< HEAD
//            startBT.setVisible(false);
        }

    public void checkLost() throws Exception {
    PageController.showAlert("Finished", "GAME OVER", " ", Alert.AlertType.INFORMATION);
        PlayerController.getInstance().updateSpells();
    try {
        Main.setRoot(PageController.stage,"HomePage.fxml",722,622);
    } catch (IOException ex) {
        throw new RuntimeException(ex);
        }
    }

//    public void checkHealthTimeLine(List<Raider> nearRaiders){
//        Timeline healthTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
//        for(Raider raider:nearRaiders){
//            pane.getChildren().remove(raider.getvBox());
//            aliveRaiders.remove(raider);
//            raider.setDead(true);
//            playerCoins += raider.getLoot();
//            coinsLB.setText(String.valueOf(playerCoins));
//            raider.getPathTransition().stop();
//            pathTransitions.remove(raider.getPathTransition());
//            if (pathTransitions.isEmpty()) {
//                startNextAttack();
//            }
//        }
//        }));
//        healthTimeline.play();
//        healthTimeline.setCycleCount(Timeline.INDEFINITE);
//    }

=======
        }
        vboxesList.remove(vBox);
        MapController.getMap().getPathTransitions().remove(pathTransition);
        if (MapController.getMap().getPathTransitions().isEmpty() && PlayerController.getPlayer().getHealth()>0) {
            startNextAttack();
        }
    }
>>>>>>> b01
    public void attackTimeLine(Raider currentRaider, VBox vBox, PathTransition pathTransition, int health) {
        Timeline attackTimeline=new Timeline();
        attackTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (isFinished) {
                return;
            }
            if(!MapController.getMap().getAliveRaiders().contains(currentRaider)) {
                return;
            }
            currentRaider.setHealth(health);
            for (ImageView point : MapController.getMap().getTowersList().keySet()) {
                Tower tower = MapController.getMap().getTowersList().get(point);
                double distance = Math.hypot(vBox.getTranslateX() - point.getLayoutX(), vBox.getTranslateY() - point.getLayoutY());
<<<<<<< HEAD
                if (distance <= tower.getRange() && !map1.getActiveTowers().contains(point)) {
                    if (tower instanceof ArcherTower) {
                        archerTowerAttack(point, vBox);
                        map1.getActiveTowers().add(point);
                        if (currentRaider instanceof ShieldRaider) {
                            currentRaider.setHealth(currentRaider.getHealth() - tower.getDestroyPower() / 2);
                        } else {
                            currentRaider.setHealth(currentRaider.getHealth() - tower.getDestroyPower());
                        }
                    } else if (tower instanceof Artillery) {
                        map1.getActiveTowers().add(point);
                        artilleryTowerAttack(point, vBox);
                        //List<Raider>nears=getNearbyRaiders(vBox,50);
                        //System.out.println(nears.size());
                        //List<Raider> nearRaiders = getNearbyRaiders(vBox, 100);
                        //checkHealthTimeLine(nearRaiders);
                        currentRaider.setHealth(currentRaider.getHealth() - tower.getDestroyPower());

                    } else if (tower instanceof WizardTower) {
                        map1.getActiveTowers().add(point);
                        wizardTowerAttack(point, vBox);
                        currentRaider.setHealth(currentRaider.getHealth() - tower.getDestroyPower());
                        if (currentRaider instanceof ShieldRaider && currentRaider.getHealth() <= 100) {
                            vBox.getChildren().get(1).setVisible(true);
                        }
                    }
                    if (currentRaider.getHealth() <= 0) {
                        removeRaider(vBox,currentRaider,pathTransition);
                        return;
=======
                if (distance <= tower.getRange() && !MapController.getMap().getActiveTowers().contains(point)&&currentRaider.getHealth()>0) {
                    if (tower instanceof ArcherTower && (!(currentRaider instanceof WizardRaider))) {
                        MapController.getInstance().archerTowerAttack(tower, currentRaider, point, vBox, pane);

                    } else if (tower instanceof Artillery && (!(currentRaider instanceof FlyerRaider))) {
                        MapController.getInstance().artilleryTowerAttack( point, vBox, pane);
                        attackNearRaiders(tower,vBox,((Artillery) tower).getDamageRange());
                        return;

                    } else if (tower instanceof WizardTower) {
                        MapController.getInstance().wizardTowerAttack(tower, currentRaider, point, vBox, pane);


                    } else if (tower instanceof AirTower && (currentRaider instanceof FlyerRaider)) {
                        MapController.getInstance().airTowerAttack(tower, currentRaider, point, vBox, pane);


>>>>>>> b01
                    }
                }

                if(currentRaider.getHealth()<=0){
                    removeRaider(currentRaider, vBox, pathTransition);
                    MapController.getMap().getAliveRaiders().remove(currentRaider);
                    return;
                }
            }
        }));
        attackTimeline.setCycleCount(Timeline.INDEFINITE);
        attackTimeline.play();

    }
<<<<<<< HEAD
//    public List<Raider> getNearbyRaiders(VBox vBox, double radius) {
//        List<Raider> nearRaiders = new ArrayList<>();
//        for (Raider raider : map1.getAttackWave().get(waveIndex).getRaiders()) {
//            double distance = Math.hypot(raider.getvBox().getTranslateX() - vBox.getTranslateX(),
//                    raider.getvBox().getTranslateY() - vBox.getTranslateY());
//            if (distance <= radius) {
//                nearRaiders.add(raider);
//            }
//        }
//        return nearRaiders;
//    }
    public void  removeRaider(VBox vBox,Raider currentRaider,PathTransition pathTransition){
        pane.getChildren().remove(vBox);
        map1.getAliveRaiders().remove(currentRaider);
        currentRaider.setDead(true);
        playerCoins += currentRaider.getLoot();
        coinsLB.setText(String.valueOf(playerCoins));
        pathTransition.stop();
        map1.getPathTransitions().remove(pathTransition);
        if (map1.getPathTransitions().isEmpty()) {
            startNextAttack();
        }
    }
    public void wizardTowerAttack(ImageView point, VBox target){
        Path path1 = new Path();
        Image image = new Image(getClass().getResource("/Weapon/fireball.png").toExternalForm());
        ImageView ray = new ImageView(image);
        ray.setFitWidth(30);
        ray.setPreserveRatio(true);
=======
>>>>>>> b01

    public  void attackNearRaiders(Tower tower,VBox vBox1,int range){
        ArrayList<Integer> indexes=new ArrayList<>();
        for(Raider raider:MapController.getMap().getAliveRaiders()){
            int index=MapController.getMap().getAliveRaiders().indexOf(raider);
            VBox vBox2=vboxesList.get(index);
            double distance=Math.hypot(vBox1.getTranslateX()-vBox2.getTranslateX(),vBox1.getTranslateY()-vBox2.getTranslateY());
            if(distance<range) {
                PathTransition pathTransition = MapController.getMap().getPathTransitions().get(index);
                raider.setHealth(raider.getHealth()-tower.getDestroyPower());

<<<<<<< HEAD
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
            map1.getActiveTowers().remove(point);
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
            map1.getActiveTowers().remove(point);
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
            map1.getActiveTowers().remove(point);
        });
=======
                if(raider.getHealth()<=0) {
                    indexes.add(index);
                    removeRaider(raider, vBox2, pathTransition);
                }else{
                    attackTimeLine(raider,vBox1,pathTransition,raider.getHealth());
                    return;
                }
            }
        }
        for(int index:indexes){
            MapController.getMap().getAliveRaiders().remove(index);
        }
>>>>>>> b01
    }


    public void removeRaider(Raider currentRaider,VBox vBox,PathTransition pathTransition) {
        pane.getChildren().remove(vBox);
        vboxesList.remove(vBox);
        //MapController.getMap().getAliveRaiders().remove(currentRaider);
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

        double xControl5 = ps5.getControlX() + ps5.getLayoutX();
        double yControl5 = ps5.getControlY() + ps5.getLayoutY();
        double xEnd5 = ps5.getEndX() + ps5.getLayoutX();
        double yEnd5 = ps5.getEndY() + ps5.getLayoutY();

        double xControl6 = ps6.getControlX() + ps6.getLayoutX();
        double yControl6 = ps6.getControlY() + ps6.getLayoutY();
        double xEnd6 = ps6.getEndX() + ps6.getLayoutX();
        double yEnd6 = ps6.getEndY() + ps6.getLayoutY();

        path.getElements().add(new QuadCurveTo(xControl1, yControl1, xEnd1, yEnd1));
        path.getElements().add(new QuadCurveTo(xControl2, yControl2, xEnd2, yEnd2));
        path.getElements().add(new QuadCurveTo(xControl3, yControl3, xEnd3, yEnd3));
        path.getElements().add(new QuadCurveTo(xControl4, yControl4, xEnd4, yEnd4));
        path.getElements().add(new QuadCurveTo(xControl5, yControl5, xEnd5, yEnd5));
        path.getElements().add(new QuadCurveTo(xControl6, yControl6, xEnd6, yEnd6));

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
<<<<<<< HEAD
        map1.getTowersList().remove(point);
        playerCoins +=Integer.parseInt(destroyLootLB.getText());
        coinsLB.setText(String.valueOf(playerCoins));
=======
        setSound("Music/destroy.mp3");
        MapController.getMap().getTowersList().remove(point);
        setSound("Music/getCoins.mp3");
        PlayerController.getPlayer().setCoins(PlayerController.getPlayer().getCoins()+ Integer.parseInt(destroyLootLB.getText()) );
        coinsLB.setText(String.valueOf(PlayerController.getPlayer().getCoins()));
>>>>>>> b01
    }

    @FXML
    void upgradeTower(ActionEvent event) throws URISyntaxException {
        UpgradeBox.setVisible(false);
<<<<<<< HEAD
        Tower selectedTower = getTower(newPath);
       if(checkCoins(selectedTower)){
           return;
       }else {
           playerCoins -=selectedTower.getBulidCost();
           coinsLB.setText(String.valueOf(playerCoins));
       }
=======
        Tower selectedTower = MapController.getInstance().getTower(newPath);
        if(MapController.getInstance().checkTowerLevelForUpgrade(selectedTower)){
            spellsBox.setVisible(true);
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
>>>>>>> b01
        setTowerOnPosition(newPath);
        for (ImageView image : MapController.getMap().getTowersList().keySet()) {
            if (image == point) {
                MapController.getMap().getTowersList().replace(point, selectedTower);
            }
        }
    }

<<<<<<< HEAD
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
                selectedTower = new WizardTower(450, 250, 245);
                break;

            case "/Towers/2ArmyPlace.png":
                selectedTower = new AirTower(350, 150, 220);
                break;
            case "/Towers/3ArmyPlace.png":
                selectedTower = new AirTower(400, 200, 240);
                break;
            case "/Towers/4ArmyPlace.png":
                selectedTower = new AirTower(450, 250, 245);
                break;
            default:
        }
        return selectedTower;
    }
    public boolean checkCoins(Tower tower) {
        if (playerCoins < tower.getBulidCost()) {
=======
    public boolean checkCoins(Tower tower) {
        if (PlayerController.getPlayer().getCoins() < tower.getBulidCost()) {
>>>>>>> b01
            PageController.showAlert("Error", "You don't have enough coins to upgrade this tower!!", "", Alert.AlertType.ERROR);
            UpgradeBox.setVisible(false);
            towersBox.setVisible(false);
            spellsBox.setVisible(true);
            return true;
        }
        return false;
    }

    public void quitMap(MouseEvent event) throws Exception {
        PlayerController.getInstance().updateSpells();
        SettingPageController.player.stop();
        SettingPageController.setSound("Music/startGame.mp3");
        player.setCycleCount(MediaPlayer.INDEFINITE);
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

