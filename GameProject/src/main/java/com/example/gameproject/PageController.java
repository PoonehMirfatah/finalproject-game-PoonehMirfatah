package com.example.gameproject;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PageController {
    static AnchorPane root;
    static AnchorPane MainSection = new AnchorPane();
    static Stage stage;
    static Scene scene;
    public static void setstage(Event event, String fxml) throws IOException {
        root = FXMLLoader.load(PageController.class.getResource(fxml));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        root.getChildren().add(MainSection);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public static void showAlert(String title, String headerText,String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

