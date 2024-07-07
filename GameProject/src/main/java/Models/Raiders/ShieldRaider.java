package Models.Raiders;

import javafx.scene.image.Image;

public class ShieldRaider extends Raider {

    public ShieldRaider() {
       setHealth(400);
        setLoot(50);
        setSpeed(1d/20d);
        setDead(false);
        Image image1 = new Image(getClass().getResource("/Raiders/1.png").toExternalForm());
        Image image2 = new Image(getClass().getResource("/Raiders/2.png").toExternalForm());
        Image image3 = new Image(getClass().getResource("/Raiders/3.png").toExternalForm());
        Image image4 = new Image(getClass().getResource("/Raiders/4.png").toExternalForm());
        Image image5 = new Image(getClass().getResource("/Raiders/5.png").toExternalForm());
        Image image7 = new Image(getClass().getResource("/Raiders/7.png").toExternalForm());
        Image image8 = new Image(getClass().getResource("/Raiders/8.png").toExternalForm());
        Image image9 = new Image(getClass().getResource("/Raiders/9.png").toExternalForm());
        Image image10 = new Image(getClass().getResource("/Raiders/10.png").toExternalForm());
        //Image image11 = new Image(getClass().getResource("/Raiders/11.png").toExternalForm());
        getHeroImages().add(image1);
        getHeroImages().add(image2);
        getHeroImages().add(image3);
        getHeroImages().add(image4);
        getHeroImages().add(image5);
        getHeroImages().add(image7);
        getHeroImages().add(image8);
        getHeroImages().add(image9);
        getHeroImages().add(image10);
        //getHeroImages().add(image11);
    }
}
