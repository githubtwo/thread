package com.example.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @program: thread
 * @description: Semaphore(信号量)，用于做限流处理，比如说同时只允许5五个人访问，超过五个人访问就需要等待，
 *                 类似这样的需求，下面的案例可以看出执行是五个五个的执行，等上一个五个执行完了，才会执行下一个
 * @author: huangyizeng
 * @create: 2020-05-13 20:46
 */
public class SemaphoreTest {
    public static void main(String[] args) {

        // 线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        // 只能5个线程同时访问
        final Semaphore semp = new Semaphore(2);
        // 模拟20个客户端访问
        for (int index = 0; index < 20; index++) {
            final int NO = index;
            Runnable run = new Runnable() {
                public void run() {
                    try {
                        System.out.println(NO + " 开始换取执行权");
                        // 获取许可
                        semp.acquire();
                        System.out.println("Accessing: " + NO + " 拿到了许可");
                        //模拟实际业务逻辑
                        Thread.sleep(2000);
                        // 访问完后，释放
                        semp.release();
                    } catch (InterruptedException e) {
                    }finally {
                        System.out.println("还有 " + semp.getQueueLength() + " 个等待的线程");
                        System.out.println(NO + " 执行完了，结束线程");
                    }
                }
            };
            exec.execute(run);
        }

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println(semp.getQueueLength());
        // 退出线程池
        exec.shutdown();
    }
}
