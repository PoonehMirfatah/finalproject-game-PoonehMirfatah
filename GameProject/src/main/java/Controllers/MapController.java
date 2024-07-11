package Controllers;

import Controllers.SQL.SQLController;
import Models.Map;
import Models.Position;
import Models.Raiders.Raider;
import Models.Raiders.ShieldRaider;
import Models.Towers.*;
import Models.Wave;
import com.example.gameproject.Main;
import com.example.gameproject.PageController;
import com.example.gameproject.SettingPageController;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import static com.example.gameproject.SettingPageController.setSound;

public class MapController {
    public static Map map;
    public static MediaPlayer player;
    private static MapController instance;

    private MapController() {
    }

    public static MapController getInstance() {
        if (instance == null) {
            instance = new MapController();
        }
        return instance;
    }

    public static Map getMap() {
        return map;
    }

    public static void setMap(Map map) {
        MapController.map = map;
    }

    //public static  boolean firstAttack = true;
    public void freezeTransitions() {
        for (PathTransition pathTransition : map.getPathTransitions()) {
            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(5));
            pauseTransition.setOnFinished(event2 -> pathTransition.play());

            PauseTransition stopTransition = new PauseTransition(Duration.seconds(0.1));
            stopTransition.setOnFinished(event3 -> {
                pathTransition.pause();
                pauseTransition.play();
            });
            pathTransition.play();
            stopTransition.play();
        }
    }

    public void freezeTimelines() {
        for (Timeline timeline : map.getTimelines()) {
            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(5));
            pauseTransition.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    timeline.play();
                }
            });

            timeline.pause();
            pauseTransition.play();
        }
    }

    public void timeline(VBox vBox, ArrayList<Image> heroImages) {
        vBox.setTranslateX(vBox.getTranslateX() - 100);
        vBox.setTranslateY(vBox.getTranslateY() - 50);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            ImageView imageView = (ImageView) vBox.getChildren().get(0);
            int index = heroImages.indexOf(imageView.getImage()) + 1;

            if (index >= heroImages.size()) {
                index = 0;
            }
            //Sheild
            Image image = new Image(getClass().getResource("/Weapon/Sheild.png").toExternalForm());
            ImageView sheild = new ImageView(image);
            sheild.setFitWidth(30);
            sheild.setPreserveRatio(true);
            sheild.setVisible(false);
            //
            ImageView walkKnight = new ImageView(heroImages.get(index));
            walkKnight.setFitHeight(60);
            walkKnight.setPreserveRatio(true);
            vBox.getChildren().setAll(walkKnight, sheild);
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        map.getTimelines().add(timeline);
    }

    public void bombAttacks(Pane pane) throws  URISyntaxException {
        for (Position pointDamage : map.getDamagePoints()) {
            Image image = new Image(getClass().getResource("/Weapon/bombs.png").toExternalForm());
            ImageView bomb = new ImageView(image);
            Path path1 = new Path();
            bomb.setFitWidth(50);
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
            setSound("Music/bombSpell.mp3");
            pathTransition.setOnFinished(event2 -> {
                pane.getChildren().remove(bomb);
            });

        }
    }

    public Tower getTower(String newPath) {
        Tower selectedTower = null;
        switch (newPath) {
            case "/Towers/1ArcherTower.png":
                selectedTower = new ArcherTower(100, 70, 180,1);
                break;
            case "/Towers/2ArcherTower.png":
                selectedTower = new ArcherTower(150, 130, 200,2);
                break;
            case "/Towers/3ArcherTower.png":
                selectedTower = new ArcherTower(200, 200, 215,3);
                break;
            case "/Towers/4ArcherTower.png":
                selectedTower = new ArcherTower(250, 250, 230,4);
                break;

            case "/Towers/1Artillery.png":
                selectedTower = new Artillery(500, 125, 180,1,70);
                break;
            case "/Towers/2Artillery.png":
                selectedTower = new Artillery(550, 150, 200,2,100);
                break;
            case "/Towers/3Artillery.png":
                selectedTower = new Artillery(600, 200, 215,3,100);
                break;
            case "/Towers/4Artillery.png":
                selectedTower = new Artillery(650, 250, 230,4,140);
                break;

            case "/Towers/1WizardTower.png":
                selectedTower = new WizardTower(150, 100, 180,1);
                break;

            case "/Towers/2WizardTower.png":
                selectedTower = new WizardTower(200, 150, 200,2);
                break;
            case "/Towers/3WizardTower.png":
                selectedTower = new WizardTower(250, 200, 215,3);
                break;
            case "/Towers/4WizardTower.png":
                selectedTower = new WizardTower(300, 250, 230,4);
                break;

            case "/Towers/1ArmyPlace.png":
                selectedTower = new AirTower(150, 100, 180,1);
                break;

            case "/Towers/2ArmyPlace.png":
                selectedTower = new AirTower(200, 150, 200,2);
                break;
            case "/Towers/3ArmyPlace.png":
                selectedTower = new AirTower(250, 200, 215,3);
                break;
            case "/Towers/4ArmyPlace.png":
                selectedTower = new AirTower(300, 250, 230,4);
                break;
            default:
                return null;
        }
        return selectedTower;
    }

    public VBox addRaiderVbox(Wave currentWave, int waveNum) {
        VBox vBox = new VBox();
        ArrayList<Image> heroImages = currentWave.getRaiders().get(waveNum).getHeroImages();
        Image image1 = heroImages.get(0);
        ImageView walkKnight = new ImageView(image1);
        walkKnight.setFitHeight(50);
        walkKnight.setPreserveRatio(true);
        vBox.getChildren().add(walkKnight);
        timeline(vBox, heroImages);
        return vBox;
    }

    public boolean checkWin(int waveNumbers) throws Exception {
        if (map.getWaveCounter() < waveNumbers) {
            map.setWaveCounter(map.getWaveCounter() + 1);
            return true;
        } else {
            SettingPageController.player.stop();
            SettingPageController.setSound("Music/win.wav");
            Alert alert=PageController.showAlert2("Finished", "YOU WON!", " ", Alert.AlertType.INFORMATION);
            PlayerController.getPlayer().setDiamonds(PlayerController.getPlayer().getDiamonds()+100);
            PlayerController.getInstance().updateSpells();
            SQLController.updatePlayer(PlayerController.getPlayer().getID());
            try {
                alert.setOnHidden((event)->{
                    try {
                        Main.setRoot(PageController.stage,"HomePage.fxml",722,622);
                        SettingPageController.setSound("Music/gamemusic.mp3");
                    } catch (IOException | URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                });
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
           return false;
        }
    }

    public void checkLost() throws Exception {
        SettingPageController.player.stop();
        SettingPageController.setSound("Music/gameOver.wav");
        Alert alert=PageController.showAlert2("Finished", "GAME OVER", " ", Alert.AlertType.INFORMATION);
        PlayerController.getInstance().updateSpells();
        try {
            alert.setOnHidden((event -> {
                try {
                    Main.setRoot(PageController.stage,"HomePage.fxml",722,622);
                    SettingPageController.setSound("Music/gamemusic.mp3");
                } catch (IOException | URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            }));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void wizardTowerAttack(Tower tower,Raider currentRaider,ImageView point, VBox target,Pane pane)  {
        MapController.getMap().getActiveTowers().add(point);
        currentRaider.setHealth(currentRaider.getHealth() - tower.getDestroyPower());
        if (currentRaider instanceof ShieldRaider ) {
            target.getChildren().get(1).setVisible(true);
        }
        Path path1 = new Path();
        Image image = new Image(getClass().getResource("/Weapon/fireball.png").toExternalForm());
        ImageView ray = new ImageView(image);
        ray.setFitWidth(30);
        ray.setPreserveRatio(true);

        pane.getChildren().add(ray);

        double xStart = point.getLayoutX() + 50;
        double yStart = point.getLayoutY() + 50;

        path1.getElements().add(new MoveTo(xStart, yStart));


        double xEnd = target.getTranslateX();
        double yEnd = target.getTranslateY();
        path1.getElements().add(new LineTo(xEnd, yEnd));

        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(0.3));
        pathTransition.setPath(path1);
        pathTransition.setNode(ray);
        pathTransition.setAutoReverse(false);
        pathTransition.play();
        try {
            SettingPageController.setSound("Music/wizard1.mp3");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        pathTransition.setOnFinished(event -> {
            pane.getChildren().remove(ray);
            map.getActiveTowers().remove(point);
        });
    }
    public void airTowerAttack(Tower tower,Raider currentRaider,ImageView point, VBox target,Pane pane)  {
        MapController.getMap().getActiveTowers().add(point);
        currentRaider.setHealth(currentRaider.getHealth() - tower.getDestroyPower());
        Path path1 = new Path();
        Image image = new Image(getClass().getResource("/Weapon/airBullet.png").toExternalForm());
        ImageView ray = new ImageView(image);
        ray.setFitWidth(30);
        ray.setPreserveRatio(true);

        pane.getChildren().add(ray);

        double xStart = point.getLayoutX() + 50;
        double yStart = point.getLayoutY() + 50;

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
        try {
            SettingPageController. setSound("Music/flyattack.mp3");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        pathTransition.setOnFinished(event -> {
            pane.getChildren().remove(ray);
            map.getActiveTowers().remove(point);
        });
    }

    public void artilleryTowerAttack(Tower tower,Raider currentRaider,ImageView point, VBox target,Pane pane)  {
        MapController.getMap().getActiveTowers().add(point);

        Path path1 = new Path();
        Image image = new Image(getClass().getResource("/Weapon/bomb.png").toExternalForm());
        ImageView ray = new ImageView(image);
        ray.setFitWidth(30);
        ray.setPreserveRatio(true);

        pane.getChildren().add(ray);

        double xStart = point.getLayoutX() + 50;
        double yStart = point.getLayoutY() + 20;

        path1.getElements().add(new MoveTo(xStart, yStart));


        double xEnd = target.getTranslateX();
        double yEnd = target.getTranslateY();

        double xControl = (xEnd + xStart) / 2;
        double yControl = 50;

        path1.getElements().add(new QuadCurveTo(xControl, yControl, xEnd, yEnd));

        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(0.4));
        pathTransition.setPath(path1);
        pathTransition.setNode(ray);
        pathTransition.setAutoReverse(false);
        try {
            SettingPageController.setSound("Music/artillery.mp3");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        pathTransition.play();

        pathTransition.setOnFinished(event -> {
            pane.getChildren().remove(ray);
            map.getActiveTowers().remove(point);
        });
    }
    public void archerTowerAttack(Tower tower,Raider currentRaider,ImageView point, VBox target,Pane pane) {
        MapController.getMap().getActiveTowers().add(point);
        if (currentRaider instanceof ShieldRaider) {
            currentRaider.setHealth(currentRaider.getHealth() - tower.getDestroyPower() / 2);
        } else {
            currentRaider.setHealth(currentRaider.getHealth() - tower.getDestroyPower());
        }
        Path path1 = new Path();
        Image image = new Image(getClass().getResource("/Weapon/arrow.png").toExternalForm());
        ImageView arrow = new ImageView(image);
        arrow.setFitWidth(30);
        arrow.setPreserveRatio(true);

        pane.getChildren().add(arrow);

        double xStart = point.getLayoutX() + 50;
        double yStart = point.getLayoutY() + 50;

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
        try {
            SettingPageController.setSound("Music/archer.mp3");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        pathTransition.setOnFinished(event -> {
            pane.getChildren().remove(arrow);
            map.getActiveTowers().remove(point);
        });
    }

    public String getUpdateTowerPath(String towerPath){
        int level = 0;
        char digitChar = 0;
        for (Character chr : towerPath.toCharArray()) {
            if (Character.isDigit(chr)) {
                digitChar = chr;
                level = Integer.parseInt(String.valueOf(digitChar));
            }
        }
        level += 1;
        if(level>4){
            return towerPath;
        }
        String newLevel = String.valueOf(level);
        String newPath = towerPath.replaceAll("\\d", newLevel);
        return newPath;
    }
    public boolean checkTowerLevelForUpgrade(Tower tower){
        if(PlayerController.getPlayer().getLevel()<tower.getLevel()){
            PageController.showAlert("Error",String.format("You can't Update this tower in level %s",tower.getLevel()-1)
                    ,"", Alert.AlertType.ERROR);
            return true;
        }else {
            return false;
        }
    }

//    public static void setSound(String soundName) throws URISyntaxException {
//        String fileName = Main.class.getResource(soundName).toURI().toString();
//        Media media = new Media(fileName);
//        player= new MediaPlayer(media);
//        player.play();
//    }

    //-------------------------------------------------------------
}
