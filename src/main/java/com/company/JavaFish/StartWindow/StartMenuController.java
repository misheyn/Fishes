package com.company.JavaFish.StartWindow;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.company.JavaFish.Models.GoldenFish;
import com.company.JavaFish.Models.GuppyFish;
import com.company.JavaFish.MainWindow.Habitat;
import com.company.JavaFish.PropertiesPackage;
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
    private Button getPropertiesButton;
    @FXML
    private TextField IPTextField;
    @FXML
    private ComboBox<String> clientComboBox;
    @FXML
    private TextField loginTextField;

    @FXML
    void exitMenuButtonClick(ActionEvent event) {
        int flag = 1;
        try {
            Habitat.P1 = Integer.parseInt(goldenFishComboBox.getValue());
        } catch (NumberFormatException e) {
            Habitat.P1 = 40;
        }
        try {
            Habitat.P2 = Integer.parseInt(guppyFishComboBox.getValue());
        } catch (NumberFormatException e) {
            Habitat.P2 = 60;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Wrong input!\nThink about it ☺", ButtonType.YES);
        try {
            Habitat.N1 = Integer.parseInt(goldenFishTextField.getText());
        } catch (NumberFormatException e) {
            alert.showAndWait();
            flag = 0;
            Habitat.N1 = 5;
            goldenFishTextField.setText("5");
        }
        try {
            Habitat.N2 = Integer.parseInt(guppyFishTextField.getText());
        } catch (NumberFormatException e) {
            alert.showAndWait();
            flag = 0;
            guppyFishTextField.setText("2");
            Habitat.N2 = 2;
        }
        try {
            GoldenFish.lifeTime = Long.parseLong(lifetimeGoldTextField.getText());
        } catch (NumberFormatException e) {
            alert.showAndWait();
            flag = 0;
            lifetimeGoldTextField.setText("10");
            GoldenFish.lifeTime = 10;
        }
        try {
            GuppyFish.lifeTime = Long.parseLong(lifetimeGuppyTextField.getText());
        } catch (NumberFormatException e) {
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
    void getPropertiesButtonAction(ActionEvent event) throws IOException, ClassNotFoundException, InterruptedException {
        Habitat.getInstance().client.getProperties(propertiesPackage);
        Habitat.N1 = propertiesPackage.N1;
        Habitat.N2 = propertiesPackage.N2;
        Habitat.P1 = propertiesPackage.P1;
        Habitat.P2 = propertiesPackage.P2;
        GoldenFish.lifeTime = propertiesPackage.goldenLifeTime;
        GuppyFish.lifeTime = propertiesPackage.guppyLifeTime;
        //todo set data for view
        goldenFishComboBox.getSelectionModel().select(Habitat.P1 / 10 - 1);
        guppyFishComboBox.getSelectionModel().select(Habitat.P2 / 10 - 1);
        goldenFishTextField.setText(Integer.toString(Habitat.N1));
        guppyFishTextField.setText(Integer.toString(Habitat.N2));
        lifetimeGoldTextField.setText(Long.toString(GoldenFish.lifeTime));
        lifetimeGuppyTextField.setText(Long.toString(GuppyFish.lifeTime));
    }

    @FXML
    void setPropertiesButtonAction(ActionEvent event) throws IOException {
        int p1, p2, n1, n2;
        long lifeTime1, lifeTime2;
        try {
            p1 = Integer.parseInt(goldenFishComboBox.getValue());
        } catch (NumberFormatException e) {
            p1 = 40;
        }
        try {
            p2 = Integer.parseInt(guppyFishComboBox.getValue());
        } catch (NumberFormatException e) {
            p2 = 60;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Wrong input!\nThink about it ☺", ButtonType.YES);
        try {
            n1 = Integer.parseInt(goldenFishTextField.getText());
        } catch (NumberFormatException e) {
            alert.showAndWait();
            n1 = 5;
            goldenFishTextField.setText("5");
        }
        try {
            n2 = Integer.parseInt(guppyFishTextField.getText());
        } catch (NumberFormatException e) {
            alert.showAndWait();
            guppyFishTextField.setText("2");
            n2 = 2;
        }
        try {
            lifeTime1 = Long.parseLong(lifetimeGoldTextField.getText());
        } catch (NumberFormatException e) {
            alert.showAndWait();
            lifetimeGoldTextField.setText("10");
            lifeTime1 = 10;
        }
        try {
            lifeTime2 = Long.parseLong(lifetimeGuppyTextField.getText());
        } catch (NumberFormatException e) {
            alert.showAndWait();
            lifetimeGuppyTextField.setText("20");
            lifeTime2 = 20;
        }
        propertiesPackage.getProperties(n1, n2, p1, p2, lifeTime1, lifeTime2);
        Habitat.getInstance().client.sendProperties(propertiesPackage);
    }

    @FXML
    void initialize() {
        propertiesPackage = new PropertiesPackage();
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
        getPropertiesButton.setDisable(true);
        clientComboBox.setDisable(true);
    }

    private PropertiesPackage propertiesPackage;
}
