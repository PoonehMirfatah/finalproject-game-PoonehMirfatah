package Models.Spells;

import Controllers.SpellsController;
import Models.Map;

public class FreezeSpell extends Spell {
    private final int freezeDuration;

    public FreezeSpell() {
        super("Freeze", 50);
        this.freezeDuration =  5;
    }

    public int getFreezeDuration() {
        return freezeDuration;
    }

}