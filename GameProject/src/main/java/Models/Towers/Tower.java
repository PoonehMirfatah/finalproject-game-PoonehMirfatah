package Models.Towers;

public abstract class Tower  {
    private int destroyPower;
    private int bulidCost;
    private int range;
    private String image;
    private int level;

    public Tower(int destroyPower, int bulidCost, int range,int level){
        this.bulidCost=bulidCost;
        this.destroyPower=destroyPower;
        this.range=range;
        this.level=level;
    }
    public Tower(){
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

    public int getBulidCost() {
        return bulidCost;
    }

    public void setBulidCost(int bulidCost) {
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
