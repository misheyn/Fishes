package com.company.JavaFish;

import java.io.FileNotFoundException;

public class GuppyFish extends Fish implements IBehaviour {

    public GuppyFish(long burstTime, int _x, int _y) throws FileNotFoundException {
        super(burstTime, _x, _y, "src/image/guppyFish.png");
    }

    @Override
    public long getLifeTime() {
        return lifeTime;
    }

    static long lifeTime; //todo

}
