package com.micro.test.arithmetic;

/**
 * 约瑟夫问题
 *     firstChild指针不能动
 * Created by mycge on 2018/5/10.
 */
public class JosephusDemo {
    public static void main(String[] args) {
        CycLink cyclink = new CycLink();
        cyclink.setLen(5);
        cyclink.createLink();
        cyclink.setK(2);
        cyclink.setM(3);
        //cyclink.show();

        cyclink.play();
    }
}

/**
 * 环形链表
 */
class CycLink{

    Child firstChild = null;
    int len = 0;

    Child temp = null; //游标

    int k=0;
    int m=0;

    //设置链表大小
    public void setLen(int len){
        this.len = len;
    }

    //设置从第几个人开始数
    public void setK(int k){
        this.k = k;
    }

    //设置数几下
    public void setM(int m){
        this.m = m;
    }

    //开始玩
    public void play(){
        Child temp = this.firstChild;

        //1.先找到开始数数的人
        for(int i=1; i<k; i++){
            temp = temp.nextChild;
        }

        while (len != 1){
            //2.数m下
            for(int j=1; j<m; j++){
                temp = temp.nextChild;
            }

            //找到要出圈的前一个小孩
            Child temp2 = temp;
            while(temp2.nextChild != temp){
                temp2 = temp2.nextChild;
            }
            //3.将数到m的小孩，退出圈
            temp2.nextChild = temp.nextChild;

            //让temp指向下一个数数的小孩
            temp = temp.nextChild;

            this.len--;
        }

        System.out.println(temp.no);

    }

    //创建环形链表
    public void createLink(){

        for(int i=1; i<=len; i++){
            if(i == 1){
                Child child = new Child(i);
                this.firstChild = child;
                temp = child;
            }else {
                if(i == len){//创建最后一个小孩
                    Child child = new Child(i);
                    temp.nextChild = child;
                    temp = child;
                    temp.nextChild = this.firstChild;
                }else{
                    Child child = new Child(i);
                    temp.nextChild = child;
                    temp = child;
                }
            }
        }
    }

    //打印
    public void show(){
        Child temp = this.firstChild;
        do{
            System.out.println(temp.no);
            temp = temp.nextChild;
        }while (temp != this.firstChild);
    }

}

/**
 * 节点对象
 */
class Child{
    int no;
    Child nextChild;

    public Child(int no){
        this.no = no;
    }
}
