package Models.Raiders;

import javafx.scene.image.Image;

public class SpeedyRaider extends Raider{
    public SpeedyRaider() {
        setHealth(150);
        setLoot(10);
        setSpeed(1d/15d);
        setDead(false);
        Image image1 = new Image(getClass().getResource("/Raiders/12.png").toExternalForm());
        Image image2 = new Image(getClass().getResource("/Raiders/13.png").toExternalForm());
        Image image3 = new Image(getClass().getResource("/Raiders/14.png").toExternalForm());
        Image image4 = new Image(getClass().getResource("/Raiders/15.png").toExternalForm());
        Image image5= new Image(getClass().getResource("/Raiders/16.png").toExternalForm());
        Image image6 = new Image(getClass().getResource("/Raiders/17.png").toExternalForm());
        Image image7 = new Image(getClass().getResource("/Raiders/18.png").toExternalForm());
        Image image8 = new Image(getClass().getResource("/Raiders/19.png").toExternalForm());
        Image image9 = new Image(getClass().getResource("/Raiders/20.png").toExternalForm());
        getHeroImages().add(image1);
        getHeroImages().add(image2);
        getHeroImages().add(image3);
        getHeroImages().add(image4);
        getHeroImages().add(image5);
        getHeroImages().add(image6);
        getHeroImages().add(image7);
        getHeroImages().add(image8);
        getHeroImages().add(image9);
    }
}
