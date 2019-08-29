package com.micro.test.concurrent.task;

import java.util.concurrent.Callable;

/**
 * Description: Callable<T>
 * Created by mycge at 0:29 on 2019-08-29.
 */
public class TaskResult implements Callable<String> {
    private Integer i;

    public TaskResult(Integer i) {
        this.i = i;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(300);

        return Thread.currentThread().getId()+"---output---"+i;
    }
}
