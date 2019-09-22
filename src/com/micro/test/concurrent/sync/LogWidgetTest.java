package com.micro.test.concurrent.sync;

/**
 * Description: ReentrantLock也是重入锁
 * Created by mycge at 1:40 on 2019-09-22.
 */
public class LogWidgetTest extends Widget {

    public static void main(String[] args) {
        LogWidgetTest test = new LogWidgetTest();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.doSomething();
            }
        }).start();

    }

    synchronized public void doSomething() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            //do nothing
        }
        System.out.println("son is doing something...");

        super.doSomething();

        doOthers();
    }

    synchronized public void doOthers() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            //do nothing
        }
        System.out.println("son is doing others...");
    }
}
