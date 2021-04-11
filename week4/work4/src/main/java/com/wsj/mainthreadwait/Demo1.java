package com.wsj.mainthreadwait;

import java.util.concurrent.*;

/**
 * 使用FutureTask,通过get拿到结果
 */
public class Demo1 {
    public static void main(String[] args) {
        long start=System.currentTimeMillis();

        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return sum();
            }
        });

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(task);

        try {
            int result = task.get();
            System.out.println("异步计算结果为："+result);

            System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
            executor.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
