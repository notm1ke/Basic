package co.m1ke.basic.events.interfaces;

public interface Cancellable {

    boolean isCancelled();
    void setCancelled(boolean cancelled);

}
