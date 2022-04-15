package com.company.JavaFish;

import javafx.application.Platform;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

abstract class BaseAI extends Thread {
    BaseAI(String name) {
        stopFlag = false;
        if (name.equals("Golden")) {
            flag = true;
        } else if (name.equals("Guppy")) {
            flag = false;
        }
    }

    public void timerWait() throws InterruptedException {
//        moveTimer.wait();
        this.wait();
    }

    public void timerNotify() {
        moveTimer.notify();
    }

    public void run() {
//        moveTimer = new Timer();
//        moveTimer.schedule(new TimerTask() {
//            @Override
//            public void run() {
        while (!stopFlag) {
            synchronized (FishArr.getInstance().linkedList) {
                if (!FishArr.getInstance().linkedList.isEmpty())
                    if (flag) {
                        FishArr.getInstance().linkedList.forEach(tmp -> {
                            if (tmp instanceof GoldenFish) tmp.move();
                        });
                    } else {
                        FishArr.getInstance().linkedList.forEach(tmp -> {
                            if (tmp instanceof GuppyFish) tmp.move();
                        });
                    }
            }
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
//            }
//        }, 0, 100);
    }

    public Timer moveTimer;
    public boolean stopFlag;
    private boolean flag;
}
