package Models;

import Models.Raiders.Raider;

public class Wave {
    private Raider raiders;
    private int raiderCount;
    public Wave(Raider raiders,int raiderCount){
        this.raiders=raiders;
        this.raiderCount=raiderCount;
    }

    public int getRaiderCount() {
        return raiderCount;
    }

    public Raider getRaiders() {
        return raiders;
    }

    public void setRaiderCount(int raiderCount) {
        this.raiderCount = raiderCount;
    }

    public void setRaiders(Raider raiders) {
        this.raiders = raiders;
    }
}
