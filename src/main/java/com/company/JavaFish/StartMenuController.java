package com.company.JavaFish;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class StartMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exitMenuButton;

    @FXML
    private ComboBox<String> goldenFishComboBox;

    @FXML
    private TextField goldenFishTextField;

    @FXML
    private ComboBox<String> guppyFishComboBox;

    @FXML
    private TextField guppyFishTextField;

    @FXML
    private DialogPane startMenu;

    @FXML
    void exitMenuButtonClick(ActionEvent event) {
        try {
            Habitat.setP1(Integer.parseInt(goldenFishComboBox.getValue()));
        } catch (NumberFormatException e) {
            Habitat.setP1(40);
        }
        try {
            Habitat.setP2(Integer.parseInt(guppyFishComboBox.getValue()));
        } catch (NumberFormatException e) {
            Habitat.setP2(60);
        }
        try {
            Habitat.setN1(Integer.parseInt(goldenFishTextField.getText()));
        } catch (NumberFormatException e) {
            Habitat.setN1(5);
        }
        try {
            Habitat.setN2(Integer.parseInt(guppyFishTextField.getText()));
        } catch (NumberFormatException e) {
            Habitat.setN2(2);
        }

        startMenu.setVisible(false);
        startMenu.setDisable(true);
        StartMenu.getInstance().getStage().close();
    }

    public Pane getStartMenu() {
        return startMenu;
    }

    public ComboBox<String> getGoldComboBox() {
        return goldenFishComboBox;
    }

    public ComboBox<String> getGuppyComboBox() {
        return guppyFishComboBox;
    }

    @FXML
    void initialize() {
        ArrayList<String> comboBoxTexts = new ArrayList<>();
        for (int i = 10; i <= 100; i += 10)
            comboBoxTexts.add(Integer.toString(i));
        goldenFishComboBox.getItems().addAll(comboBoxTexts);
        guppyFishComboBox.getItems().addAll(comboBoxTexts);
        startMenu.setContent(null);
        startMenu.setGraphic(null);
    }

}
