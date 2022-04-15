package com.company.JavaFish;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.Vector;

public class FishArr {
    private static volatile FishArr instance;

    public FishArr() {
        vector = new Vector<>();
        linkedList = new LinkedList<>();
        hashSet = new HashSet<>();
        treeMap = new TreeMap<>();
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

    public Vector<Fish> vector;
    public final LinkedList<Fish> linkedList;
    public HashSet<Integer> hashSet;
    public TreeMap<Integer, Long> treeMap;
}
