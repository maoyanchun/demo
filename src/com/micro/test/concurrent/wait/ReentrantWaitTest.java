package com.micro.test.concurrent.wait;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:  主线程&子线程
 * ReentrantLock + Condition
 * Condition的await()和signal()，底层都是采用LockSupport
 * Created by mycge at 23:56 on 2019-09-08.
 */
public class ReentrantWaitTest {

    public static void main(String[] args) {
        Lock locker = new ReentrantLock();
        Condition condition = locker.newCondition();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int sum = 0;
                for (int i = 0; i < 100; i++) {
                    sum += i;
                }
                try {
                    locker.lock();  //必须要提前锁定
                    condition.await();  //阻塞当前线程

                } catch (Exception e) {

                }finally {
                    locker.unlock();
                }

                System.out.println("sum = " + sum);
            }
        }).start();

        try {
            Thread.sleep(2000);
            locker.lock();
            condition.signal(); //通知监视器解锁

        } catch (Exception e) {

        }finally {
            locker.unlock();
        }
    }
}
