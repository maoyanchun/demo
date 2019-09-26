package com.micro.test.concurrent;

/**
 * Description:
 *      线程中断：1.中断异常
 * Created by mycge at 15:44 on 2019-09-21.
 */
public class ThreadInterruptTest {

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 1; i <= 50; i++) {
                        System.out.println(Thread.currentThread().getId() + "---t1---" + i);
                        //做一个大的浮点型运算
                        double d = 0;
                        for (int m = 0; m < 1000000000; m++) {
                            d += (Math.PI + Math.E) / (double) i;
//                            System.out.println("d: "+d);
                        }
                        Thread.sleep(100);  //如果当前线程没有blocking状态，就没有中断异常
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
