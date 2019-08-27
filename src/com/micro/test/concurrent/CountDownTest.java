package com.micro.test.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * Created by mycge on 2019/8/19.
 */
public class CountDownTest {
    private static final int COUNT = 10;

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(COUNT);
        new Thread(new WaitingTask(latch)).start();  //汽车总装任务

        for (int i = 0; i < COUNT; i++) {
            new Thread(new WorkingTask(latch)).start();
        }
    }
}

/**
 * 汽车总装任务
 */
class WaitingTask implements Runnable {
    private CountDownLatch latch;

    public WaitingTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.println("汽车总装任务执行正在准备执行...");
            latch.await();
            System.out.println("汽车总装任务---" + Thread.currentThread().getId() + "执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 各部件任务
 */
class WorkingTask implements Runnable {
    private CountDownLatch latch;

    public WorkingTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            int rand = (int) (Math.random() * 10);
            Thread.sleep(rand * 1000);
            System.out.println("汽车任务" + Thread.currentThread().getId() + "执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }
    }
}

