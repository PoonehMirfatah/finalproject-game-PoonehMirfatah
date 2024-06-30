package Models.Raiders;

public abstract class Raider {
    int health;
    int speed;
    int loot;
    int destroyedPath;
    public Raider(int health,int speed,int loot ,int destroyedPath){
        this.destroyedPath=destroyedPath;
        this.health=health;
        this.speed=speed;
        this.loot=loot;
    }
}
