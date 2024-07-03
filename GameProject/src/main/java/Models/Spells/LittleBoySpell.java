package Models.Spells;

public class LittleBoySpell extends AbstractSpell {

    public LittleBoySpell(String name, int price) {
        super(name, price);
    }

    @Override
    public void drop() {
        System.out.println("All attackers in the path are destroyed.");
    }
}