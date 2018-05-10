package com.micro.test.sort;

/**
 * 内部排序：
 *     选择式排序：选择排序
 *     思想：从arr[0]~arr[n-1]中选择一个最小的元素与arr[0]互换，从arr[1]~arr[n-1]中选择一个最小的元素与arr[1]互换，......
 * Created by mycge on 2018/5/10.
 */
public class SelectSort {

    public static void main(String[] args) {

        int[] arr = {6, 5, 9, -10, 8, 5, 20, 17, 4, 8, 100, -2};
        sort(arr);

        System.out.println("排序后的结果:");
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i]+" ");;
        }

    }

    public static void sort(int[] arr) {
        int temp = 0;
        for(int i = 0; i < arr.length-1; i++){
            int minVal = arr[i];
            int minIndex = i;

            for(int j = i+1; j < arr.length; j++){
                if(minVal > arr[j]){
                    minVal = arr[j];
                    minIndex = j;
                }
            }

            temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;

        }

    }
}
