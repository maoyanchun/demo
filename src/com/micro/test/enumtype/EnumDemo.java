package com.micro.test.enumtype;

/**
 * Created by mycge on 2018/5/25.
 */
public class EnumDemo {

    public static void main(String[] args) {
        EnumDemo enumDemo = new EnumDemo();
        enumDemo.function();
    }

    public void function(){
        ColorEnum[] color = ColorEnum.values();

        /*if(color != null && color.length > 0){
            for(int i = 0; i<color.length; i++){
                System.out.println(color[i].getIndex()+"---"+color[i].getName());
            }
        }*/

        System.out.println(SexEnum.getCode("女"));
        System.out.println(SexEnum.getValue(1.0));
    }
}
