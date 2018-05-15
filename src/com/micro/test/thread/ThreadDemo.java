package com.micro.test.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Created by mycge on 2018/5/14.
 */
public class ThreadDemo
{
    public static volatile int i = 0;

    Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        ThreadDemo threadDemo = new ThreadDemo();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadDemo.fun4(Thread.currentThread());
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadDemo.fun4(Thread.currentThread());
            }
        },"t2");

        t1.start();
        t2.start();

    }

    public void fun1(){
        synchronized (new ThreadDemo()){

        }
    }

    public synchronized void fun2(){

    }

    public void fun3(){
        synchronized (ThreadDemo.class){

        }
    }

    //需要参与同步的代码
    public void fun4(Thread thread){
        /*lock.lock();*/
        if(lock.tryLock()){
            try {
                System.out.println("线程名"+thread.getName() + "获得了锁");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
                System.out.println("线程名"+thread.getName() + "释放了锁");
            }
        }else {
            System.out.println("我是"+Thread.currentThread().getName()+"有人占着锁，我就不要啦");
        }

    }

}
