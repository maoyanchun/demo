package com.micro.test.sort.search;

/**
 * 二分查找又称折半查找
 *
 * 二分查找的先决条件是查找的数列必须是有序的，采用跳跃式方法进行查找，即先以有序数列的中点位置为比较对象
 * 折半查找方法适用于不经常变动而查找频繁的有序列表
 * Created by mycge on 2018/5/29.
 */
public class BinarySearch {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5, 7, 8, 9, 21, 22, 26};
        System.out.println("元素的INDEX位置：");
        //System.out.println(search(arr, 9));
        System.out.println(recurseSearch(arr, 9, 0, arr.length-1));
    }

    //递归实现
    public static int recurseSearch(int[] arr, int target, int left, int right){
        if(arr != null){
            if(target<arr[left] || target>arr[right] || left>right){
                return -1;
            }
            int middle = (left+right)/2;
            if(target > arr[middle]){
                return recurseSearch(arr, target, middle+1, right);
            }else if(target < arr[middle]){
                return recurseSearch(arr, target, left, middle-1);
            }else {
                return middle;
            }
        }
        return -1;
    }

    //非递归
    public static int search(int[] arr, int target){
        int left = 0;
        int right = arr.length-1;
        int middle = 0;

        if(arr != null){
            if(target<arr[left] || target>arr[right] || left>right){
                return -1;
            }
            while (left <= right){
                middle = (left+right)/2;
                if(target > arr[middle]){
                    left = middle+1;
                }else if(target < arr[middle]){
                    right = middle-1;
                }else {
                    return middle;
                }
            }
        }
        return -1;
    }

}


