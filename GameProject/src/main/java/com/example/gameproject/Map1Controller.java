package com.example.gameproject;

import Models.Map;
import Models.Position;
import Models.Towers.ArcherTower;
import Models.Towers.Artillery;
import Models.Towers.WizardTower;
import Models.Wave;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.QuadCurveTo;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Map1Controller implements Initializable {
    @FXML
    private GridPane UpgradeBox;

    @FXML
    private Label coinsLB;

    @FXML
    private Label destroyLootLB;

    @FXML
    private Label heartLB;

    @FXML
    private AnchorPane pane;

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

    @FXML
    private Button startBT;

    @FXML
    private Button t1;

    @FXML
    private Button t2;

    @FXML
    private Button t21;

    @FXML
    private Button t211;

    @FXML
    private Button t3;

    @FXML
    private Button t4;

    @FXML
    private ImageView towerPoint1;

    @FXML
    private ImageView towerPoint2;

    @FXML
    private ImageView towerPoint3;

    @FXML
    private GridPane towersBox;

    @FXML
    private Label upgradedCostLB;

    @FXML
    private ImageView upgradedTower;

    String towerID;
    Position clickedPosition;
    int coins;
    int health;
    ImageView point;
    String newPath;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        towersBox.setVisible(false);
        ArrayList<Position> towers=new ArrayList<>();
        Position p1=new Position(towerPoint1.getX(),towerPoint1.getY());
        Position p2=new Position(towerPoint2.getX(),towerPoint2.getY());
        Position p3=new Position(towerPoint3.getX(),towerPoint3.getY());
        Position end=new Position(409,634);
        towers.add(p1);
        towers.add(p2);
        towers.add(p3);
        ArrayList<Wave> attackWaves=new ArrayList<>();
        Path path = new Path();
        path.getElements().add(new MoveTo(547, -2));
        path.getElements().add(new QuadCurveTo(ps1.getControlX(),ps1.getControlY(),ps1.getEndX(),ps1.getEndY()));
        Map map1=new Map(towers,path,end,attackWaves,300,20);
        coins=300;
        health=25;
        heartLB.setText(String.format("%s/25",health));
        coinsLB.setText(String.valueOf(300));
    }

    @FXML
    void showTowers(MouseEvent event){
       ImageView clickedImage = (ImageView) event.getSource();
        towerID=clickedImage.getId();
        switch (towerID){
            case "towerPoint1":
                 point=towerPoint1;
                break;
            case "towerPoint2":
                point=towerPoint2;
                break;
            case "towerPoint3":
                point=towerPoint3;
                break;
        }
        towersBox.setVisible(true);
    }
    @FXML
    void buildTower(MouseEvent event) {
        Button clickedButton = (Button) event.getSource();
        if (clickedButton.getId()==null){
            return;
        }
        switch (clickedButton.getId()){
            case "t1":
               setTowerOnPosition("/Towers/1ArcherTower.png");
               ArcherTower archerTower=new ArcherTower(100,70,20);
               coins-=archerTower.getBulidCost();
                coinsLB.setText(String.valueOf(coins));
                break;
            case "t2":
                setTowerOnPosition("/Towers/Artillery.png");
                Artillery artillery=new Artillery(200,125,30);
                coins-=artillery.getBulidCost();
                coinsLB.setText(String.valueOf(coins));
                break;
            case "t3":
                setTowerOnPosition("/Towers/WizardTower.png");
                WizardTower wizardTower =new WizardTower(300,100,30);
                coins-=wizardTower.getBulidCost();
                coinsLB.setText(String.valueOf(coins));
                break;
            case "t4":
                setTowerOnPosition("/Towers/ArmyPlace.png");
                break;
            default:
                break;
        }
        towersBox.setVisible(false);
    }
    public void setTowerOnPosition(String towerPath){
        Image image=new Image(getClass().getResource(towerPath).toExternalForm());
        point.setImage(image);
        point.setOnMouseClicked(event ->{
            UpgradeBox.setVisible(true);
            int level = 0;
            char digitChar = 0;
            for (Character chr:towerPath.toCharArray()){
                if (Character.isDigit(chr)){
                    digitChar= chr;
                    level=Integer.parseInt(String.valueOf(digitChar));
                }
            }
            level+=1;
            String newLevel = String.valueOf(level);
            newPath = towerPath.replaceAll("\\d", newLevel);
            System.out.println(newPath);
            Image upgradedImage=new Image(getClass().getResource(newPath).toExternalForm());
            upgradedTower.setImage(upgradedImage);

        });
    }
    @FXML
    void startAttack(MouseEvent event) {

    }
    @FXML
    void destroyTower(ActionEvent event) {
        Image image=new Image(getClass().getResource("/Towers/Pointer.png").toExternalForm());
        point.setImage(image);
        point.setOnMouseClicked(
                this::showTowers
        );
        UpgradeBox.setVisible(false);
    }
    @FXML
    void upgradeTower(ActionEvent event) {
        setTowerOnPosition(newPath);
        UpgradeBox.setVisible(false);
        //cost Money
    }
}
