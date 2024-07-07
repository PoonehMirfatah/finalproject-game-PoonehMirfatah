module com.example.gameproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.media;
    requires mysql.connector.java;


    opens com.example.gameproject to javafx.fxml;
    exports com.example.gameproject;
}