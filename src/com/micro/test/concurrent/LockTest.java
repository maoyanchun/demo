package com.micro.test.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 * ReentrantLock是重入锁，调用lock()时
 *      1.如果锁没有被另一个线程占用并且立即返回，则将锁定计数设置为1。
 *      2.如果当前线程已经保持锁定，则保持计数增加1，该方法立即返回。
 *      3.如果锁被另一个线程保持，则当前线程将被禁用以进行线程调度，并且在锁定已被获取之前处于休眠状态，此时锁定保持计数被设置为1。
 *
 * Created by mycge at 0:19 on 2019-09-02.
 */
public class LockTest {
    public static void main(String[] args) {
        ReentrantLock locker = new ReentrantLock();

        for (int i = 0; i < 20; i++) {
            new Thread(new Garden(locker)).start();
        }
    }
}

class Garden implements Runnable {
    ReentrantLock locker;

    public Garden(ReentrantLock locker) {
        this.locker = locker;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getId() + "---" + "正准备进入公园");
        try {
//            locker.lock();
            boolean bRet = locker.tryLock(3000, TimeUnit.MILLISECONDS);
            if(bRet){
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getId() + "---" + "已进入公园。。。。。。");
            }
        } catch (InterruptedException e) {
//            e.printStackTrace();
        } finally {
            try {
                locker.unlock();
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }

    }
}