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

    public Habitat() {
    }

    @Override
    public void start(Stage stage) throws IOException {
        instance = this;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene root = new Scene(fxmlLoader.load(), 900, 750);
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
        stage.setTitle("Aquarium fishes");
        stage.setScene(root);
        stage.getIcons().add(new Image(new FileInputStream("src/image/fill.png")));
        stage.show();
    }

    public void startAction() {
        startFlag = true;
        startTime = System.currentTimeMillis();
        mainController.getStatistic().setVisible(false);
        mainController.getTimeLabel().setVisible(false);
        startCycle();
    }

    public void stopAction() throws IOException {
        startFlag = false;
        showStatLabel();
        update(System.currentTimeMillis() - startTime);
        if (!startFlag) {
            timer.cancel();
            timer = new Timer();
            startTime = System.currentTimeMillis();
            clearListFish();
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
        listFish.forEach((tmp) -> mainController.getPane().getChildren().remove(tmp.getImageView()));
        listFish.clear();
    }

    private void bornFishes(long currentTime, int P, int N, String fish) {
        Random random = new Random();
        int randP;
        if ((currentTime / 1000) % N == 0) {
            randP = random.nextInt(300);
            if (P <= randP) {
                if (fish.equals("gold")) {
                    try {
                        bornGold();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else if (fish.equals("guppy")) {
                    try {
                        bornGuppy();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void update(long currentTime) throws IOException {
        if (startFlag) {
            showTimeLabel();
            bornFishes(currentTime, P1, N1, "gold");
            bornFishes(currentTime, P2, N2, "guppy");
        }
    }

    public void showTimeLabel() {
        String timeStr;
        if (timeFlag) {
            mainController.getTimeLabel().setVisible(true);
            if (startTime != 0) {
                long time = System.currentTimeMillis() - startTime;
                timeStr = "Time: " + time / 1000;
            } else {
                timeStr = "0";
            }
            mainController.printLabel(timeStr);
        } else {
            mainController.getTimeLabel().setVisible(false);
        }
    }

    private void showStatLabel() {
        String statStr;
        long finalTime = System.currentTimeMillis() - startTime;
        long countGold = listFish.stream().filter(i -> i instanceof GoldenFish).count();
        long countGuppy = listFish.stream().filter(i -> i instanceof GuppyFish).count();
        statStr = "Golden fish: " + countGold;
        statStr += "\nGuppy fish: " + countGuppy;
        statStr += "\nTime: " + finalTime / 1000;
        mainController.getStatistic().setVisible(true);
        mainController.printStatistic(statStr);
    }

    private void bornGold() throws FileNotFoundException {
        Random random = new Random();
        GoldenFish fish = new GoldenFish(random.nextInt((int) mainController.getPane().getWidth() - 200), random.nextInt((int) mainController.getPane().getHeight() - 100));
        mainController.getPane().getChildren().add(fish.getImageView());
        listFish.add(fish);
    }

    private void bornGuppy() throws FileNotFoundException {
        Random random = new Random();
        GuppyFish fish = new GuppyFish(random.nextInt((int) mainController.getPane().getWidth() - 200), random.nextInt((int) mainController.getPane().getHeight() - 100));
        mainController.getPane().getChildren().add(fish.getImageView());
        listFish.add(fish);
    }

    public static void main(String[] args) {
        launch();
        timer.cancel();
    }

    private void initialize() {
        N1 = 2;
        N2 = 5;
        P1 = 40;
        P2 = 60;
        listFish = new ArrayList<>();
        timeFlag = false;
        startFlag = false;
        timer = new Timer();
        startTime = System.currentTimeMillis();
        mainController.getStatistic().setVisible(false);
        mainController.getTimeLabel().setVisible(false);
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
    private Controller mainController;
    private static Timer timer;
    public boolean timeFlag;
    private long startTime;
    private static int P1, P2;
    private static int N1, N2;
    public static boolean startFlag;
    private ArrayList<Fish> listFish;
}