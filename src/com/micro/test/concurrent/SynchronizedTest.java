package com.micro.test.concurrent;

/**
 * Description:
 *
 * 一个线程进入了synchronized的同步方法，会给当前对象加锁，其它synchronized方法会受到影响
 * 注意：对象加了锁后，不会对没有synchronized的方法产生影响
 *
 *
 * Created by mycge at 22:20 on 2019-09-21.
 */
public class SynchronizedTest implements Runnable {
    int b = 100;

    @Override
    public void run() {
        try {
            m1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public void m1() throws InterruptedException {
        b = 1000;
        Thread.sleep(5000);
        System.out.println("m1------, b = " + b);
    }

    synchronized public void m2() throws InterruptedException {
        Thread.sleep(2000);
        b = 2000;
        System.out.println("m2------, b = " + b);
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedTest test = new SynchronizedTest();
        Thread t = new Thread(test);
        t.start();  //启动了一个新线程，执行run()

        test.m2(); //当前调用模式，test.m2()执行时，m2前的synchronized，没起作用

        System.out.println("main------, b = " + test.b);
    }
}
