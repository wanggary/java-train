package com.wsj.mainthreadwait;

import java.util.concurrent.locks.LockSupport;

public class SumTask2 extends Thread {
    private Thread thread;

    public SumTask2(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        int result = sum();
        System.out.println("异步计算结果为："+result);

        // 唤醒传入的线程
        LockSupport.unpark(thread);
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
