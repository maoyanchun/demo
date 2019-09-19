程序：是计算机指令的集合；程序是一组静态指令集，不占用系统运行资源，不能被系统调度，也不能作为独立运行的单位，它以文件的形式存储在磁盘上。

进程：是一个程序在其自身的地址空间中的一次执行活动；进程是资源申请、调度和独立运行的单位，它使用系统中的运行资源。
        一个程序可以对应多个进程
        
线程：是进程中的一个单一的连续控制流程；由操作系统负责调度
        线程没有独立的存储空间，是和所属进程中的其它线程共享一个存储空间；一个进程可以拥有多个线程
        线程的本质是cpu分片运行，不是真正的同时执行！
        线程中的run()执行完，就会自动结束


java.lang.Thread.State (Enum)
Thread t = new Thread();  //状态为Thread.NEW
t.start();  //状态为Thread.RUNNABLE
    Runnable 没有获取cpu；Running 获得了cpu
    在某个线程中调用wait()方法，使当前线程进入Thread.WAITING状态
    synchronized： 排它锁，隐形锁，锁定后其它线程会进入Thread.BLOCKED
Thread.BLOCKED   阻塞等待状态，需要占用cpu分片
Thread.WAITING   等待状态，不占用cpu分片


Synchronized与Lock
Synchronized是Java JDK内置的关键字，JVM级别的，更为重要
Lock是JDK后期扩展的线程操作的接口，使用lock()获得锁，必须使用unlock()解锁


class ReentrantLock implements Lock, java.io.Serializable {...}
    一个可重入互斥Lock具有与使用synchronized方法和语句访问的隐式监视锁相同的基本行为和语义
interface ReadWriteLock {...}
class ReentrantReadWriteLock implements ReadWriteLock, java.io.Serializable {...}
public interface Lock {...}
class ReentrantLock implements Lock, java.io.Serializable {...}


interface ExecutorService extends Executor {...}
    ForkJoinPool
    ScheduledThreadPoolExecutor
    ThreadPoolExecutor(最重要)
线程池工具类：Executors 
    ExecutorService newCachedThreadPool()  //无界线程池
    ExecutorService newFixedThreadPool(int nThreads)  //有界线程池
    ExecutorService newSingleThreadExecutor()  //单一线程池
    ScheduledExecutorService newScheduledThreadPool(int corePoolSize)
    ScheduledExecutorService newSingleThreadScheduledExecutor()
    ExecutorService newWorkStealingPool()
自定义线程池

public static ExecutorService newCachedThreadPool(ThreadFactory threadFactory) {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                      60L, TimeUnit.SECONDS,
                                      new SynchronousQueue<Runnable>(),
                                      threadFactory);
    }
//无界线程池
如果一次执行20个任务，由于corePoolSize=0，所以20个任务都会被加入BlockingQueue
启动新线程，执行队列中的任务
最多可以启动20个线程。
如果20个线程执行完毕。从线程闲置开始倒计时，计时60s后关闭线程
最后线程池中的线程全部关闭

public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                                      0L, TimeUnit.MILLISECONDS,
                                      new LinkedBlockingQueue<Runnable>());
    }
//有界线程池
corePoolSize的数量=nThreads
如果一次执行20个任务，线程池中会启动nThreads个线程，剩下的任务进入队列BlockingQueue。
keepAliveTime=0，表示某个线程任务执行完毕后，马上检查是否还有其它任务
最后线程池中的线程，即便没有任务，也会保留nThreads


interface Queue<E> extends Collection<E> 
interface BlockingQueue<E> extends Queue<E>

BlockingQueue的实现是线程安全的
    解释：通过ReentrantLock中的Condition，来实现阻塞方式的添加元素，和阻塞式的提取元素
生产者-消费者模式：
    生产者插入的元素，多个消费者去抢。但是只有一个消费者能抢到
    take()抢到后，马上移除元素    
设计模式中的OBSERVER(观察者) - 对象行为型模式 （又称为PUB/SUB模式）
    被观察者生成消息，会通知所有观察者（监听者）  （通知信息，会被所有的监听者收到）
    采用对象引用通知的模式实现的
JMS即Java消息服务（Java Message Service）中也有PUS/SUB：
    分布式架构（异步消息通知）   
JMS PTP(Point-To-Point)模式
基于BlockingQueue实现的Producer-Consumer模式，与JMS模式中PTP模式基本一致


ReentrantLock显示锁，可重入互斥锁(排它锁)，虽然是排它锁，但是等待时间可以设置tryLock(long timeout, TimeUnit unit)
Synchronized隐式锁，排它锁，synchronized锁住资源后，其它线程只能是死等
从ReentrantLock中创建监视器new Condition()， 功能与synchronized+Object中的wait()/notify()


Object.wait() -> Thread waiting
Condition.await() -> Thread waiting 
    Thread.Blocked状态 阻塞之后还要接受CPU分片
    Thread.waiting状态 阻塞之后不会占用CPU分片，不会占用CPU性能
    
lock.lock();
    --condition.await();
    --condition.signal()/condition.signalAll();
lock.unlock();


在多线程的环境下，要使用共享资源，要保证共享资源的多线程安全性，怎么办？
1.Synchronized 隐式锁
2.ReentrantLock 显示锁
3.CAS  无锁方案，乐观锁的一种 （compareAndSwapInt() -- 比较并交换）


http://www.docjar.com/html/api/sun/misc/Unsafe.java.html
native修饰的，没有具体代码实现，用其它语言实现的，比如C语言，java里面调，需要native修饰

class AtomicInteger  采用CAS指令实现int值的原子更新
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
//用ClassLoader加载class后，class中的每个属性，都有一个唯一的偏移量标识（这是相对，不是绝对）
public final native boolean compareAndSwapInt(Object o, long offset,
                                                int expected,
                                                int x)
*/ Object o：当前对象的引用(指针) 
1.Object o代表了实际内存的首地址(绝对地址)，offset表示属性的偏移量(相对地址)；这样通过CAS指令可以马上获得value的当前值
  Object o, long offset这两个参数可以算出value在内存中的实际值
  expected 是指value的当前值  int current = get();  --缓存值（实际内存随后可能被修改）
2.return true 表示CAS替换成功（表示缓存值与内存当前值相同，即value没有被其它线程修改）
3.如果多个线程同时进入compareAndSwapInt()会怎样？
    这里没有锁，由CAS的机器指令来保证是原子操作


volatile关键字与多线程,中间没有缓存区，直接从主存拿的数据
ReentrantLock
Condition
AtomicInteger
CAS


ArrayBlockingQueue 对比 LinkedBlockingQueue
链接队列通常具有比基于阵列的队列更高的吞吐量，但在大多数并发应用程序中的可预测性能较低
LinkedBlockingQueue 基于链表实现
                    采用的是双锁机制（两个锁，两个监视器）
                    put()和take()在同一时间，同一个队列中的put()和take()可以并行
                    吞吐量好
                    创建：LinkedBlockingQueue() 容量为Integer.MAX_VALUE；LinkedBlockingQueue(int capacity)    
ArrayBlockingQueue 基于数组
                   采用的是単锁机制（一个锁，两个监视器）
                   put()和take()在同一时间，同一个队列中的put()和take()不能并行
                   吞吐量差
                   队列容量是固定的
                   

java.util.concurrent.locks.LockSupport  线程阻塞与通知机制 
static void park(Object blocker)
        禁止当前线程进行线程调度，除非许可证可用（如果当前线程有许可证，就不会阻塞）
static void unpark(Thread thread)
        为给定的线程提供许可证（如果尚未完成）


//TODO...

-- ------20190910
SynchronousQueue

newCachedThreadPool   

-- ------20190917
java.util.ConcurrentModificationException  并发修改异常

Collections  

订单号生成
Mysql
Oracle
并发量很高，数据库集群，分库，想区一个随时增长又不冲突的序列号
像天猫、京东，从订单服务中心获取唯一号，再插入库中

OrderInfo oi = order.getInstance();
AtomicLong al = new AtomicLong(oi.getOrderAinitialValue());
al.getAndIncrement()

如果服务器宕机，AtomicLong就会从新开始，会产生冲突，把它配置在配置文件中，重启时再延续增加
order.properties
