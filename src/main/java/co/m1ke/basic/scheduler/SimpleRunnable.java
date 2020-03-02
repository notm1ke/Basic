package co.m1ke.basic.scheduler;

public abstract class SimpleRunnable implements Runnable {

    public abstract void cancel();
    public abstract int getTaskId();

}
