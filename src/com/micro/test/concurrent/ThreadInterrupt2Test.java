package com.micro.test.concurrent;

/**
 * Description:
 *      线程中断：2.循环判断中断状态
 * Created by mycge at 16:01 on 2019-09-21.
 */
public class ThreadInterrupt2Test {

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    double sum = 0.0;
                    int i = 1;
                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.println(Thread.currentThread().getId() + "---t1---" + i);
                        sum += (Math.PI + Math.E) / (double) i;
                        i++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();

        for (int i = 0; i < 50; i++) {
            System.out.println(Thread.currentThread().getId() + "---main---" + i);
            if (i == 20) {
                System.out.println("发出---t1---中断指令");
                t1.interrupt();
            }
        }
    }
}
