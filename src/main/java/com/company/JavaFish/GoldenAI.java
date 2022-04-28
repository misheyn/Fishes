package com.company.JavaFish;

import java.util.Timer;
import java.util.TimerTask;

final public class GoldenAI extends BaseAI {
    public void run() {
        moveTimer = new Timer();
        moveTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (Habitat.waitGolden) {
                    try {
                        waitThread();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                synchronized (FishArr.getInstance().linkedList) {
                    if (!FishArr.getInstance().linkedList.isEmpty())
                        FishArr.getInstance().linkedList.forEach(tmp -> {
                            if (tmp instanceof GoldenFish) tmp.move();
                        });
                }
            }
        }, 0, 100);
    }
}

