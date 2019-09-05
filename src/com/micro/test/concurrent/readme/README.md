Condition
Object：wait()/notify()/notifyAll()
    wait -> Thread waiting
    Condition.await() -> Thread waiting
    
    Thread.Blocked状态 阻塞之后还要接受CPU分片
    Thread.waiting状态 阻塞之后不会占用CPU分片，不会占用CPU性能
    
lock.lock();
    --lock.await();
    --lock.signal()/lock.signalAll();
lock.unlock();

-- ------20190903
volatile关键字与多线程
中间没有缓存区，直接从主存拿的数据

-- ------20190904

AtomicInteger操作原理

native修饰的，没有具体代码实现，用其它语言实现的，比如C语言，java里面调，需要native修饰
unsafe（C语言） jdk1.8
jdk1.6看 getAndIncrement()  getAndDecrement()
注意：CAS是compareAndSwapInt()

public native long objectFieldOffset(Field f)
//用ClassLoader加载class后，class中的每个属性，都有一个唯一的偏移量标识（这是相对，不是绝对）

public final int getAndIncrement(){
    for(;;){  //死循环
        int current = get();
        int next = current + 1;
        if(compareAndSet(current, next)) //比较并且替换
            return current;  //真->返回
    }
}
//Atomically sets the value to the given updated value
public final boolean compareAndSet(int expect, int update){
    return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
}

//compareAndSwapInt(...) C语言写的
public final native boolean compareAndSwapInt(Object o, long offset,
                                                int expected,
                                                int x)
                                                   