package com.micro.test.arithmetic;

/**
 * 斐波那契数列（Fibonacci sequence）
 * 1、1、2、3、5、8、13、21、34、……
 *
 * F(0)=1，F(1)=1, F(n)=F(n-1)+F(n-2)（n>=2，n∈N*）
 * Created by mycge on 2018/5/29.
 */
public class FibonacciSequenceDemo {
    public static void main(String[] args) {
        //fibosequen();
        recurseFibosequen();
    }

    //递归实现
    public static int getFibo(int n){
        if(n==0 || n==1){
            return 1;
        }
        return getFibo(n-1)+getFibo(n-2);
    }


    public static void recurseFibosequen(){
        for(int i=0; i<20; i++){
            System.out.print(getFibo(i)+"\t");
        }
    }

    //非递归
    public static void fibosequen(){
        int a=1, b=1, c=0;

        System.out.println("斐波那契数列前20项为：");
        System.out.print(a+"\t"+b+"\t");

        for(int i=0; i<18; i++){
            c=a+b;
            a=b;
            b=c;
            System.out.print(c+"\t");
        }
    }
}
