package Models.Towers;

import Models.Towers.Tower;

public class Artillery extends Tower {
    int damageRange;
    public Artillery(int destroyPower, int bulidCost, int range,int level,int damageRange) {
        super(destroyPower, bulidCost, range,level);
        this.damageRange=damageRange;
    }

    public int getDamageRange() {
        return damageRange;
    }

    public void setDamageRange(int damageRange) {
        this.damageRange = damageRange;
    }
}
