package com.micro.test.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Description:
 * java.util.ConcurrentModificationException
 * Created by mycge at 9:31 on 2019-09-22.
 */
public class Thread1Test {

    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>(20);  //ConcurrentModificationException
//        List<Integer> list = new Vector<>(20);

        /**
         * ArrayList相对Vector执行效率更高，Vector是锁整个方法
         *
         * public boolean remove(Object o) {
         *             synchronized (mutex) {return c.remove(o);}
         *         }
         *    synchronizedList锁代码块的方式，某些方法加了锁代码块，可以并发读，作用域小，并发高；而Vector是同一个对象所有方法都受影响
         *
         */
        List<Integer> list = Collections.synchronizedList(arrayList);  //Collections 集合工具类

        for (int i = 0; i < 20; i++) {
            list.add(i);
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {

        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (list) {  //手动锁定
                    for (Integer i : list) {
                        System.out.println("线程-" + Thread.currentThread().getId() + "---is read---" + i);
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {

                        }
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    list.remove(i); //Vector的remove()有synchronized，进入前必须获得锁
                    System.out.println("线程-" + Thread.currentThread().getId() + "---is remove---" + i);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {

                    }
                }
            }
        }).start();
    }
}
