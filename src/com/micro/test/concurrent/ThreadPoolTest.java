package com.micro.test.concurrent;

import java.util.concurrent.*;

/**
 * Description: ThreadPoolExecutor
 *  1.corePoolSize 线程启动：根据任务数量，直接启动线程。这些任务是不进阻塞队列的。
 *                          即使空闲时仍保留在池中的数量
 *  2.入队的任务数量：
 *      LinkedBlockingQueue  扣除corePoolSize启动的线程数，其它任务先进入LinkedBlockingQueue
 *
 *  3.最大线程数是如何增加
 *      LinkedBlockingQueue 未入队列的任务，会通过maxPoolSize来增加线程，去执行任务
 *                          如果maxPoolSize不能完全解决队列外面的任务，会抛出异常
 *                          注意：已经入队的任务，是不会用maxPoolSize来执行的
 *
 * Created by mycge at 13:06 on 2019-09-21.
 */
public class ThreadPoolTest {

    public static void main(String[] args) {

        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 5, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(9));
//        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 9, 10, TimeUnit.SECONDS, new SynchronousQueue<>());

        //启动一个线程，随时输出线程池状态
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("corePoolSize = " + pool.getCorePoolSize());
                    System.out.println("maximumPoolSize = " + pool.getMaximumPoolSize());
                    System.out.println("activeCount = " + pool.getActiveCount());
                    System.out.println("task end-----------------");
                    System.out.println("pool size = " + pool.getPoolSize());
                    System.out.println("queue size = " + pool.getQueue().size());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        //do nothing
                    }
                }
            }
        }).start();

        for (int i = 0; i < 10; i++) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getId() + " is running........................................");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        //do nothing
                    }
                }
            });

        }

//        pool.shutdown();
    }
}
