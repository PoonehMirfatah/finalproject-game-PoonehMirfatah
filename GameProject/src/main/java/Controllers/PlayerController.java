package Controllers;

import Controllers.SQL.SQLController;
import Models.Player;

public class PlayerController {

    private static PlayerController instance;

    private  static Player player;
    private PlayerController() {
    }
    public static  PlayerController getInstance() {
        if (instance == null) {
            instance = new PlayerController();
        }
        return instance;
    }

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        PlayerController.player = player;
    }

    public void updateSpells() throws Exception {
        SQLController.deletePlayerSpells(player.getID());
        for (String spellName : player.getBackPack().keySet()) {
            int count = player.getBackPack().get(spellName);
            SQLController.insertSpell(spellName, count);
        }
        SQLController.updatePlayer(player.getID());
    }
}
