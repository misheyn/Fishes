package com.company.JavaFish;

import java.util.Timer;

abstract class BaseAI extends Thread {

    public synchronized void waitThread() throws InterruptedException {
        wait();
    }

    public synchronized void notifyThread() throws InterruptedException {
        notify();
    }

    public Timer moveTimer;
}
