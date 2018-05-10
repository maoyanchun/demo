package com.micro.test.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mycge on 2018/5/8.
 */
public class TestDemo {

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("a", 123);
        map.put("b", 456);
        map.put("c", 789);
        map.put(null, null);
        map.put("c", 111);
        map.put(null, null);
        //System.out.println(map.size());
        //System.out.println(map.get("c"));

        int a = 0;
        int b = 11;
        System.out.println((a+b)/2);
    }
}
