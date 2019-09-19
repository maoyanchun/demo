package com.micro.test;

/**
 * Description:
 * Created by mycge at 1:20 on 2019-09-19.
 */
public class Main {
    public static void main(String[] args) {
        function1();
    }

    private static void function1() {
        int i = 1;
        int j = 2;

        if (++i == 2) {
            System.out.println("i = " + i);
        } else {
            System.out.println("warn");
        }
    }
}
