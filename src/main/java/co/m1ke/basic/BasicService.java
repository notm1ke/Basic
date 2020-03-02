package co.m1ke.basic;

import co.m1ke.basic.scheduler.SimpleScheduler;

public interface BasicService {

    String name();
    String author();

    SimpleScheduler getScheduler();

    double version();

}
