package com.company.JavaFish;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class StartMenu {
    public static void showMenu() throws IOException {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(StartMenu.class.getResource("startMenu.fxml"));
        controller = fxmlLoader.getController();
        Scene root = new Scene(fxmlLoader.load(), 400, 400);
        window.setScene(root);
        window.setTitle("StartMenu");
        window.showAndWait();
    }

    public StartMenuController getController() {
        return controller;
    }

    public StartMenu() {

    }

    public static StartMenu getInstance() {
        StartMenu localInstance = instance;
        if (localInstance == null) {
            synchronized (StartMenu.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new StartMenu();
                }
            }
        }
        return localInstance;
    }

    private static volatile StartMenu instance;

    public Stage getStage() {
        return window;
    }

    private static Stage window;
    private static StartMenuController controller;
    private int P1, P2;
}
