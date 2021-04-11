package com.wsj.mainthreadwait;

/**
 * 使用wait，主线程开启子线程后等待，子线程计算出结果后唤醒主线程
 */
public class Demo4 {
    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        Object o = new Object();

        Thread thread = new Thread() {
            @Override
            public void run() {
                int result = sum();
                System.out.println("异步计算结果为：" + result);

                synchronized (o) {
                    o.notify();
                }
            }
        };
        thread.start();

        // wait需要释放锁，拿到thread的对象锁
        synchronized (o) {
            try {
                o.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
