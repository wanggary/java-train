package com.wsj.mainthreadwait;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 使用CyclicBarrier
 */
public class Demo9 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        // 等待2个线程包括主线程
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        SumTask4 task = new SumTask4(cyclicBarrier);
        new Thread(task).start();

        try {
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        System.out.println("Main thread exit");
    }
}
