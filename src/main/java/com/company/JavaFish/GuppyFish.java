package com.company.JavaFish;

import java.io.FileNotFoundException;

public class GuppyFish extends Fish implements IBehaviour {

    public GuppyFish(int _x, int _y) throws FileNotFoundException {
        super(_x, _y, "src/image/guppyFish.png");
    }

}
