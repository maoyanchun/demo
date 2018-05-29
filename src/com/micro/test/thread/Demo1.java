package com.micro.test.thread;

/**
 * 子线程循环10次，接着主线程循环100，接着又回到子线程循环10次，接着再回到主线程循环100，如此循环50次
 *
 * 子线程与主线程之间同步通信：wait()、notify()、notifyAll()
 * Created by mycge on 2018/5/28.
 */
public class Demo1 {

    public static void main(String[] args) {
        final Business business = new Business();
        //子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int m=1; m<=50; m++){
                    business.sub(m);
                }
            }
        }).start();

        //主线程
        for(int m=1; m<=50; m++){
            business.main(m);
        }
    }
}

class Business{
    private Boolean bShouldSub = true;

    public synchronized void sub(int m){
        while(!bShouldSub){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int i=1; i<=10; i++){
            System.out.println("sub thread sequence of "+i+", loop of "+m);
        }
        bShouldSub = false;
        this.notify();
    }

    public synchronized void main(int m){
        while(bShouldSub){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int j=1; j<=100; j++){
            System.out.println("main thread sequence of "+j+", loop of "+m);
        }
        bShouldSub = true;
        this.notify();
    }
}
