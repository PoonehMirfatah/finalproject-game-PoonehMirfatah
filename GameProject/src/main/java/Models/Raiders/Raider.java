package Models.Raiders;

import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public abstract class Raider {
    private int health;
    private double speed;
    private int loot;
    private boolean isDead;
    private PathTransition pathTransition=new PathTransition();
    private ArrayList<Image> heroImages = new ArrayList<>();



    public PathTransition getPathTransition() {
        return pathTransition;
    }

    public void setPathTransition(PathTransition pathTransition) {
        this.pathTransition = pathTransition;
    }



    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
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

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}
