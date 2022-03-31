package com.company.JavaFish;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

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

    public Pane getPane() {
        return modelPane;
    }

    public Label getStatistic() {
        return statistic;
    }

    public Label getTimeLabel() {
        return label;
    }

    public void printLabel(String str) {
        label.setText(str);
    }

    public void printStatistic(String str) {
        statistic.setText(str);
    }

    @FXML
    void keyPressed(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.T)) {
            Habitat.getInstance().timeFlag = !Habitat.getInstance().timeFlag;
        } else if (event.getCode().equals(KeyCode.B)) {
            if (!Habitat.startFlag) {
                Habitat.getInstance().startAction();
            }
        } else if (event.getCode().equals(KeyCode.E)) {
            if (Habitat.startFlag) {
                Habitat.getInstance().stopAction();
            }
        }
    }

    @FXML
    void initialize() {
    }

}