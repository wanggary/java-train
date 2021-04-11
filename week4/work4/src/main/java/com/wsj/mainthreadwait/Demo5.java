package com.wsj.mainthreadwait;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Condition接口
 */
public class Demo5 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        final Lock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();

        Thread thread = new Thread() {
            @Override
            public void run() {
                lock.lock();
                try {
                    int result = sum();
                    System.out.println("异步计算结果为：" + result);
                    // 通知主线程从await返回并重新获取锁
                    condition.signal();
                } finally {
                    lock.unlock();
                }
            }
        };
        thread.start();

        lock.lock();
        try {
            // 调用前先拿到锁，主线程释放锁并等待
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
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
