package com.micro.test.thread;

/**
 * Created by mycge on 2018/5/28.
 */
public class TraditionalThread {

    public static void main(String[] args) {

        Thread t1 = new Thread(){

            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                }catch (Exception e){
                    e.printStackTrace();
                }
                for(int i=0; i<10; i++){
                    System.out.println(Thread.currentThread().getName()+"正在运行");
                }

            }
        };
        //t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                }catch (Exception e){
                    e.printStackTrace();
                }
                for(int i=0; i<10; i++){
                    System.out.println(Thread.currentThread().getName()+"正在运行");
                }
            }
        });
        //t2.start();

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable:"+Thread.currentThread().getName()+"正在运行");
            }
        }){
            @Override
            public void run() {
                System.out.println("Thread:"+Thread.currentThread().getName()+"正在运行");
            }
        };
        t3.start();

    }
}
