package Models;

import java.nio.file.Path;
import java.util.ArrayList;

public class Map {
    final ArrayList<Position> towerPoints;
    final javafx.scene.shape.Path path;
    final Position endPoint;
    final ArrayList<Wave> attackWave;
    final int coins;
    final int health;


    public Map(ArrayList<Position> towerPoints, javafx.scene.shape.Path path, Position endPoint, ArrayList<Wave> attackWave, int coins, int health) {
        this.towerPoints = towerPoints;
        this.path = path;
        this.endPoint = endPoint;
        this.attackWave = attackWave;
        this.coins = coins;
        this.health = health;
    }
}
