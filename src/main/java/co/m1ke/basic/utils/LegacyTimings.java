package co.m1ke.basic.utils;

public class LegacyTimings {

    private long i;

    public LegacyTimings() {
        this.i = System.currentTimeMillis();
    }

    public long stop() {
        return System.currentTimeMillis() - i;
    }

}
