package com.micro.test.concurrent.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 *      ReentrantLock & Condition监视器
 * Created by mycge at 1:44 on 2019-09-02.
 */
public class BoundedBuffer {

    final Lock lock = new ReentrantLock();  //唯一的ReentrantLock对象
    //一个lock对象有很多Condition监视器，每一个监视器都是一个双向链表。链表中每个Node都记录了被阻塞的线程对象
    final Condition notFull = lock.newCondition();  //监视buffer是否满了，如果为满了，不能put()，要等待
    final Condition notEmpty = lock.newCondition();  //监视buffer是否空了，如果为空，不能take()，要等待

    final Object[] items = new Object[20];  //buffer容量
    int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {
        System.out.println(Thread.currentThread().getId()+"，准备写入"+x.toString());
        lock.lock();  //排它锁，取数据和写数据不能同时进行
        try {
            while (count == items.length){
                System.out.println(Thread.currentThread().getId()+"，缓存满了，等待......");
                notFull.await(); //1.使当前线程进入waiting状态；2.释放当前线程的lock锁；3.收到signal通知后，继续从等待处向下执行程序
            }
            items[putptr] = x;
            System.out.println(Thread.currentThread().getId()+"，已写入"+x.toString());
            if (++putptr == items.length) putptr = 0;
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();  //排它锁，取数据和写数据不能同时进行
        try {
            while (count == 0){
                notEmpty.await();
            }
            Object x = items[takeptr];
            if (++takeptr == items.length) takeptr = 0;
            --count;
//            notFull.signal();  //取出数据后，唤醒一个等待的线程
            notFull.signalAll();
            return x;
        } finally {
            lock.unlock();
        }
    }
}
