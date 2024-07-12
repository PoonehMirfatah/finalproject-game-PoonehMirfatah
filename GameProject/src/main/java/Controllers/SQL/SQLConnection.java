package Controllers.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class SQLConnection {
    String URL = "jdbc:mysql://localhost/game";
    String password = "123";
    String username = "root";

    Boolean executeSQL(String SQLcmd) throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, username, password);
            Statement s = con.prepareStatement(SQLcmd);
            s.execute(SQLcmd);
            con.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    ResultSet executeQuery(String SQLcmd) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, username, password);
            Statement s = con.prepareStatement(SQLcmd);
            ResultSet rs = s.executeQuery(SQLcmd);
            return rs;
        } catch (Exception e) {
            return null;
        }
    }
}
