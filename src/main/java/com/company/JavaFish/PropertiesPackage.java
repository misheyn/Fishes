package com.company.JavaFish;

public class PropertiesPackage {
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

    public void setProperties() {
        Habitat.setN1(N1);
        Habitat.setN2(N2);
        Habitat.setP1(P1);
        Habitat.setP2(P2);
        GoldenFish.lifeTime = goldenLifeTime;
        GuppyFish.lifeTime = guppyLifeTime;
    }

    public static PropertiesPackage getInstance() {
        PropertiesPackage localInstance = instance;
        if (localInstance == null) {
            synchronized (PropertiesPackage.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new PropertiesPackage();
                }
            }
        }
        return localInstance;
    }

    private static volatile PropertiesPackage instance;

    long goldenLifeTime, guppyLifeTime;
    int N1, N2;
    int P1, P2;
}
