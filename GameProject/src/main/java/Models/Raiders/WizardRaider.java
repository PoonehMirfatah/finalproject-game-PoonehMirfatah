package Models.Raiders;

import javafx.scene.image.Image;

public class WizardRaider extends Raider {
    public WizardRaider() {
        setHealth(200);
        setLoot(80);
        setSpeed(1d / 20d);
        setDead(false);
        Image image1 = new Image(getClass().getResource("/Raiders/wizardRaider (1).png").toExternalForm());
        Image image2 = new Image(getClass().getResource("/Raiders/wizardRaider (2).png").toExternalForm());
        Image image3 = new Image(getClass().getResource("/Raiders/wizardRaider (3).png").toExternalForm());
        Image image4 = new Image(getClass().getResource("/Raiders/wizardRaider (4).png").toExternalForm());
        Image image5 = new Image(getClass().getResource("/Raiders/wizardRaider (5).png").toExternalForm());
        Image image6 = new Image(getClass().getResource("/Raiders/wizardRaider (6).png").toExternalForm());
        Image image7 = new Image(getClass().getResource("/Raiders/wizardRaider (7).png").toExternalForm());
        Image image8 = new Image(getClass().getResource("/Raiders/wizardRaider (8).png").toExternalForm());
        Image image9 = new Image(getClass().getResource("/Raiders/wizardRaider (9).png").toExternalForm());
        Image image10 = new Image(getClass().getResource("/Raiders/wizardRaider (10).png").toExternalForm());

        getHeroImages().add(image1);
        getHeroImages().add(image2);
        getHeroImages().add(image3);
        getHeroImages().add(image4);
        getHeroImages().add(image5);
        getHeroImages().add(image6);
        getHeroImages().add(image7);
        getHeroImages().add(image8);
        getHeroImages().add(image9);
        getHeroImages().add(image10);
    }
}

