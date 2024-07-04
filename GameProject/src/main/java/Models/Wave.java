package Models;

import Models.Raiders.Raider;

import java.util.ArrayList;

public class Wave {
    private ArrayList<Raider> raiders=new ArrayList<>();
    private int raiderCount;
    public Wave(Raider raider,int raiderCount){
        for (int i = 0; i <raiderCount; i++) {
            raiders.add(raider);
        }
        this.raiderCount=raiderCount;
    }

    public int getRaiderCount() {
        return raiderCount;
    }

    public ArrayList<Raider> getRaiders() {
        return raiders;
    }

    public void setRaiderCount(int raiderCount) {
        this.raiderCount = raiderCount;
    }

    public void setRaiders(ArrayList<Raider> raiders) {
        this.raiders = raiders;
    }
}
