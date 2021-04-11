package com.wsj.mainthreadwait;

/**
 * 主线程调用子线程的join方法，等待子线程执行完再继续主线程
 */
public class Demo3 {

    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        SumTask sumTask = new SumTask();
        sumTask.start();

        try {
            sumTask.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        System.out.println("Main thread exit");
    }
}
