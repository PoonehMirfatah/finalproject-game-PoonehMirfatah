package Controllers;

import Controllers.SQL.SQLController;
import Models.Map;
import Models.Position;
import Models.Raiders.Raider;
import Models.Raiders.ShieldRaider;
import Models.Spells.Spell;
import Models.Towers.*;
import Models.Wave;
import com.example.gameproject.Main;
import com.example.gameproject.Map1Controller;
import com.example.gameproject.PageController;
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
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MapController {
    public static Map map;
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

    public void bombAttacks(Pane pane,int waveIndex) throws InterruptedException {
        for (Position pointDamage : map.getDamagePoints()) {
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
        Wave currentWave = map.getAttackWave().get(waveIndex);
        for (Raider raider : currentWave.getRaiders()) {
            raider.setHealth(0);
        }

    }

    public Tower getTower(String newPath) {
        Tower selectedTower = null;
        switch (newPath) {
            case "/Towers/1ArcherTower.png":
                selectedTower = new ArcherTower(100, 70, 200);
                break;
            case "/Towers/2ArcherTower.png":
                selectedTower = new ArcherTower(150, 130, 220);
                break;
            case "/Towers/3ArcherTower.png":
                selectedTower = new ArcherTower(200, 200, 240);
                break;
            case "/Towers/4ArcherTower.png":
                selectedTower = new ArcherTower(250, 250, 250);
                break;

            case "/Towers/1Artillery.png":
                selectedTower = new Artillery(400, 125, 200);
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

            case "/Towers/1WizardTower.png":
                selectedTower = new WizardTower(300, 100, 200);
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

            case "/Towers/1ArmyPlace.png":
                selectedTower = new AirTower(200, 100, 200);
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

    public boolean checkWin() throws Exception {
        if (map.getWaveCounter() < 5) {
            map.setWaveCounter(map.getWaveCounter() + 1);
            return true;
        } else {
            PageController.showAlert("Finished", "YOU WON!", " ", Alert.AlertType.INFORMATION);
            PlayerController.getPlayer().setDiamonds(PlayerController.getPlayer().getDiamonds()+100);
            PlayerController.getInstance().updateSpells();
            SQLController.updatePlayer(PlayerController.getPlayer().getID());
            try {
                Main.setRoot(PageController.stage,"HomePage.fxml",722,622);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
           return false;
        }
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
    public void wizardTowerAttack(Tower tower,Raider currentRaider,ImageView point, VBox target,Pane pane) {
        MapController.getMap().getActiveTowers().add(point);
        currentRaider.setHealth(currentRaider.getHealth() - tower.getDestroyPower());
        if (currentRaider instanceof ShieldRaider && currentRaider.getHealth() <= 100) {
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
        pathTransition.setDuration(Duration.seconds(0.5));
        pathTransition.setPath(path1);
        pathTransition.setNode(ray);
        pathTransition.setAutoReverse(false);
        pathTransition.play();

        pathTransition.setOnFinished(event -> {
            pane.getChildren().remove(ray);
            map.getActiveTowers().remove(point);
        });
    }
    public void airTowerAttack(Raider currentRaider,ImageView point, VBox target,Pane pane) {
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
        pathTransition.setDuration(Duration.seconds(0.5));
        pathTransition.setPath(path1);
        pathTransition.setNode(ray);
        pathTransition.setAutoReverse(false);
        pathTransition.play();

        pathTransition.setOnFinished(event -> {
            pane.getChildren().remove(ray);
            map.getActiveTowers().remove(point);
        });
    }

    public void artilleryTowerAttack(Tower tower,Raider currentRaider,ImageView point, VBox target,Pane pane) {
        MapController.getMap().getActiveTowers().add(point);
        currentRaider.setHealth(currentRaider.getHealth() - tower.getDestroyPower());
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
        pathTransition.setDuration(Duration.seconds(1));
        pathTransition.setPath(path1);
        pathTransition.setNode(ray);
        pathTransition.setAutoReverse(false);
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
        String newLevel = String.valueOf(level);
        String newPath = towerPath.replaceAll("\\d", newLevel);
        return newPath;
    }



    //-------------------------------------------------------------
}
