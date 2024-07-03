package Models;

import Models.Spells.Spell;
import com.example.gameproject.SQL.SQLController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private int ID;
    private String userName;
    private String password;
    private int level;
    private int diamonds;
    private HashMap<Spell,Integer> backPack;
    public Player(String userName,String password) throws SQLException {
        this.ID=SQLController.getMaxID()+1;
        this.userName=userName;
        this.password=password;
        this.level=1;
        this.backPack=new HashMap<>();
        this.diamonds=1000;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }


    public int getDiamonds() {
        return diamonds;
    }

    public int getLevel() {
        return level;
    }


    public void setDiamonds(int diamonds) {
        this.diamonds = diamonds;
    }

    public void setBackPack(HashMap backPack) {
        this.backPack = backPack;
    }

    public HashMap getBackPack() {
        return backPack;
    }
}
