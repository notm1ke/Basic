package co.m1ke.basic.events.listener;

import co.m1ke.basic.events.EventManager;
import co.m1ke.basic.events.interfaces.EventListener;
import co.m1ke.basic.logger.Logger;

public abstract class Listener implements EventListener {

    private String name;
    private EventManager manager;
    private Logger logger;

    public Listener(String name, EventManager manager) {
        this.name = name;
        this.manager = manager;
        this.logger = new Logger(this.name);
    }

    public abstract void init();

    public EventManager getManager() {
        return manager;
    }

    public void log(String msg) {
        logger.info(msg);
    }

    public void logRaw(String msg) {
        logger.raw(msg);
    }

    public void registerSelf() {
        manager.getEventExecutor().registerListener(this);
    }

    public void unregisterSelf() {
        manager.getEventExecutor().unregisterAll(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
