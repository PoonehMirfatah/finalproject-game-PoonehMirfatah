package com.example.gameproject.SQL;

import Models.Player;
import Models.Spell;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLController {
    public static int getMaxID() throws SQLException {
        String sqlCmd="Select MAX(ID) from programmers";
        SQLConnection sql=new SQLConnection();
        ResultSet rs=sql.executeQuery(sqlCmd);
        if(rs.next()){
            return rs.getInt(1);
        }else {
            return 0;
        }
    }
    public static void insertPlayer(Player player) throws Exception {
        SQLConnection sql = new SQLConnection();
        String SQLcom = String.format("INSERT INTO players (ID, UserName, Password, Level, Diamonds) VALUES (%s, '%s','%s','%s', %s)", player.getID(),player.getUserName(),player.getPassword(),player.getLevel(),player.getDiamonds());
//        for(Spell spell:player.getBackPack()){
//        String SQLcom2 = String.format("INSERT INTO backpacks (ID,SpellName) VALUES (%s,'%s')",player.getID(),spell.toString());
//            sql.executeSQL(SQLcom2);
//        }
        sql.executeSQL(SQLcom);

    }
}
