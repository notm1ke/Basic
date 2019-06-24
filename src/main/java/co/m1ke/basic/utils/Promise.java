package co.m1ke.basic.utils;

import java.util.concurrent.CompletableFuture;

public class Promise<T> extends CompletableFuture<T> {

    public Promise() {}

    public Promise(final Throwable t)
    {
        this.completeExceptionally(t);
    }

    public Promise(final T t)
    {
        this.complete(t);
    }

}