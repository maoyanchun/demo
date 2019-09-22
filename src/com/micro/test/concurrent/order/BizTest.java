package com.micro.test.concurrent.order;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Description:
 * Created by mycge at 10:50 on 2019-09-22.
 */
public class BizTest {
    private static ExecutorService POOL = Executors.newFixedThreadPool(20);

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Future<String> future = POOL.submit(() -> {
                try {
                    return "线程-" + Thread.currentThread().getId() + "------生成订单号：" + OrderUtil.getOrderNo();
                } catch (Exception e) {
                    System.out.println("调订单服务获取订单号失败：" + e);
                }
                return null;
            });

            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        POOL.shutdown();
    }
}
