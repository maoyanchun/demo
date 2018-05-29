package com.micro.test.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by mycge on 2018/5/28.
 */
public class CallableAndFuture {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        Future<String> future =
            threadPool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                return "hello";
            }
        });

        System.out.println("等待结果...");
        try {
            System.out.println("拿到结果："+future.get());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
