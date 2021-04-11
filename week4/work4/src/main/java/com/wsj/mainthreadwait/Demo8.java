package com.wsj.mainthreadwait;

import java.util.concurrent.CountDownLatch;

/**
 * 使用CountDownLatch
 */
public class Demo8 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(1);

        SumTask3 task = new SumTask3(countDownLatch);
        new Thread(task).start();

        // 主线程await
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        System.out.println("Main thread exit");
    }
}
