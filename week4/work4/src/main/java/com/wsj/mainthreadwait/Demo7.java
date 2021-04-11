package com.wsj.mainthreadwait;

import java.util.concurrent.CompletableFuture;

/**
 * 使用CompletableFuture
 */
public class Demo7 {
    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(Demo7::sum);
        completableFuture.thenAccept((result -> {
            System.out.println("异步计算结果为：" + result);
        })).join();

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