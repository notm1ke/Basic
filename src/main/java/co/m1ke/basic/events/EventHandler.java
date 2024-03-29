package co.m1ke.basic.events;

import co.m1ke.basic.events.interfaces.Event;
import co.m1ke.basic.events.interfaces.EventListener;
import co.m1ke.basic.logger.Logger;

import java.lang.reflect.Method;

public class EventHandler implements Comparable<EventHandler> {

    private final EventListener listener;
    private final Method method;
    private final Event annotation;

    public EventHandler(EventListener listener, Method method, Event annotation) {
        this.listener = listener;
        this.method = method;
        this.annotation = annotation;
    }

    public Event getAnnotation() {
        return annotation;
    }

    public Method getMethod() {
        return method;
    }
    public EventListener getListener() {
        return listener;
    }

    public void execute(Event event) {
        try {
            method.invoke(listener, event);
        } catch (Exception e) {
            Logger l = new Logger("Event Handler");

            l.severe("Error executing Event Handler " + this.listener.getClass().getSimpleName() + "#" + method.getName() + ": " + e.getMessage() + " (" + e.getClass().getName() + ")");
            l.severe("Printing stacktrace:");

            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "EventHandler{" +
                "listener=" + listener +
                ", method=" + method.getName() +
                '}';
    }

    public int getPriority() {
        return annotation.priority();
    }

    @Override
    public int compareTo(EventHandler other) {
        int annotation = this.annotation.priority() - other.annotation.priority();
        if (annotation == 0)
            annotation = this.listener.hashCode() - other.listener.hashCode();
        return annotation == 0 ? this.hashCode() - other.hashCode() : annotation;
    }
}