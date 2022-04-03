package com.company.JavaFish;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

abstract class Fish {
    public Fish(long _burstTime, int _x, int _y, String path) throws FileNotFoundException {
        birthTime = _burstTime;
        Image image = new Image(new FileInputStream(path));
        imageView = new ImageView(image);
        imageView.setX(_x);
        imageView.setY(_y);
        imageView.setFitHeight(300);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public long getBirthTime() {
        return birthTime;
    }

    public long getLifeTime() {
        return 0;
    }

    long birthTime;
    final ImageView imageView;
}
