package com.company.JavaFish.Models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class Fish implements IBehaviour {
    public Fish(long _burstTime, int _x, int _y, String path) throws FileNotFoundException {
        birthTime = _burstTime;
        x = _x;
        y = _y;
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

    public long getMoveSpeed() {
        return 0;
    }

    protected int x, y;
    long birthTime;
    final ImageView imageView;
}
