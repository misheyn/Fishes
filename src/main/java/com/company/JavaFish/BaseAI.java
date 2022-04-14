package com.company.JavaFish;

import java.util.Objects;

abstract class BaseAI extends Thread {
    BaseAI(String name) {
        stopFlag = false;
        if (name.equals("Golden")) {
            flag = true;
        } else if (name.equals("Guppy")) {
            flag = false;
        }
    }

    public void run() {
        while (!stopFlag) {
            if (flag) {
                if (!FishArr.getInstance().linkedList.isEmpty())
                FishArr.getInstance().linkedList.forEach(tmp -> {
                    if (tmp instanceof GoldenFish) tmp.move();
                });
            } else {
                if (!FishArr.getInstance().linkedList.isEmpty())
                    FishArr.getInstance().linkedList.forEach(tmp -> {
                        if (tmp instanceof GuppyFish) tmp.move();
                    });
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean stopFlag;
    private boolean flag;
}
