package com.micro.test.concurrent.task;

/**
 * Description:
 * synchronized锁定方法，表示同一个对象的这个方法，在多线程并发时，只能允许一个线程进入
 *
 * 解释：synchronized同步方法的时候，线程进入方法体，同时会对这个对象Garden。出了同步方法体，对象解锁。
 * synchronized是排它的，一个线程进入openDoor()时，就持有了对象锁
 * 其他线程想enterRoom()，也必须拿到锁才行。但是对象锁时排它的，所以另一个线程进入enterRoom()只能等待
 *
 * Created by mycge at 21:30 on 2019-09-21.
 */
public class Garden1 {

    public Garden1() {
    }

    synchronized public void openDoor() {
        System.out.println("------线程" + Thread.currentThread().getId() + "---openDoor---start");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {

        }
        System.out.println("------线程" + Thread.currentThread().getId() + "---openDoor---end");
    }

    synchronized public void enterRoom() {
        System.out.println("------线程" + Thread.currentThread().getId() + "---enterRoom---start");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {

        }
        System.out.println("------线程" + Thread.currentThread().getId() + "---enterRoom---end");
    }

}
