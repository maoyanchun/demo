package com.micro.test.thread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器调度线程任务
 * TimerTask implements Runnable
 * Created by mycge on 2018/5/28.
 */
public class TimerDemo {

    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("bombing");
            }
        }, 3000,1000);
    }
}
