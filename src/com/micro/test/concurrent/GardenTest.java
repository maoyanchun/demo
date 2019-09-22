package com.micro.test.concurrent;

import com.micro.test.concurrent.task.Garden2;

/**
 * Description:
 * Created by mycge at 21:34 on 2019-09-21.
 */
public class GardenTest {

    public static void main(String[] args) {
//        Garden1 garden = new Garden1();
        Garden2 garden = new Garden2();

        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    garden.openDoor();
                    garden.enterRoom();
                }
            }).start();
        }
    }
}
