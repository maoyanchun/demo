package com.micro.test.datatype;

/**
 * Java中八种基本类型
 * 1.整形: byte  short  int  long    (自动装箱的时候,在-128~127范围内,对象在对象池中分配,对象池中的对象是共享的)
 * 2.浮点型: float  double    (在任何范围内自动装箱的时候,都不在对象池中分配)
 * 3.字符型: char    (在0~127范围内自动装箱的时候,对象在对象池中分配,对象池中的对象是被共享的)
 * 4.布尔型: boolean (true/false)    (所有boolean类型在自动装箱的时候,对象在对象池中分配)
 * Created by mycge on 2018/5/15.
 */
public class BaseDemo {

    public static void main(String[] args) {
        getBaseInfo();
    }

    public static void getBaseInfo(){
        System.out.println("java基本类型-整型：");
        System.out.println("Byte: "+Byte.SIZE / 8);
        System.out.println("Short: "+Short.SIZE / 8);
        System.out.println("Integer: "+Integer.SIZE / 8);
        System.out.println("Long: "+Long.SIZE / 8);

        System.out.println("java基本类型-浮点型：");
        System.out.println("Float: "+Float.SIZE / 8);
        System.out.println("Double: "+Double.SIZE / 8);

        System.out.println("java基本类型-字符型：");
        System.out.println("Character: "+Character.SIZE / 8);

        System.out.println("java基本类型-布尔型：");
        System.out.println("Boolean: "+Boolean.toString(false));
    }
}
