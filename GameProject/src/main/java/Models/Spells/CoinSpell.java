package Models.Spells;

import Models.Spells.AbstractSpell;

public class CoinSpell extends AbstractSpell {
    private final int coinIncrease;

    public CoinSpell(String name, int price, int coinIncrease) {
        super(name, price);
        this.coinIncrease = coinIncrease;
    }

    @Override
    public void drop() {
        System.out.println("Player coins increased by " + coinIncrease + " units.");
    }

    public int getCoinIncrease() {
        return coinIncrease;
    }

}
