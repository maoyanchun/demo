package com.micro.test.concurrent.task;

/**
 * Description:
 * Created by mycge at 14:52 on 2019-09-21.
 */
public class WorkTask implements Runnable {
    private int taskNo;

    private boolean bRun = true;

    /**
     * 线程等待标识
     */
    private boolean bWait = false;

    public WorkTask(int taskNo) {
        this.taskNo = taskNo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            if (bRun) {
                if (bWait) {
                    try {
                        System.out.println(Thread.currentThread().getId() + "---start waiting---任务编号：" + this.taskNo);
                        synchronized (this) {
                            this.wait();  //wait()方法的调用必须要在synchronized代码块中，否则没有wait blocking效果
                        }
                    } catch (InterruptedException e) {
                        //do nothing
                    }
                }

                System.out.println(Thread.currentThread().getId() + "------------is running------任务编号：" + this.taskNo);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    //do nothing
                }
            }
        }
    }

    public void stopTask() {
        this.bRun = false;
    }

    public void waitTask() {
        this.bWait = true;
    }

    public void notifyTask() {
        this.bWait = false; //回来循环的时候不能再进入
        synchronized (this){
            this.notify();
        }
    }
}
