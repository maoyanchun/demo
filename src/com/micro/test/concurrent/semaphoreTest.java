package com.micro.test.concurrent;

import java.util.concurrent.Semaphore;

/**
 * 场景：实验室共有5台实验机，现在A班有20人要进行实验。
 * 没人一次实验，无需排队
 * <p>
 * Semaphore
 * Created by mycge on 2019/8/19.
 */
public class semaphoreTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);  //一个计数信号量，维持5个许可证

        for (int i = 1; i <= 20; i++) {
            new Thread(new StudentTask(semaphore, Long.valueOf(i))).start();
        }
    }
}

class StudentTask implements Runnable{
    private Semaphore smp;
    private Long stuNo;

    StudentTask(Semaphore semaphore, Long stuNo){
        this.smp = semaphore;
        this.stuNo = stuNo;
    }

    @Override
    public void run() {
        try {
            smp.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId()+"------学生"+stuNo+",实验完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            smp.release();
        }

    }
}


