package com.micro.test.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Description: ReentrantLock 多重锁
 * <p>
 * ReentrantLock是重入锁，调用lock()时
 * 1.如果锁没有被另一个线程占用并且立即返回，则将锁定计数设置为1。
 * 2.如果当前线程已经保持锁定，则保持计数增加1，该方法立即返回。 (当前线程，可以多次锁；每次锁，锁定计数+1)
 * 3.如果锁被另一个线程保持，则当前线程将被禁用以进行线程调度（排它），并且在锁定已被获取之前处于休眠状态，此时锁定保持计数被设置为1。
 * <p>
 * Created by mycge at 0:19 on 2019-09-02.
 */
public class Lock2Test {
    public static void main(String[] args) {
        GameRoom gr = new GameRoom();

        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    gr.openDoor();
                }
            }).start();
        }
    }

    /**
     * 游戏厅
     */
    static class GameRoom {
        ReentrantLock locker = new ReentrantLock();

        /**
         * 进入
         */
        public void openDoor() {
            System.out.println(Thread.currentThread().getId() + "---正在准备进入游戏厅");
            try {
                locker.lock();
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getId() + "---已进入游戏厅......");
                //开始玩游戏
                int r = (int) (Math.random() * 10);
                if (r % 2 == 0) {//二重锁
                    shoting();
                } else {//三重锁
                    shoting();
                    boating();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                locker.unlock();
            }

        }

        /**
         * 射箭
         */
        public void shoting() {
            try {
                locker.lock();
                Thread.sleep(1500);
                System.out.println(Thread.currentThread().getId() + "---play shoting");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                locker.unlock();
            }

        }

        /**
         * 划船
         */
        public void boating() {
            try {
                locker.lock();
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getId() + "---play boating");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                locker.unlock();
            }
        }

    }

}