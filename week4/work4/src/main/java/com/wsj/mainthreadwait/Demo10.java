package com.wsj.mainthreadwait;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 使用BlockingQueue
 */
public class Demo10 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1);

        Thread thread = new Thread() {
            @Override
            public void run() {
                int sum = sum();
                try {
                    queue.put(sum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        try {
            // 主线程从队列中获取数据，take方法会阻塞队列
            Integer result = queue.take();
            System.out.println("异步计算结果为：" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        System.out.println("Main thread exit");
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
