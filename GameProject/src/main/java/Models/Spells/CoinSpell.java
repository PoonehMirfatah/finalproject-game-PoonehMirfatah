package Models.Spells;

import Controllers.SpellsController;
import Models.Map;

public class CoinSpell extends Spell {
    private  int coinIncrease=200;

    public CoinSpell() {
        super("Coins", 850 );
    }

    public int getCoinIncrease() {
        return coinIncrease;
    }

    public void setCoinIncrease(int coinIncrease) {
        this.coinIncrease = coinIncrease;
    }
}
