package com.micro.test.sort;

/**
 * 内部排序：
 *     交换式排序：快速排序 (递归思想)
 * Created by mycge on 2018/5/9.
 */
public class QuickSort {

    public static void main(String[] args) {

        int[] arr = {6, 5, 9, -10, 8, 5, 20, 17, 4, 8, 100, -2};
        sort(0, arr.length-1, arr);

        System.out.println("排序后的结果:");
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i]+" ");;
        }

    }

    public static void sort(int left, int right, int[] arr){
        int l = left;
        int r = right;
        int pivot = arr[(left+right)/2];
        int temp = 0;

        while(l < r){

            while (arr[l] < pivot) l++;
            while(arr[r] > pivot) r--;

            if(l >= r) break;

            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            if(arr[l] == pivot) --r;
            if(arr[r] == pivot) ++l;

        }

        if(l == r){
            l++;
            r--;
        }

        if(left < r) sort(left, r, arr);
        if(right > l) sort(l, right, arr);
    }
}
