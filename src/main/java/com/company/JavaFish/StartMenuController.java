package com.company.JavaFish;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    private TextField lifetimeGoldTextField;
    @FXML
    private TextField lifetimeGuppyTextField;

    @FXML
    void exitMenuButtonClick(ActionEvent event) {
        int flag = 1;
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Wrong input!\nThink about it ☺", ButtonType.YES);
        try {
            Habitat.setN1(Integer.parseInt(goldenFishTextField.getText()));
        } catch (NumberFormatException e) {
            alert.showAndWait();
            flag = 0;
            Habitat.setN1(5);
            goldenFishTextField.setText("5");
        }
        try {
            Habitat.setN2(Integer.parseInt(guppyFishTextField.getText()));
        } catch (NumberFormatException e) {
            alert.showAndWait();
            flag = 0;
            guppyFishTextField.setText("2");
            Habitat.setN2(2);
        }
        try {
            GoldenFish.lifeTime = Long.parseLong(lifetimeGoldTextField.getText());
        }catch (NumberFormatException e){
            alert.showAndWait();
            flag = 0;
            lifetimeGoldTextField.setText("10");
            GoldenFish.lifeTime = 10;
        }
        try {
            GuppyFish.lifeTime = Long.parseLong(lifetimeGuppyTextField.getText());
        }catch (NumberFormatException e){
            alert.showAndWait();
            flag = 0;
            lifetimeGuppyTextField.setText("20");
            GuppyFish.lifeTime = 20;
        }
        if (flag == 1) {
            startMenu.setVisible(false);
            startMenu.setDisable(true);
            StartMenu.getInstance().getStage().close();
        }
    }

    @FXML
    void initialize() {
        ArrayList<String> comboBoxTexts = new ArrayList<>();
        for (int i = 10; i <= 100; i += 10)
            comboBoxTexts.add(Integer.toString(i));
        goldenFishComboBox.getItems().addAll(comboBoxTexts);
        guppyFishComboBox.getItems().addAll(comboBoxTexts);
        goldenFishComboBox.getSelectionModel().select(3);
        guppyFishComboBox.getSelectionModel().select(5);
        goldenFishTextField.setText("5");
        guppyFishTextField.setText("2");
        startMenu.setContent(null);
        startMenu.setGraphic(null);
        lifetimeGoldTextField.setText("10");
        lifetimeGuppyTextField.setText("20");
    }

}
