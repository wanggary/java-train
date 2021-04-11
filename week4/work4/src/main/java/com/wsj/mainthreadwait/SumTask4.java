package com.wsj.mainthreadwait;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class SumTask4 implements Runnable {
    private CyclicBarrier barrier;

    public SumTask4(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        int result = sum();
        System.out.println("异步计算结果为："+result);

        try {
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
