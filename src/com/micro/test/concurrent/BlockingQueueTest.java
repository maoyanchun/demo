package com.micro.test.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;

/**
 * Description: 阻塞队列（实现是线程安全的）
 * <p>
 * 使用示例：基于典型的生产者 - 消费者场景
 * <p>
 * Created by mycge at 0:15 on 2019-08-30.
 */
public class BlockingQueueTest {
    public static void main(String[] args) {
        BlockingQueue q = new LinkedBlockingDeque();
//        BlockingQueue q = new SynchronousQueue();   //安全交付。  如果只有生产者的插入方法，没有数据取出，则线程阻塞在queue.put(new Integer(i));位置

        Producer p = new Producer(q);
        Consumer c1 = new Consumer(q);
        Consumer c2 = new Consumer(q);
        new Thread(p).start();
//        new Thread(c1).start();
//        new Thread(c2).start();
    }
}

/**
 *
 */
class Producer implements Runnable {
    private final BlockingQueue queue;

    Producer(BlockingQueue q) {
        queue = q;
    }

    public void run() {
        try {
            for (int i = 0; i < 50; i++) {
                System.out.println("正在往队列添加第" + i + "个元素");
                queue.put(new Integer(i));
                System.out.println("线程" + Thread.currentThread().getId() + "-往队列添加元素-" + i);
                Thread.sleep(200);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}

class Consumer implements Runnable {
    private final BlockingQueue queue;

    Consumer(BlockingQueue q) {
        queue = q;
    }

    public void run() {
        try {
            while (true) {
                Object obj = queue.take();
                System.out.println("线程" + Thread.currentThread().getId() + "------从队列获取元素------" + obj);

                Thread.sleep(300);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    /*void consume(Object x) {
    }*/
}
