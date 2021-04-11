package com.wsj.mainthreadwait;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

public class SumTask3 implements Runnable {
    private CountDownLatch latch;

    public SumTask3(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        int result = sum();
        System.out.println("异步计算结果为："+result);

        // 唤醒传入的线程
        latch.countDown();
    }

    private int sum() {
        return fibo(36);
    }

    private int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
