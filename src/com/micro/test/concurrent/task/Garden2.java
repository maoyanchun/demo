package com.micro.test.concurrent.task;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Description: ReentrantLock
 *
 * 1.如果openDoor()与enterRoom()两个方法使用一把锁ReentrantLock，代码执行效果 <<==>> synchronized同步两个方法完全一致
 *   但是两者操作的原理完全不同
 *
 * 2.使用两把锁doorLock与roomLock，分别锁大门和屋子。则openDoor()与enterRoom()之间没有任何影响
 *
 * Created by mycge at 21:30 on 2019-09-21.
 */
public class Garden2 {
//    private ReentrantLock locker = new ReentrantLock();

    private ReentrantLock doorLock = new ReentrantLock();
    private ReentrantLock roomLock = new ReentrantLock();

    public Garden2() {
    }

    public void openDoor() {
//        locker.lock();

        doorLock.lock();
        try {
            System.out.println("------线程" + Thread.currentThread().getId() + "---openDoor---start");
            Thread.sleep(200);
            System.out.println("------线程" + Thread.currentThread().getId() + "---openDoor---end");
        } catch (InterruptedException e) {

        }finally {
//            locker.unlock();

            doorLock.unlock();
        }

    }

    public void enterRoom() {
//        locker.lock();

        roomLock.lock();
        try {
            System.out.println("------线程" + Thread.currentThread().getId() + "---enterRoom---start");
            Thread.sleep(300);
            System.out.println("------线程" + Thread.currentThread().getId() + "---enterRoom---end");
        } catch (InterruptedException e) {

        }finally {
//            locker.unlock();

            roomLock.unlock();
        }

    }

}
