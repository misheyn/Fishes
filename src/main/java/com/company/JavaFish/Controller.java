package com.company.JavaFish;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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
        assert label != null : "fx:id=\"label\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert statistic != null : "fx:id=\"statistic\" was not injected: check your FXML file 'hello-view.fxml'.";
//        assert text != null : "fx:id=\"statistic\" was not injected: check your FXML file 'hello-view.fxml'.";
    }

}
