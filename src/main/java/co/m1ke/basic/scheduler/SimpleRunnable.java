package co.m1ke.basic.scheduler;

import club.argon.prototype.Base;

public abstract class SimpleRunnable implements Runnable {

    private int taskId;

    public SimpleRunnable() {
        this.taskId = Base.getScheduler().getRunnables().size() + 1;
    }

    public void cancel() {
        Base.getScheduler().cancelTask(taskId);
    }

    public int getTaskId() {
        return taskId;
    }

}
