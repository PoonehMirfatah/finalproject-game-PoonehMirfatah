package Models;

import javafx.scene.shape.Path;

import java.util.ArrayList;

public class Map {
    private final ArrayList<Position> towerPoints;
    private final javafx.scene.shape.Path path;
    private final Position endPoint;
    private final ArrayList<Wave> attackWave;
    private final int coins;
    private final int health;
    private int waveCounter = 0;

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
}

