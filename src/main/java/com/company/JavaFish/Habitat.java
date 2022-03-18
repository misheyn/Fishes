package com.company.JavaFish;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Habitat extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        root = new Scene(fxmlLoader.load(), 900, 750);
        Pane group = fxmlLoader.getRoot();
        controller = fxmlLoader.getController();
        Image img = new Image(new FileInputStream("src/image/aquarium.png"));
        BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        group.setBackground(bGround);
        timeFlag = false;
        statFlag = false;
        N1 = 5;
        N2 = 2;
        P1 = 40;
        P2 = 60;
        timer = new Timer();
        listFish = new ArrayList<>();
        startTime = System.currentTimeMillis();
        keyEventsHandler();
        controller.getStatistic().setVisible(true);
        stage.setTitle("Aquarium fishes");
        stage.setScene(root);
        stage.getIcons().add(new Image(new FileInputStream("src/image/fill.png")));
        stage.show();
    }

    private void keyEventsHandler() {
        root.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().equals(KeyCode.T)) {
                timeFlag = !timeFlag;
            }
        });
        root.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().equals(KeyCode.B)) {
                statFlag = false;
                controller.getStatistic().setVisible(false);
                startTime = System.currentTimeMillis();
                startCycle();
            }
        });
        root.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().equals(KeyCode.E)) {
                statFlag = !statFlag;
                controller.getStatistic().setVisible(true);
                update(System.currentTimeMillis() - startTime);
                timer.cancel();
                timer = new Timer();
                clearListFish();
                startTime = System.currentTimeMillis();
            }
        });
    }

    private void startCycle() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> update(System.currentTimeMillis() - startTime));

            }
        }, 0, 1000);
    }

    private void clearListFish() {

        listFish.forEach((tmp) -> controller.getPane().getChildren().remove(tmp.getImageView()));
        listFish.clear();
    }

    private void update(long currentTime) {
        Random random = new Random();
        int P;
        if ((currentTime / 1000) % N1 == 0) {
            P = random.nextInt(300);
            if (P1 <= P) {
                try {
                    bornGold();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        if ((currentTime / 1000) % N2 == 0) {
            P = random.nextInt(300);
            if (P2 <= P) {
                try {
                    bornGuppy();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        showLabels();
    }

    private void showLabels() {
        String timeStr = "", statStr;

        if (timeFlag) {
            if (startTime != 0) {
                long time = System.currentTimeMillis() - startTime;
                timeStr = "Time: " + time / 1000;
            } else {
                timeStr = "0";
            }
        }
        if (statFlag) {
            long finalTime = System.currentTimeMillis() - startTime;
            long countGold = listFish.stream().filter(i -> i instanceof GoldenFish).count();
            long countGuppy = listFish.stream().filter(i -> i instanceof GuppyFish).count();
            statStr = "Golden fish: " + countGold;
            statStr += "\nGuppy fish: " + countGuppy;
            statStr += "\nTime: " + finalTime / 1000;
        } else {
            statStr = "";
        }
        controller.printStatistic(statStr);
        controller.printLabel(timeStr);
    }

    private void bornGold() throws FileNotFoundException {
        Random random = new Random();
        GoldenFish fish = new GoldenFish(random.nextInt((int) root.getWidth() - 200), random.nextInt((int) root.getHeight() - 100));
        controller.getPane().getChildren().add(fish.getImageView());
        listFish.add(fish);
    }

    private void bornGuppy() throws FileNotFoundException {
        Random random = new Random();
        GuppyFish fish = new GuppyFish(random.nextInt((int) root.getWidth() - 200), random.nextInt((int) root.getHeight() - 100));
        controller.getPane().getChildren().add(fish.getImageView());
        listFish.add(fish);
    }

    public static void main(String[] args) {
        launch();
        timer.cancel();
    }

    private Scene root;
    private Controller controller;
    private static Timer timer;
    private boolean timeFlag;
    private boolean statFlag;
    private long startTime;
    private ArrayList<Fish> listFish;
    private int P1, P2;
    private int N1, N2;
}
