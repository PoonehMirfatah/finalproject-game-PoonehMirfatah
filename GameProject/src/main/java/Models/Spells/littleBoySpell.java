package Models.Spells;

public class littleBoySpell extends Spell{
    public littleBoySpell(String name, int price) {
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
