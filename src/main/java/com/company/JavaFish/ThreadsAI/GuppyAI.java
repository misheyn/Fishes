package com.company.JavaFish.ThreadsAI;

import com.company.JavaFish.MainWindow.Habitat;
import com.company.JavaFish.Models.FishArr;
import com.company.JavaFish.Models.GuppyFish;

import java.util.Timer;
import java.util.TimerTask;

final public class GuppyAI extends BaseAI {
    public void run() {
        moveTimer = new Timer();
        moveTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (Habitat.waitGuppy) {
                    try {
                        waitThread();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                synchronized (FishArr.getInstance().linkedList) {
                    if (!FishArr.getInstance().linkedList.isEmpty())
                        FishArr.getInstance().linkedList.forEach(tmp -> {
                            if (tmp instanceof GuppyFish) tmp.move();
                        });
                }
            }
        }, 0, 100);
    }
}
