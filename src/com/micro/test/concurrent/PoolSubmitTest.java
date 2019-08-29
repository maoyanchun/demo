package com.micro.test.concurrent;

import com.micro.test.concurrent.task.TaskResult;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Description:
 * Created by mycge at 0:32 on 2019-08-29.
 */
public class PoolSubmitTest {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool(); //无界线程池

        for (int i=0; i< 20; i++){
            try {
                Future<String> future = executor.submit(new TaskResult(i));
                System.out.println(future.get());

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
    }
}
