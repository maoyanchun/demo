package com.micro.test.collection;

import java.util.*;

/**
 * Created by mycge on 2018/5/8.
 */
public class CollectionDemo {

    public static void main(String[] args) {
        //setTest();
        mapTest();
    }

    //HashSet的底层是HashMap实现的,通过覆盖hashCode()和equals()方法来保证内容不重复
    public static void setTest(){
        Set<String> set = new HashSet<String>();
        set.add("aaa");
        set.add("aaa");
        set.add("bbb");
        Iterator<String> it = set.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }

    //一维数组+链表+红黑树
    public static void mapTest(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("a", 123);
        map.put("b", 456);
        map.put("c", 789);
        map.put(null, null);
        map.put("c", 111); //key值不变,111覆盖789
        map.put(null, null);
        System.out.println(map.size());
        System.out.println(map.get("c"));
    }
}
