package com.micro.test.thread;

/**
 * Created by mycge on 2018/5/28.
 */
public class ThreadLocalTest {

}

class MyThreadScopeData{
    private static ThreadLocal<MyThreadScopeData> map = new ThreadLocal<MyThreadScopeData>();

    private MyThreadScopeData(){}

    public static MyThreadScopeData getThreadInstance(){
        MyThreadScopeData instance = map.get();
        if(instance == null){
            instance = new MyThreadScopeData();
            map.set(instance);
        }
        return instance;
    }

    private String name;
    private int age;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}