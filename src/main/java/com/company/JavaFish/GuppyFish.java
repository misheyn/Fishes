package com.company.JavaFish;

import java.io.FileNotFoundException;

public class GuppyFish extends Fish {

    public GuppyFish(long burstTime, int _x, int _y) throws FileNotFoundException {
        super(burstTime, _x, _y, "src/image/guppyFish.png");
    }

    @Override
    public void move() {
        super.y += moveSpeed;
        if (super.y > MainController.height - 100) {
            super.getImageView().setRotate(90);
            moveSpeed = -moveSpeed;
        } else if (super.y < 0) {
            super.getImageView().setRotate(0);
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

    static long lifeTime;
    static int moveSpeed = 10;

}
