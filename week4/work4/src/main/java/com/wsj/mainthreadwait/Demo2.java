package com.wsj.mainthreadwait;

/**
 * 暴力循环等待法
 */
public class Demo2 {
    private static volatile int result = 0;

    private static class SumTask implements Runnable {
        @Override
        public void run() {
            result = sum();
        }
    }

    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        SumTask task = new SumTask();
        Thread thread = new Thread(task);
        thread.start();

        while (true) {
            if (result != 0) {
                break;
            }
        }

        System.out.println("异步计算结果为：" + result);
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
