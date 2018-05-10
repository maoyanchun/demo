package com.micro.test.sort;

/**
 * 内部排序：
 *     交换式排序：冒泡排序
 * Created by mycge on 2018/5/10.
 */
public class BubbleSort {

    public static void main(String[] args) {

        int[] arr = {6, 5, 9, -10, 8, 5, 20, 17, 4, 8, 100, -2};
        sort(arr);

        System.out.println("排序后的结果:");
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i]+" ");;
        }

    }

    public static void sort(int[] arr){
        int temp = 0;
        for(int i = 0; i < arr.length - 1; i++){

            for(int j = 0; j < arr.length - 1 - i; j++){
                if(arr[j] > arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }

        }

    }

}
