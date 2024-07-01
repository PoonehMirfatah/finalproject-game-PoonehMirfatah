package Models;

import java.nio.file.Path;
import java.util.ArrayList;

public class Map {
    final ArrayList<Position> towerPoints;
     Path path;
    final Position endPoint;
    final ArrayList<Wave> attackWave;
    final int coins;


    public Map(ArrayList<Position> towerPoints, Position endPoint,ArrayList<Wave> attackWave, int coins) {
        this.towerPoints = towerPoints;
        this.endPoint = endPoint;
        this.attackWave = attackWave;
        this.coins = coins;
    }
}
