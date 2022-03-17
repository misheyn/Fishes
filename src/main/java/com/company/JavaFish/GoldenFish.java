package com.company.JavaFish;

import java.io.FileNotFoundException;

public class GoldenFish extends Fish implements IBehaviour {

    public GoldenFish(int _x, int _y) throws FileNotFoundException {
        super(_x, _y, "src/image/goldenFish.png");
    }

}
