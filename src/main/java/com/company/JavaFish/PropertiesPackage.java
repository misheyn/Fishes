package com.company.JavaFish;

import java.io.Serializable;

public class PropertiesPackage implements Serializable {

    public String toString() {
        return Integer.toString(N1) + Integer.toString(P1) + Long.toString(goldenLifeTime) +
                Integer.toString(N2) + Integer.toString(P2) + Long.toString(guppyLifeTime);
    }

    public void getProperties(int n1, int n2, int p1, int p2, long lifeTime1, long lifeTime2) {
        N1 = n1;
        N2 = n2;
        P1 = p1;
        P2 = p2;
        goldenLifeTime = lifeTime1;
        guppyLifeTime = lifeTime2;
    }
    public void print(){
        System.out.println("Now " + N1 + " "+ N2 + " "+ P1 + " "+ P2 + " "+ goldenLifeTime + " "+ guppyLifeTime + " ");
    }
    public void copyProperties(PropertiesPackage tmp) {
        N1 = tmp.N1;
        N2 = tmp.N2;
        P1 = tmp.P1;
        P2 = tmp.P2;
        goldenLifeTime = tmp.goldenLifeTime;
        guppyLifeTime = tmp.guppyLifeTime;
    }

    public long goldenLifeTime;
    public long guppyLifeTime;
    public int N1, N2;
    public int P1, P2;
}
