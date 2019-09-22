package com.micro.test.concurrent;

import com.micro.test.concurrent.task.WorkTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * 停止线程池中已经启动并且正在运行的线程
 * <p>
 * <p>
 * Created by mycge at 14:51 on 2019-09-21.
 */
public class ThreadTest {

    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<>());

        List<WorkTask> taskList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            WorkTask task = new WorkTask(i);
            pool.execute(task);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                //do nothing
            }
            taskList.add(task);
        }

//        dealPoolState(pool, taskList);
        dealPoolState1(pool, taskList);
    }

    private static void dealPoolState1(ThreadPoolExecutor pool, List<WorkTask> taskList) {
        try {
            System.out.println("pool start waiting...");
            taskList.forEach(task -> task.waitTask());

            Thread.sleep(3000);

            System.out.println("after 3s...");
            taskList.forEach(task -> task.notifyTask());
        } catch (InterruptedException e) {
            //do nothing
        }

    }


    private static void dealPoolState(ThreadPoolExecutor pool, List<WorkTask> taskList) {
        try {
            System.out.println("pool start shutdown...");
            Thread.sleep(3000);
            System.out.println("after 3s...");
        } catch (InterruptedException e) {
            //do nothing
        }

        taskList.forEach(task -> task.stopTask());

        pool.shutdown();
//        pool.shutdownNow();  //shutdown()、shutdownNow()都不能停掉线程池已经启动并且正在运行的线程
    }
}
