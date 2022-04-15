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
    @FXML
    private MenuItem stopMenu;
    @FXML
    private MenuItem startMenu;
    @FXML
    private MenuItem showMenu;
    @FXML
    private MenuItem hideMenu;
    @FXML
    private MenuItem showResultWindow;
    @FXML
    private MenuItem unshowResultWindow;
    @FXML
    private Button currentObjectsButton;

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

    public void switchMenuItemOn() {
        startMenu.setDisable(false);
        stopMenu.setDisable(true);
    }

    public void switchMenuItemOff() {
        startMenu.setDisable(true);
        stopMenu.setDisable(false);
    }

    @FXML
    void keyPressed(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.T)) {
            Habitat.getInstance().timeFlag = !Habitat.getInstance().timeFlag;
            if (Habitat.getInstance().timeFlag) {
                showRadioButton.setSelected(true);
                hideRadioButton.setSelected(false);
                showMenu.setDisable(true);
                hideMenu.setDisable(false);
            } else {
                hideRadioButton.setSelected(true);
                showRadioButton.setSelected(false);
                showMenu.setDisable(false);
                hideMenu.setDisable(true);
            }
        } else if (event.getCode().equals(KeyCode.B)) {
            if (!Habitat.startFlag) {
                switchButtonsOff();
                switchMenuItemOff();
                Habitat.getInstance().startAction();
            }
        } else if (event.getCode().equals(KeyCode.E)) {
            if (Habitat.startFlag) {
                switchButtonsOn();
                switchMenuItemOn();
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
    void hideMenuItemAction(ActionEvent event) {
        Habitat.getInstance().timeFlag = false;
        showMenu.setDisable(false);
        hideMenu.setDisable(true);
        hideRadioButton.setSelected(true);
        showRadioButton.setSelected(false);
    }

    @FXML
    void showMenuItemAction(ActionEvent event) {
        Habitat.getInstance().timeFlag = true;
        showMenu.setDisable(true);
        hideMenu.setDisable(false);
        showRadioButton.setSelected(true);
        hideRadioButton.setSelected(false);
    }

    @FXML
    void startButtonClick(ActionEvent event) {
        if (!Habitat.startFlag) {
            switchButtonsOff();
            switchMenuItemOff();
            Habitat.getInstance().startAction();
        }
    }

    @FXML
    void stopButtonClick(ActionEvent event) throws IOException {
        if (Habitat.startFlag) {
            switchButtonsOn();
            switchMenuItemOn();
            Habitat.getInstance().stopAction();
        }
    }

    @FXML
    void resultWindowCheckBoxClick(ActionEvent event) {
        Habitat.setResultWindowFlag();
        if (Habitat.getResultWindowFlag()) {
            showResultWindow.setDisable(true);
            unshowResultWindow.setDisable(false);
        } else {
            unshowResultWindow.setDisable(true);
            showResultWindow.setDisable(false);
        }
    }

    @FXML
    void notShowResultAction(ActionEvent event) {
        Habitat.setResultWindowFlag();
        resultWindowCheckBox.setSelected(false);
        unshowResultWindow.setDisable(true);
        showResultWindow.setDisable(false);
    }

    @FXML
    void showResultAction(ActionEvent event) {
        Habitat.setResultWindowFlag();
        resultWindowCheckBox.setSelected(true);
        unshowResultWindow.setDisable(false);
        showResultWindow.setDisable(true);
    }

    @FXML
    void currentObjectsButtonClick(ActionEvent event) throws IOException {
        Habitat.startFlag = !Habitat.startFlag;
        FishLogWindow.showWindow();
        Habitat.startFlag = !Habitat.startFlag;
        Habitat.getInstance().startTime += System.currentTimeMillis() - FishLogWindow.getInstance().startTime;
    }

    @FXML
    void initialize() {
        stopButton.setDisable(true);
        resultWindowCheckBox.setSelected(true);
        unshowResultWindow.setDisable(false);
        showResultWindow.setDisable(true);
    }


    public void waitGoldenButtonClick(KeyEvent keyEvent) {
    }

    public void awakeGuppyButtonClick(KeyEvent keyEvent) {
    }

    public void waitGuppyButtonClick(KeyEvent keyEvent) {
    }

    public void awakeGoldenButtonClick(KeyEvent keyEvent) {
    }
}
