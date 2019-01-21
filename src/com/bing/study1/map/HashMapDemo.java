package com.bing.study1.map;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class HashMapDemo {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        int n = 16;
        System.out.println("test hashcode: "+"test".hashCode());

        ConcurrentHashMap<String, String> map1 = new ConcurrentHashMap<>();

        System.out.println("hash: "+((n - 1) & 35));

        ConcurrentSkipListMap map2 = new ConcurrentSkipListMap();
    }
}
