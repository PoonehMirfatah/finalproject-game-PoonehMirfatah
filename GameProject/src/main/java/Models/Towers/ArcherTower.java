package Models.Towers;

public class ArcherTower extends Tower {
    public ArcherTower(int destroyPower, int bulidCost, int range) {
        super(destroyPower, bulidCost, range);
    }
    public ArcherTower(){
        setDestroyPower(100);
        setBulidCost(70);
        setRange(20);
    }
}
