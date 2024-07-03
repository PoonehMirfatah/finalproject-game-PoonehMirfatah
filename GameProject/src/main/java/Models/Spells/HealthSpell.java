package Models.Spells;

import Models.Spells.AbstractSpell;

public class HealthSpell extends AbstractSpell {
    private int healthIncrease;

    public HealthSpell(String name, int price, int healthIncrease) {
        super(name, price);
        this.healthIncrease = healthIncrease;
    }

    @Override
    public void drop() {
        System.out.println("Player health increased by " + healthIncrease + " units.");
    }

    public int getHealthIncrease() {
        return healthIncrease;
    }
}