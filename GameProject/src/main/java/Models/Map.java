package Models;

import Models.Raiders.Raider;
import Models.Towers.Tower;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Path;

import java.util.*;

public class Map {
    private final ArrayList<Position> towerPoints;
    private final javafx.scene.shape.Path path;
    private final Position endPoint;
    private final ArrayList<Wave> attackWave;
    private final int coins;
    private final int health;
    private int waveCounter = 0;
    private HashMap<ImageView,Tower> towersList=new HashMap<>();
    //
    List<Timeline> timelines = new ArrayList<>();
    private boolean firstAttack = true;
    List<PathTransition> pathTransitions = new ArrayList<>();
    List<Position> damagePoints=new ArrayList<>();
    List<Raider> aliveRaiders=new ArrayList<>();
    Set<ImageView> activeTowers = new HashSet<>();

    public Map(ArrayList<Position> towerPoints, javafx.scene.shape.Path path, Position endPoint, ArrayList<Wave> attackWave, int coins, int health) {
        this.towerPoints = towerPoints;
        this.path = path;
        this.endPoint = endPoint;
        this.attackWave = attackWave;
        this.coins = coins;
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public ArrayList<Position> getTowerPoints() {
        return towerPoints;
    }

    public ArrayList<Wave> getAttackWave() {
        return attackWave;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public int getCoins() {
        return coins;
    }

    public  int getWaveCounter() {
        return waveCounter;
    }

    public Path getPath() {
        return path;
    }

    public Position getEndPoint() {
        return endPoint;
    }

    public void setWaveCounter(int waveCounter) {
        this.waveCounter = waveCounter;
    }

    public void setTowersList(HashMap<ImageView, Tower> towersList) {
        this.towersList = towersList;
    }

    public HashMap<ImageView, Tower> getTowersList() {
        return towersList;
    }

    public boolean isFirstAttack() {
        return firstAttack;
    }



    public List<PathTransition> getPathTransitions() {
        return pathTransitions;
    }

    public List<Position> getDamagePoints() {
        return damagePoints;
    }

    public List<Raider> getAliveRaiders() {
        return aliveRaiders;
    }

    public List<Timeline> getTimelines() {
        return timelines;
    }

    public Set<ImageView> getActiveTowers() {
        return activeTowers;
    }

    public void setActiveTowers(Set<ImageView> activeTowers) {
        this.activeTowers = activeTowers;
    }

    public void setAliveRaiders(List<Raider> aliveRaiders) {
        this.aliveRaiders = aliveRaiders;
    }

    public void setDamagePoints(List<Position> damagePoints) {
        this.damagePoints = damagePoints;
    }

    public void setFirstAttack(boolean firstAttack) {
        this.firstAttack = firstAttack;
    }

    public void setPathTransitions(List<PathTransition> pathTransitions) {
        this.pathTransitions = pathTransitions;
    }

    public void setTimelines(List<Timeline> timelines) {
        this.timelines = timelines;
    }

}

