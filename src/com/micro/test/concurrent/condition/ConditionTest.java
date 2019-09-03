package com.micro.test.concurrent.condition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 * ReentrantLock & Condition监视器
 * Created by mycge at 1:43 on 2019-09-02.
 */
public class ConditionTest {
    public static void main(String[] args) {
        BoundedBuffer buffer = new BoundedBuffer();

        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Integer r = (int) (Math.random() * 10);
                            Thread.sleep(500);
                            buffer.put(r);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        executor.shutdown();

        //主线程取一个
        try {
            Thread.sleep(8000);
            Object obj = buffer.take();
            System.out.println(Thread.currentThread().getId()+"------------取出------------"+obj.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
