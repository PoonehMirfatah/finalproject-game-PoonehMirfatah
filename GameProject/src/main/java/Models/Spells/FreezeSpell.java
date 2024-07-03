package Models.Spells;

public class FreezeSpell extends Spell{
    public FreezeSpell(String name, int price) {
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
