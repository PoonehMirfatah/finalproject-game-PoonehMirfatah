package Models.Spells;

public abstract class AbstractSpell implements Spell {
    protected String name;
    protected int price;

    public AbstractSpell(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public abstract void drop();
    public String getName() {
        return name;
    }
}
