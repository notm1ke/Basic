package co.m1ke.basic.events.listener;

import co.m1ke.basic.utils.Timings;

import java.util.ArrayList;

public class ListenerAdapter {

    private ArrayList<Listener> listeners;

    public ListenerAdapter() {
        this.listeners = new ArrayList<>();
    }

    public void loadAll() {
        Timings timings = new Timings("Listeners", "Start Listeners");
        this.listeners.forEach(Listener::init);
        timings.complete();
    }

    public void register(Listener listener) {
        listeners.add(listener);
    }

}
