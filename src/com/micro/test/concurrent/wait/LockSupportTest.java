package com.micro.test.concurrent.wait;

import java.util.concurrent.locks.LockSupport;

/**
 * Description: LockSupport线程阻塞与通知机制
 * <p>
 * Created by mycge at 0:13 on 2019-09-09.
 */
public class LockSupportTest {

    public static void main(String[] args) {

        //线程t
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                int sum = 0;
                for (int i = 0; i < 100; i++) {
                    sum += i;
                }
                LockSupport.park(); //阻塞当前线程（除非当前线程有许可证）
                System.out.println("Thread---" + Thread.currentThread().getId() + "---sum = " + sum);
            }
        });
        t.start();

        try {
            System.out.println("Main Thread---" + Thread.currentThread().getId());
            //主线程阻塞2s
            Thread.sleep(3000);
        } catch (Exception e) {

        }
        LockSupport.unpark(t);  //为给定的线程提供许可证。 如果线程在park被阻塞，那么它将被解除阻塞。

    }
}
