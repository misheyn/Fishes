package com.company.JavaFish;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

public class MainController {

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
    private Button startButton;
    @FXML
    private Button stopButton;
    @FXML
    private RadioButton showRadioButton;
    @FXML
    private RadioButton hideRadioButton;
    @FXML
    private CheckBox resultWindowCheckBox;

    public Pane getPane() {
        return modelPane;
    }

    public Label getStatistic() {
        return statistic;
    }

    public void printLabel(String str) {
        label.setText(str);
    }

    public void printStatistic(String str) {
        statistic.setText(str);
    }

    public void switchButtonsOn() {
        startButton.setDisable(false);
        stopButton.setDisable(true);
    }

    public void switchButtonsOff() {
        startButton.setDisable(true);
        stopButton.setDisable(false);
    }

    @FXML
    void keyPressed(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.T)) {
            Habitat.getInstance().timeFlag = !Habitat.getInstance().timeFlag;
            if (Habitat.getInstance().timeFlag) {
                showRadioButton.setSelected(true);
                hideRadioButton.setSelected(false);
            } else {
                hideRadioButton.setSelected(true);
                showRadioButton.setSelected(false);
            }
        } else if (event.getCode().equals(KeyCode.B)) {
            if (!Habitat.startFlag) {
                switchButtonsOff();
                Habitat.getInstance().startAction();
            }
        } else if (event.getCode().equals(KeyCode.E)) {
            if (Habitat.startFlag) {
                switchButtonsOn();
                Habitat.getInstance().stopAction();
            }
        }
    }

    @FXML
    void hideRadioButtonClick(ActionEvent event) {
        Habitat.getInstance().timeFlag = false;
    }

    @FXML
    void showRadioButtonClick(ActionEvent event) {
        Habitat.getInstance().timeFlag = true;
    }

    @FXML
    void startButtonClick(ActionEvent event) {
        if (!Habitat.startFlag) {
            switchButtonsOff();
            Habitat.getInstance().startAction();
        }
    }

    @FXML
    void stopButtonClick(ActionEvent event) throws IOException {
        if (Habitat.startFlag) {
            switchButtonsOn();
            Habitat.getInstance().stopAction();
        }
    }

    @FXML
    void resultWindowCheckBoxClick(ActionEvent event) {
        Habitat.setResultWindowFlag();
    }

    @FXML
    void initialize() {
        resultWindowCheckBox.setSelected(true);
    }

}
