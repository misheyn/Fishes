package com.company.JavaFish;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label label;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label statistic;

    @FXML
    void keyPressed(KeyEvent event) {

    }

    @FXML
    void keyReleased(KeyEvent event) {

    }

    @FXML
    void keyTyped(KeyEvent event) {

    }

    @FXML
    private Pane modelPane;
    @FXML
    private ImageView image;

    public Pane getPane() {
        return modelPane;
    }

    @FXML
    void mouseClick(MouseEvent event) {

    }

    public void printLabel(String str) {
        label.setText(str);
    }

    public void printStatistic(String str) {
        statistic.setText(str);
    }

    @FXML
    void initialize() {
    }

}
