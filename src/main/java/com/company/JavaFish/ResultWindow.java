package com.company.JavaFish;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ResultWindow {

    public ResultWindow() {
    }

    public static void showMenu() throws IOException {
        getInstance().showStartMenuFlag = false;
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(StartMenu.class.getResource("resultWindow.fxml"));
        fxmlLoader.getRoot();
        Scene root = new Scene(fxmlLoader.load());
        window.setScene(root);
        window.setTitle("Result Window");
        window.showAndWait();
        if (getInstance().showStartMenuFlag) StartMenu.showMenu();
    }

    public Stage getStage() {
        return window;
    }

    public static ResultWindow getInstance() {
        ResultWindow localInstance = instance;
        if (localInstance == null) {
            synchronized (ResultWindow.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ResultWindow();
                }
            }
        }
        return localInstance;
    }

    public boolean showStartMenuFlag;
    private static volatile ResultWindow instance;
    private static Stage window;
}
