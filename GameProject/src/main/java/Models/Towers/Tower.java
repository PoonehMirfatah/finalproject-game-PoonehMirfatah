package Models.Towers;

public abstract class Tower  {
    private int destroyPower;
    private long bulidCost;
    private int range;
    private String image;

    public Tower(int destroyPower, long bulidCost, int range, String image){
        this.bulidCost=bulidCost;
        this.destroyPower=destroyPower;
        this.range=range;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
