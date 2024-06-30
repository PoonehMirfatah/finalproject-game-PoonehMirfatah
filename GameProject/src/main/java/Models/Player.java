package Models;

import com.example.gameproject.SQL.SQLController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;

public class Player {
    private int ID;
    private String userName;
    private String password;
    private int level;
    private int diamonds;
    private ArrayList<Spell> backPack;
    public Player(String userName,String password) throws SQLException {
        this.ID=SQLController.getMaxID()+1;
        this.userName=userName;
        this.password=password;
        this.level=1;
        this.backPack=new ArrayList<>();
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


    public ArrayList<Spell> getBackPack() {
        return backPack;
    }

    public int getDiamonds() {
        return diamonds;
    }

    public int getLevel() {
        return level;
    }

    public void setBackPack(ArrayList<Spell> backPack) {
        this.backPack = backPack;
    }

    public void setDiamonds(int diamonds) {
        this.diamonds = diamonds;
    }
}
