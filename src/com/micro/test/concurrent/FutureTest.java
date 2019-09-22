package com.micro.test.concurrent;

import com.micro.test.concurrent.task.TaskResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Description:  Future.cancel(true) 取消正在执行的任务
 * Created by mycge at 21:09 on 2019-09-21.
 */
public class FutureTest {

    public static void main(String[] args) {
        ExecutorService pool = new ThreadPoolExecutor(3, 3, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

        List<Future> futureList = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            Future<String> future = pool.submit(new TaskResult(i));
            futureList.add(future);
            /*try {
                String result = future.get();
                System.out.println(result);
            } catch (InterruptedException e) {

            } catch (ExecutionException e) {

            }*/
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }
        System.out.println("取消线程任务------start-----");
        futureList.forEach(future -> future.cancel(true));  //取消正在执行的任务（之后提交的任务就不执行了）

        pool.shutdown();
    }
}
