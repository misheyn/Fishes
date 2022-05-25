package com.company.JavaFish.MainWindow;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.company.JavaFish.FishLogWindow.FishLogWindow;
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
    private Label timerLabel;
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
    @FXML
    private Button awakeGoldenButton;
    @FXML
    private Button awakeGuppyButton;
    @FXML
    private Button waitGoldenButton;
    @FXML
    private Button waitGuppyButton;
    @FXML
    private Label clientCountLabel;
    @FXML
    private ComboBox<String> threadPriorityComboBox;

    public Pane getPane() {
        return modelPane;
    }

    public Label getStatistic() {
        return statistic;
    }

    public Label getTimerLabel() {
        return timerLabel;
    }

    public void printTimerLabel(String str) {
        timerLabel.setText(str);
    }

    public void printClientCountLabel(int count) {
        clientCountLabel.setText("Client count " + count);
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
            waitGuppyButton.setDisable(false);
            waitGoldenButton.setDisable(false);
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
            waitGoldenButton.setDisable(true);
            awakeGoldenButton.setDisable(true);
            waitGuppyButton.setDisable(true);
            awakeGuppyButton.setDisable(true);
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
    public void waitGoldenButtonClick(ActionEvent keyEvent) {
        synchronized (Habitat.getInstance().goldenThread) {
            Habitat.waitGolden = true;
            waitGoldenButton.setDisable(true);
            awakeGoldenButton.setDisable(false);
        }
    }

    @FXML
    public void waitGuppyButtonClick(ActionEvent keyEvent) {
        synchronized (Habitat.getInstance().guppyThread) {
            Habitat.waitGuppy = true;
            waitGuppyButton.setDisable(true);
            awakeGuppyButton.setDisable(false);
        }
    }

    @FXML
    public void awakeGuppyButtonClick(ActionEvent keyEvent) {
        synchronized (Habitat.getInstance().guppyThread) {
            Habitat.waitGuppy = false;
            try {
                Habitat.getInstance().guppyThread.notifyThread();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        awakeGuppyButton.setDisable(true);
        waitGuppyButton.setDisable(false);
    }

    @FXML
    public void awakeGoldenButtonClick(ActionEvent keyEvent) {
        synchronized (Habitat.getInstance().goldenThread) {
            Habitat.waitGolden = false;
            try {
                Habitat.getInstance().goldenThread.notifyThread();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            awakeGoldenButton.setDisable(true);
            waitGoldenButton.setDisable(false);
        }
    }

    @FXML
    void threadPriorityComboBoxSet(ActionEvent event) {
        if (threadPriorityComboBox.getValue().equals("GoldenThread")) {
            Habitat.getInstance().goldenThread.setPriority(4);
            Habitat.getInstance().guppyThread.setPriority(3);
        } else {
            Habitat.getInstance().guppyThread.setPriority(4);
            Habitat.getInstance().goldenThread.setPriority(3);
        }
    }

    @FXML
    void initialize() {
        clientCountLabel.setText("Client count " + Habitat.getInstance().clientCount);
        threadPriorityComboBox.getItems().add("GoldenThread");
        threadPriorityComboBox.getItems().add("GuppyThread");
        threadPriorityComboBox.getSelectionModel().select(0);
        waitGoldenButton.setDisable(true);
        awakeGoldenButton.setDisable(true);
        waitGuppyButton.setDisable(true);
        awakeGuppyButton.setDisable(true);
        stopButton.setDisable(true);
        resultWindowCheckBox.setSelected(true);
        unshowResultWindow.setDisable(false);
        showResultWindow.setDisable(true);
        timerLabel.setVisible(false);
        statistic.setVisible(false);
    }

}
