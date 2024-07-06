package Models.Spells;

import Controllers.SpellsController;


public class HealthSpell extends Spell {
    private final int healthIncrease;

    public HealthSpell() {
        super("Health", 350);
        this.healthIncrease = 5;
    }

    public int getHealthIncrease() {
        return healthIncrease;
    }

}