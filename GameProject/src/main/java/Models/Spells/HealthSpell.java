package Models.Spells;

public class HealthSpell extends Spell{
    public HealthSpell(String name, int price) {
        super(name, price);
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public void drop() {

    }
}
