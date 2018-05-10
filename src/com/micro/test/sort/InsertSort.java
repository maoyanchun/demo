package com.micro.test.sort;

/**
 * 内部排序：
 *     插入式排序：插入排序
 *     思想：把数组元素看成：有序表+无序表， 初始状态：有序表中只有一个元素arr[0], 无序表中元素arr[1]~arr[n-1]，从无序表中依次选择元素：arr[1]、arr[2]、...、arr[n-1]插入到有序表
 * Created by mycge on 2018/5/10.
 */
public class InsertSort {
    public static void main(String[] args) {

        int[] arr = {6, 5, 9, -10, 8, 5, 20, 17, 4, 8, 100, -2};
        sort(arr);

        System.out.println("排序后的结果:");
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i]+" ");;
        }

    }

    public static void sort(int[] arr) {

        for(int i = 1; i < arr.length; i++){
            int insertVal = arr[i];
            int index = i-1;

            while(index >= 0 && insertVal < arr[index]){
                arr[index+1] = arr[index];
                index --;
            }
            arr[index+1] = insertVal;

        }

    }
}
