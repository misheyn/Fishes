package com.company.JavaFish;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label label;

    @FXML
    private Label statistic;

    @FXML
    private AnchorPane pane;

    @FXML
    private Pane modelPane;

    @FXML
    void keyPressed(KeyEvent event) {

    }

    @FXML
    void keyReleased(KeyEvent event) {

    }

    @FXML
    void keyTyped(KeyEvent event) {

    }

    public Pane getPane() {
        return modelPane;
    }

    public void printLabel(String str) {
        label.setText(str);
    }

    public Label getStatistic() {
        return statistic;
    }

    public void printStatistic(String str) {
//        statistic.setBackground(new Background(new BackgroundFill(Color.rgb(1, 0, 1, 0.7), new CornerRadii(5.0), new Insets(-5.0))));
        statistic.setText(str);
//        statistic.setVisible(false);
    }

    @FXML
    void initialize() {

    }

}
