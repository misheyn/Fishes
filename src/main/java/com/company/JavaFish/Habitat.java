package com.company.JavaFish;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Habitat extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        root = new Scene(fxmlLoader.load(), 900, 750);
        group = fxmlLoader.getRoot();
        Image img = new Image(new FileInputStream("src/image/aquarium.png"));
        BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        group.setBackground(bGround);
        N1 = 5;
        N2 = 2;
        P1 = 40;
        P2 = 60;
        timer = new Timer();
        listGuppy = new ArrayList<>();
        listGolden = new ArrayList<>();
        startTime = 0;
        stage.setTitle("Aquarium fish");
        stage.setScene(root);
        root.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().equals(KeyCode.T)) {
                flag = !flag;
            }
        });
        root.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().equals(KeyCode.B)) {
                startTime = System.currentTimeMillis();
                startCycle();
            }
        });
        root.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().equals(KeyCode.E)) {
                timer.cancel();
                String str;
                long finalTime = System.currentTimeMillis() - startTime;
                str = "Golden fish: " + Integer.toString(listGolden.size());
                str += "\nGuppy fish: " + Integer.toString(listGuppy.size());
                str += "\nTime: " + Long.toString(finalTime / 1000);
                ((Controller) fxmlLoader.getController()).printStatistic(str);
                timer = new Timer();
                listGuppy.clear();
                listGolden.clear();
                startTime = 0;
            }
        });
        stage.getIcons().add(new Image(new FileInputStream("src/image/fill.png")));
        stage.show();
    }


    private void startCycle() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {

                    update(System.currentTimeMillis() - startTime);
                });

            }
        }, 0, 1000);
    }

//    private void clear() {
//        list.forEach((tmp) -> {
//            group.getChildren().remove(tmp.getImageView());
//        });
//        list.clear();
//    }

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
        String str = "";
        if (flag) {
            if (startTime != 0) {
                long time = System.currentTimeMillis() - startTime;
                str = "Time: " + Long.toString(time / 1000);
            } else {
                str = "0";
            }
        }
        ((Controller) fxmlLoader.getController()).printLabel(str);
    }

    private void bornGold() throws FileNotFoundException {
        Random random = new Random();
        GoldenFish fish = new GoldenFish(random.nextInt((int) root.getWidth() - 200), random.nextInt((int) root.getHeight()) - 300);
        group.getChildren().add(fish.getImageView());
        listGolden.add(fish);
    }

    private void bornGuppy() throws FileNotFoundException {
        Random random = new Random();
        GuppyFish fish = new GuppyFish(random.nextInt((int) root.getWidth() - 200), random.nextInt((int) root.getHeight()) - 300);
        group.getChildren().add(fish.getImageView());
        listGuppy.add(fish);
    }

    public static void main(String[] args) {
        launch();
        timer.cancel();
    }

    private Scene root;
    private boolean flag;
    private FXMLLoader fxmlLoader;
    private static Timer timer;
    private long startTime;
    private ArrayList<GuppyFish> listGuppy;
    private ArrayList<GoldenFish> listGolden;
    private Pane group;
    private int P1, P2;
    private int N1, N2;
}
