package Controllers;

import Models.Player;

public class PlayerController {

    private static PlayerController instance;

    public Player player;
    private PlayerController() {
    }
    public static  PlayerController getInstance() {
        if (instance == null) {
            instance = new PlayerController();
        }
        return instance;
    }

}
