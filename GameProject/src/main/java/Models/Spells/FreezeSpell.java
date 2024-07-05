package Models.Spells;

public class FreezeSpell extends AbstractSpell {
    private final int freezeDuration;

    public FreezeSpell(String name, int price, int freezeDuration) {
        super(name, price);
        this.freezeDuration = freezeDuration;
    }

    @Override
    public void drop() {
        System.out.println("All attackers are frozen for " + freezeDuration + " seconds.");
    }

    public int getFreezeDuration() {
        return freezeDuration;
    }
}