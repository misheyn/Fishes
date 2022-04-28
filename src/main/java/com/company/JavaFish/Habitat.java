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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Habitat extends Application {

    public Habitat() {
        goldenThread = new GoldenAI();
        guppyThread = new GuppyAI();
    }

    @Override
    public void start(Stage stage) throws IOException {
        instance = this;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));
        Scene root = new Scene(fxmlLoader.load());
        mainController = fxmlLoader.getController();
        Image img = new Image(new FileInputStream("src/image/aquarium.png"));
        BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        mainController.getPane().setBackground(bGround);
        initialize();
        mainController.getStatistic().setVisible(true);
        stage.setTitle("Aquarium fishes");
        stage.setScene(root);
        stage.getIcons().add(new Image(new FileInputStream("src/image/fill.png")));
        width = 900;
        height = 750;
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
            mainController.switchMenuItemOn();
        } else {
            mainController.switchButtonsOff();
            mainController.switchMenuItemOff();
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
        synchronized (FishArr.getInstance().linkedList) {
            FishArr.getInstance().linkedList.forEach(tmp -> mainController.getPane().getChildren().remove(tmp.getImageView()));
            FishArr.getInstance().linkedList.clear();
        }
        FishArr.getInstance().treeMap.clear();
        FishArr.getInstance().hashSet.clear();
        FishArr.getInstance().vector.clear();
    }

    private void bornFishes(long currentTime, int P, int N, String fish) {
        Random random = new Random();
        int randP;
        if ((currentTime / 1000) % N == 0) {
            randP = random.nextInt(300);
            if (P <= randP) {
                if (fish.equals("gold")) {
                    try {
                        bornGold(currentTime / 1000);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else if (fish.equals("guppy")) {
                    try {
                        bornGuppy(currentTime / 1000);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void update(long currentTime) throws IOException {
        if (startFlag) {
            bornFishes(currentTime, P1, N1, "gold");
            bornFishes(currentTime, P2, N2, "guppy");
            showTimeLabel();
            clearDeadFish(currentTime);
        }
    }

    private void clearDeadFish(long currentTime) {
        AtomicReference<Fish> foundedFish = new AtomicReference<>();
        FishArr.getInstance().linkedList.forEach(tmp -> {
            if (currentTime / 1000 - tmp.getBirthTime() > tmp.getLifeTime()) {
                AtomicInteger foundedId = new AtomicInteger();
                FishArr.getInstance().treeMap.forEach((id, birthTime) -> {
                    if (tmp.getBirthTime() == birthTime) {
                        foundedId.set(id);
                    }
                });
                FishArr.getInstance().hashSet.remove(foundedId.get());
                FishArr.getInstance().treeMap.remove(foundedId.get());
                mainController.getPane().getChildren().remove(tmp.getImageView());
                foundedFish.set(tmp);
            }
        });
        synchronized (FishArr.getInstance().linkedList) {
            FishArr.getInstance().linkedList.remove(foundedFish.get());
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

    private void bornGold(long currentTime) throws FileNotFoundException {
        Random random = new Random();
        Integer ID = random.nextInt(100);
        GoldenFish fish = new GoldenFish(currentTime, random.nextInt((int) mainController.getPane().getWidth() - 200), random.nextInt((int) mainController.getPane().getHeight() - 100));
        mainController.getPane().getChildren().add(fish.getImageView());
        FishArr.getInstance().vector.add(fish);
        synchronized (FishArr.getInstance().linkedList) {
            FishArr.getInstance().linkedList.add(fish);
        }
        FishArr.getInstance().hashSet.add(ID);
        FishArr.getInstance().treeMap.put(ID, currentTime);
    }

    private void bornGuppy(long currentTime) throws FileNotFoundException {
        Random random = new Random();
        Integer ID = random.nextInt(100);
        GuppyFish fish = new GuppyFish(currentTime, random.nextInt((int) mainController.getPane().getWidth() - 200), random.nextInt((int) mainController.getPane().getHeight() - 100));
        mainController.getPane().getChildren().add(fish.getImageView());
        FishArr.getInstance().vector.add(fish);
        synchronized (FishArr.getInstance().linkedList) {
            FishArr.getInstance().linkedList.add(fish);
        }
        FishArr.getInstance().hashSet.add(ID);
        FishArr.getInstance().treeMap.put(ID, currentTime);
    }

    public static void main(String[] args) {
        launch();
        timer.cancel();
        try {
            Habitat.getInstance().guppyThread.notifyThread();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            Habitat.getInstance().goldenThread.notifyThread();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Habitat.getInstance().goldenThread.moveTimer.cancel();
        Habitat.getInstance().guppyThread.moveTimer.cancel();
        Habitat.getInstance().goldenThread.stopFlag = true;
        Habitat.getInstance().guppyThread.stopFlag = true;
    }

    private void initialize() throws IOException {
        StartMenu.showMenu();
        timer = new Timer();
        startTime = System.currentTimeMillis();
        goldenThread.start();
        guppyThread.start();
//        goldenThread = new GoldenAI();
//        guppyThread = new GuppyAI();
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

    public static boolean getResultWindowFlag() {
        return resultWindowFlag;
    }

    public static String getStatStr() {
        return statStr;
    }

    public static void setStopFlag() {
        startFlag = false;
    }

    public static void setContinueFlag() {
        startFlag = true;
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
    private static Timer timer;
    public boolean timeFlag = false;
    private boolean statFlag = false;
    public long startTime;
    private static int P1, P2;
    private static int N1, N2;
    public static boolean startFlag = false;
    private static boolean resultWindowFlag = true;
    private static String statStr;
    final public GoldenAI goldenThread;
    final public GuppyAI guppyThread;
    public int width;
    public int height;
    public static boolean waitGolden = false;
    public static boolean waitGuppy = false;
}
