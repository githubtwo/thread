package com.example.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @program: thread
 * @description: 返回值线程
 * @author: huangyizeng
 * @create: 2020-05-13 21:29
 */
public class CallableThreadTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> callable = new CallableThread();
        FutureTask<Integer> task = new FutureTask<Integer>(callable);

        Thread thread = new Thread(task);
        thread.start();
        Integer sum = task.get();
        System.out.println(sum);
    }

}

class CallableThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int i = 20;
        for(; i <= 100; i++) {
            System.out.println(Thread.currentThread().getName()+"..."+i);
        }
        return i;
    }
}
