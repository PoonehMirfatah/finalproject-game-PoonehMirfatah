package Models.Spells;

public class CoinsSpell extends Spell{
    public CoinsSpell(String name, int price) {
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
