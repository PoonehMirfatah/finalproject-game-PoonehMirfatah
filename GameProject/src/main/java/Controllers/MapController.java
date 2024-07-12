package Controllers;

import Models.Map;
import Models.Position;
import Models.Raiders.Raider;
import Models.Raiders.ShieldRaider;
import Models.Spells.Spell;
import Models.Towers.ArcherTower;
import Models.Towers.Artillery;
import Models.Towers.Tower;
import Models.Towers.WizardTower;

import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Path;
import javafx.util.Duration;

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
        MapController.map=map;
    }

    //public static  boolean firstAttack = true;
    public void freezeTransitions(){
        for(PathTransition pathTransition:map.getPathTransitions()) {
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
    public void freezeTimelines(){
        for(Timeline timeline:map.getTimelines()){
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
        map.getTimelines().add(timeline);
    }


}
