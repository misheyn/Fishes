package com.company.JavaFish.Models;

import com.company.JavaFish.MainWindow.Habitat;

import java.io.FileNotFoundException;

public class GoldenFish extends Fish {

    public GoldenFish(long burstTime, int _x, int _y) throws FileNotFoundException {
        super(burstTime, _x, _y, "src/image/goldenFish.png");
        super.getImageView().setScaleX(-1);
    }

    @Override
    public void move() {
        super.x += moveSpeed;
        if (moveSpeed < 0) super.getImageView().setScaleX(1);
        else super.getImageView().setScaleX(-1);
        if (super.x >= Habitat.getInstance().width - 200) {
            moveSpeed = -moveSpeed;
        } else if (super.x <= 0) {
            moveSpeed = -moveSpeed;
        }
        super.getImageView().setX(super.x);
    }

    @Override
    public long getMoveSpeed() {
        return moveSpeed;
    }

    @Override
    public long getLifeTime() {
        return lifeTime;
    }

    public static long lifeTime;
    static int moveSpeed = 5;

}
