package com.wsj.mainthreadwait;

public class SumTask extends Thread{

    @Override
    public void run() {
        int result = sum();
        System.out.println("异步计算结果为："+result);
    }

    private int sum() {
        return fibo(36);
    }

    private int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
