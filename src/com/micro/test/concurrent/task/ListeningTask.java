package com.micro.test.concurrent.task;

/**
 * 监听任务
 */
public class ListeningTask implements Runnable{

    private Integer i;

    public ListeningTask(Integer i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println("线程-"+Thread.currentThread().getId()+"---Begining---"+i);

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(i == 5){
            throw new RuntimeException("线程"+i+"执行异常...");
        }

        System.out.println("线程-"+Thread.currentThread().getId()+"---Ending---"+i);
    }
}
