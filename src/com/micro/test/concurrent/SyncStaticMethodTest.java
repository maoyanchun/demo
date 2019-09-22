package com.micro.test.concurrent;

/**
 * Description:
 * <p>
 * 静态方法使用synchronized，当一个线程进入这个方法如m1()，本质是要给这个Class加锁。
 * 其它线程想进入m1()或者m2()，都需要先获得这个Class锁，获得锁之后才能进入，否则就排队等待
 * <p>
 * Created by mycge at 22:20 on 2019-09-21.
 */
public class SyncStaticMethodTest {

    public static void main(String[] args) throws InterruptedException {
        SyncStaticMethodTest test = new SyncStaticMethodTest();
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    test.m1();
                    test.m2();
                }
            }).start();
        }
    }

    synchronized public static void m1() {
        System.out.println("m1------"+Thread.currentThread().getId()+"------start");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            //do nothing
        }
        System.out.println("m1------"+Thread.currentThread().getId()+"------end");
    }

    synchronized public static void m2() {
        System.out.println("m2------"+Thread.currentThread().getId()+"------start");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            //do nothing
        }
        System.out.println("m2------"+Thread.currentThread().getId()+"------start");
    }
}
