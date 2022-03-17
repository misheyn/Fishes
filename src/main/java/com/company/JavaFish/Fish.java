package com.company.JavaFish;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

abstract class Fish extends Node {
    public Fish(int _x, int _y, String path) throws FileNotFoundException {
        Image image = new Image(new FileInputStream(path));
        imageView = new ImageView(image);
        imageView.setX(_x);
        imageView.setY(_y);
        imageView.setFitHeight(300);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);
        setFocused(false);
    }

    public ImageView getImageView() {
        return imageView;
    }

    private final ImageView imageView;
}
