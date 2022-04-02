package com.company.JavaFish;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class FishLogWindow {

    public FishLogWindow() {
    }

    public static void showWindow() throws IOException {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(StartMenu.class.getResource("fishLogWindow.fxml"));
        fxmlLoader.getRoot();
        Scene root = new Scene(fxmlLoader.load());
        window.setScene(root);
        window.setTitle("Living Fishes Window");
        window.showAndWait();
    }

    public Stage getStage() {
        return window;
    }

    public static FishLogWindow getInstance() {
        FishLogWindow localInstance = instance;
        if (localInstance == null) {
            synchronized (FishLogWindow.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new FishLogWindow();
                }
            }
        }
        return localInstance;
    }

    private static volatile FishLogWindow instance;
    private static Stage window;

}
