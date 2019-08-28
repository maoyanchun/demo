package com.micro.test.concurrent;

import com.micro.test.concurrent.task.ListeningTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executors.newSingleThreadExecutor()
 * 应用场景：监听线程、...
 *
 * 线程中抛出异常，会重新起一个线程接替完成剩下的task
 */
public class SingleThreadTest {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 20; i++) {
            executor.execute(new ListeningTask(i));
        }

        executor.shutdown();
    }
}
