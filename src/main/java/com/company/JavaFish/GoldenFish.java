package com.company.JavaFish;

import java.io.FileNotFoundException;

public class GoldenFish extends Fish {

    public GoldenFish(long burstTime, int _x, int _y) throws FileNotFoundException {
        super(burstTime, _x, _y, "src/image/goldenFish.png");
    }

    @Override
    public void move() {
        super.x += moveSpeed;
        if (super.x > MainController.width - 200) {
            super.getImageView().setRotate(90);
            moveSpeed = -moveSpeed;
        } else if (super.x < 0) {
            super.getImageView().setRotate(0);
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

    static long lifeTime;
    static int moveSpeed = 10;

}
