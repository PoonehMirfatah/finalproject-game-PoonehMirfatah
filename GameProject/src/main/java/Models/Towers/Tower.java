package Models.Towers;

public abstract class Tower  {
    private int destroyPower;
    private long bulidCost;
    private int range;

    public Tower(int destroyPower,long bulidCost,int range){
        this.bulidCost=bulidCost;
        this.destroyPower=destroyPower;
        this.range=range;
    }
    public void setDestroyPower(int destroyPower) {
        this.destroyPower = destroyPower;
    }

    public int getDestroyPower() {
        return destroyPower;
    }

    public int getRange() {
        return range;
    }

    public long getBulidCost() {
        return bulidCost;
    }

    public void setBulidCost(long bulidCost) {
        this.bulidCost = bulidCost;
    }

    public void setRange(int range) {
        this.range = range;
    }

}
