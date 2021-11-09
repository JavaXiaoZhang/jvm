package com.zq.classloader;

public class TLABTest {
    private static Object o;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 100000000; i++) {
            o = new Object();
        }
        long end = System.currentTimeMillis();

        System.out.println("time:" + (end-start));

        // time:384

        // -XX:-UseTLAB
        // time:1312
    }
}
