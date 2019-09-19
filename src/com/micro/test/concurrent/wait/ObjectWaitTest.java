package com.micro.test.concurrent.wait;

/**
 * Description: 子线程＆主线程交互
 * java.lang.Object: wait()、  notify()一起出现，必须先执行wait(), +Synchronized关键字
 * <p>
 * Created by mycge at 23:45 on 2019-09-08.
 */
public class ObjectWaitTest {

    public static void main(String[] args) {
        Object obj = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int sum = 0;
                for (int i = 0; i < 100; i++) {
                    sum += i;
                }
                try {
                    synchronized (obj) {
                        obj.wait();
                    }
                    System.out.println("sum = " + sum);
                } catch (Exception e) {

                }

            }
        }).start();


        try {
            Thread.sleep(2000);
            synchronized (obj) {
                obj.notify();
            }
        } catch (Exception e) {

        }

    }
}
