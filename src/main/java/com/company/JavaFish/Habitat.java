package com.company.JavaFish;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Habitat extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        instance = this;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));
        Scene root = new Scene(fxmlLoader.load(), 1100, 750);
        mainController = fxmlLoader.getController();
        Image img = new Image(new FileInputStream("src/image/aquarium.png"));
        BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        mainController.getPane().setBackground(bGround);
        initialize();
        mainController.getStatistic().setVisible(true);
        stage.setTitle("Aquarium fishes");
        stage.setScene(root);
        stage.getIcons().add(new Image(new FileInputStream("src/image/fill.png")));
        stage.show();
    }

    public void startAction() {
        startFlag = true;
        statFlag = false;
        startTime = System.currentTimeMillis();
        mainController.getStatistic().setVisible(false);
        startCycle();
    }

    public void stopAction() throws IOException {
        statFlag = true;
        startFlag = false;
        update(System.currentTimeMillis() - startTime);
        showStatLabel();
        if (!startFlag) {
            timer.cancel();
            timer = new Timer();
            clearListFish();
            startTime = System.currentTimeMillis();
            mainController.switchButtonsOn();
        }else{
            mainController.switchButtonsOff();
        }
    }

    private void startCycle() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    try {
                        update(System.currentTimeMillis() - startTime);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

            }
        }, 0, 1000);
    }

    private void clearListFish() {
        FishArr.getInstance().vector.forEach((tmp) -> mainController.getPane().getChildren().remove(tmp.getImageView()));
        FishArr.getInstance().vector.clear();
    }

    private void update(long currentTime) throws IOException {
        if (startFlag) {
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
            showTimeLabel();
        }
    }

    private void showTimeLabel() {
        String timeStr = "";

        if (timeFlag) {
            if (startTime != 0) {
                long time = System.currentTimeMillis() - startTime;
                timeStr = "Time: " + time / 1000;
            } else {
                timeStr = "0";
            }
        }
        mainController.printLabel(timeStr);
    }

    private void showStatLabel() throws IOException {
        if (statFlag) {
            long finalTime = System.currentTimeMillis() - startTime;
            long countGold = FishArr.getInstance().vector.stream().filter(i -> i instanceof GoldenFish).count();
            long countGuppy = FishArr.getInstance().vector.stream().filter(i -> i instanceof GuppyFish).count();
            statStr = "Golden fish: " + countGold;
            statStr += "\nGuppy fish: " + countGuppy;
            statStr += "\nTime: " + finalTime / 1000;
            if (!resultWindowFlag) {
                mainController.getStatistic().setVisible(true);
                mainController.printStatistic(statStr);
            } else {
                mainController.getStatistic().setVisible(false);
                ResultWindow.showMenu();
            }
        } else {
            statStr = "";
        }

    }

    private void bornGold() throws FileNotFoundException {
        Random random = new Random();
        GoldenFish fish = new GoldenFish(random.nextInt((int) mainController.getPane().getWidth() - 200), random.nextInt((int) mainController.getPane().getHeight() - 100));
        mainController.getPane().getChildren().add(fish.getImageView());
        FishArr.getInstance().vector.add(fish);
    }

    private void bornGuppy() throws FileNotFoundException {
        Random random = new Random();
        GuppyFish fish = new GuppyFish(random.nextInt((int) mainController.getPane().getWidth() - 200), random.nextInt((int) mainController.getPane().getHeight() - 100));
        mainController.getPane().getChildren().add(fish.getImageView());
        FishArr.getInstance().vector.add(fish);
    }

    public static void main(String[] args) {
        launch();
        timer.cancel();
    }

    private void initialize() throws IOException {
        timeFlag = false;
        statFlag = false;
        startFlag = false;
        resultWindowFlag = true;
        StartMenu.showMenu();
        timer = new Timer();
        startTime = System.currentTimeMillis();
    }

    public static void setN1(int n1) {
        N1 = n1;
    }

    public static void setN2(int n2) {
        N2 = n2;
    }

    public static void setP1(int p1) {
        P1 = p1;
    }

    public static void setP2(int p2) {
        P2 = p2;
    }

    public static void setResultWindowFlag() {
        resultWindowFlag = !resultWindowFlag;
    }

    public static String getStatStr() {
        return statStr;
    }

    public static void setStopFlag() {
        startFlag = false;
    }

    public static void unsetStopFlag() {
        startFlag = true;
    }

    public Habitat() {

    }

    public static Habitat getInstance() {
        Habitat localInstance = instance;
        if (localInstance == null) {
            synchronized (Habitat.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Habitat();
                }
            }
        }
        return localInstance;
    }

    private static volatile Habitat instance;
    private MainController mainController;
    private StartMenuController startMenuController;
    private static Timer timer;
    public boolean timeFlag;
    private boolean statFlag;
    private long startTime;
    private static int P1, P2;
    private static int N1, N2;
    public static boolean startFlag;
    private static boolean resultWindowFlag;
    private static String statStr;
    private StartMenu startMenu;
}
