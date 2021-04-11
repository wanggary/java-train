package com.wsj.mainthreadwait;

import java.util.concurrent.locks.LockSupport;

/**
 * 使用LockSupport
 */
public class Demo6 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        // 将当前线程即主线程传给子线程
        SumTask2 task = new SumTask2(Thread.currentThread());
        task.start();
        // 主线程park
        LockSupport.park();

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        System.out.println("Main thread exit");
    }
}
