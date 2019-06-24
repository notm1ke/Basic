package co.m1ke.basic.scheduler;

import co.m1ke.basic.logger.Logger;

import java.util.LinkedHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.ImmutableMap;

public class SimpleScheduler {

    private Logger logger;
    private LinkedHashMap<Integer, ScheduledExecutorService> runnables;

    public SimpleScheduler() {
        this.logger = new Logger("Scheduler");
        this.runnables = new LinkedHashMap<>();
    }

    public void scheduleRepeatingTask(SimpleRunnable runnable, long delay, long period, TimeUnit unit) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(runnable, delay, period, unit);
        this.runnables.put(runnable.getTaskId(), service);
    }

    public void scheduleDelayedTask(SimpleRunnable runnable, long delay, TimeUnit unit) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.schedule(runnable, delay, unit);
        try {
            service.awaitTermination(5, TimeUnit.SECONDS);
        } catch (Exception ignored) {
        } finally {
            service.shutdown();
        }
    }

    public void run(Runnable runnable) {
        runnable.run();
    }

    public void runAsync(Runnable runnable) {
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(runnable);
        try {
            service.awaitTermination(2500L, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            logger.except(e, "Error executing async task");
        }
    }

    public void cancelTask(int id) {
        ScheduledExecutorService service = runnables.get(id);
        if (service == null) {
            logger.warning("Tried to cancel task with ID " + id + ", but it was not found in the runnables map.");
            return;
        }
        try {
            service.awaitTermination(2500L, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            logger.except(e, "Error cancelling task with ID " + id);
        }
        service.shutdown();
    }

    public ImmutableMap<Integer, ScheduledExecutorService> getRunnables() {
        return ImmutableMap.copyOf(runnables);
    }

}
