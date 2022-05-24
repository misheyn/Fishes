package com.company.JavaFish.Models;

import com.company.JavaFish.MainWindow.Habitat;

import java.io.FileNotFoundException;

public class GuppyFish extends Fish {

    public GuppyFish(long burstTime, int _x, int _y) throws FileNotFoundException {
        super(burstTime, _x, _y, "src/image/guppyFish.png");
    }

    @Override
    public void move() {
        super.y += moveSpeed;
        if (moveSpeed < 0) super.getImageView().setRotate(0);
        else super.getImageView().setRotate(90);


        if (super.y >= Habitat.getInstance().height - 100) {
            super.y = Habitat.getInstance().height - 100;
            moveSpeed = -moveSpeed;
        } else if (super.y <= 0) {
            super.y = 0;
            moveSpeed = -moveSpeed;
        }
        super.getImageView().setY(super.y);
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
