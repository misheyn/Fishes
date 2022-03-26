package com.company.JavaFish;

import java.util.Vector;

public class FishArr {
    private static volatile FishArr instance;

    public FishArr() {
        vector = new Vector<Fish>();
    }

    public static FishArr getInstance() {
        FishArr localInstance = instance;
        if (localInstance == null) {
            synchronized (FishArr.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new FishArr();
                }
            }
        }
        return localInstance;
    }

    Vector<Fish> vector;
}
