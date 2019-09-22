package com.micro.test.concurrent.sync;

/**
 * Description:
 * Created by mycge at 1:43 on 2019-09-22.
 */
public abstract class Widget {

    synchronized public void doSomething(){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            //do nothing
        }
        System.out.println("parent is doing something...");
    }
}
