package co.m1ke.basic.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class PromiseTest {

    @Test
    public void t1_promise() throws ExecutionException, InterruptedException {
        Promise<String> test = new Promise<>();
        test.complete("hi");

        Assert.assertEquals(test.get(), "hi");
    }

}