module com.example.gameproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.gameproject to javafx.fxml;
    exports com.example.gameproject;
}