package com.company.JavaFish;

import java.io.FileNotFoundException;

public class GoldenFish extends Fish implements IBehaviour {

    public GoldenFish(long burstTime, int _x, int _y) throws FileNotFoundException {
        super(burstTime, _x, _y, "src/image/goldenFish.png");
    }
    @Override
    public long getLifeTime() {
        return lifeTime;
    }
    static long lifeTime; //todo

}
