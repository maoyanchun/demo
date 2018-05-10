package com.micro.test.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class RefDemo {

    public static void main(String[] args) {
       /* Object obj = new Object();
        SoftReference<Object> ref = new SoftReference<Object>(obj);
        obj = null;
        System.gc();
        System.out.println(ref.get());*/

       /*String key = new String("polysoft");
       String value = new String("www.polysoft.com");
        //Map<String, String> map = new WeakHashMap<String, String>();
        Map<String, String> map = new HashMap<String, String>();

        map.put(key, value);
        System.out.println(map);
        key = null;
        System.out.println(map);
        System.gc();
        System.out.println(map);*/

        //强引用不会出现在引用队列当中
        Object obj = new Object();
        ReferenceQueue<Object> queue = new ReferenceQueue<Object>();
        WeakReference<Object> ref = new WeakReference<Object>(obj, queue);
        System.out.println(queue.poll());
        obj = null;
        System.gc();
        //引用保存到引用队列需要时间
        try {
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(queue.poll());


    }
}
