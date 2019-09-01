Condition
Object：wait()/notify()/notifyAll()
    wait -> Thread waiting
    Condition.await() -> Thread waiting
    
    Thread.Blocked状态 阻塞之后还要接受CPU分片
    Thread.waiting状态 阻塞之后不会占用CPU分片，不会占用CPU性能