package Models.Raiders;

import javafx.scene.image.Image;

import java.util.ArrayList;

public abstract class Raider {
    private int health;
    private int speed;
    private int loot;
    private ArrayList<Image> heroImages = new ArrayList<>();

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public ArrayList<Image> getHeroImages() {
        return heroImages;
    }

    public int getHealth() {
        return health;
    }

    public int getLoot() {
        return loot;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setHeroImages(ArrayList<Image> heroImages) {
        this.heroImages = heroImages;
    }

    public void setLoot(int loot) {
        this.loot = loot;
    }
}
