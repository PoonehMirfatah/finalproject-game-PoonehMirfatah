package com.example.gameproject;

import Models.Map;
import Models.Position;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.QuadCurveTo;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Map1Controller implements Initializable {
    @FXML
    private Label coinsLB;

    @FXML
    private Label heartLB;

    @FXML
    private Button startBT;

    @FXML
    private ImageView towerPoint1;

    @FXML
    private ImageView towerPoint2;

    @FXML
    private ImageView towerPoint3;
    @FXML
    private QuadCurve ps1;

    @FXML
    private QuadCurve ps2;

    @FXML
    private QuadCurve ps3;

    @FXML
    private QuadCurve ps4;

    @FXML
    private QuadCurve ps5;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        ArrayList<Position> towers=new ArrayList<>();
//        Position p1=new Position(towerPoint1.getX(),towerPoint1.getY());
//        Position p2=new Position(towerPoint2.getX(),towerPoint2.getY());
//        Position p3=new Position(towerPoint3.getX(),towerPoint3.getY());
//        Position end=new Position(409,634);
//        towers.add(p1);
//        towers.add(p2);
//        towers.add(p3);
//        Path path = new Path();
//        path.getElements().add(new MoveTo(547, -2));
//        path.getElements().add(new QuadCurveTo(ps1.getControlX(),ps1.getControlY(),ps1.getEndX(),ps1.getEndY()));
//        Map map1=new Map(towers,path,end,);
        heartLB.setText("25");
        coinsLB.setText("1000");
    }

    @FXML
    void buildTower(MouseEvent event) {

    }
    @FXML
    void startAttack(MouseEvent event) {

    }


}
