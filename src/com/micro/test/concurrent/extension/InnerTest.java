package com.micro.test.concurrent.extension;

/**
 * Description:
 * Created by mycge at 0:11 on 2019-09-23.
 */
public class InnerTest {
    public static void main(String[] args) {

        /**
         * 将Runnable的子类对象传递给Thread的构造方法
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    //do nothing
                }
                System.out.println("匿名内部类方式1创建线程------将Runnable的子类对象传递给Thread的构造方法");
            }
        }).start();

        /**
         * 继承Thread类，重写run方法
         */
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //do nothing
                }
                System.out.println("匿名内部类方式2创建线程------继承Thread类，重写run方法");
            }
        }.start();


    }
}
