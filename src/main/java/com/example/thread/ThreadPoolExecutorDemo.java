package com.example.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;

/**
 * @TUDO
 * @Author huangyizeng
 * @Date 2020/4/26 15:18
 * @Version 1.0
 **/
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        Vector<String> vector = new Vector<>();
        ArrayList<String> list = new ArrayList<>();
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        Thread thread = new Thread();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//                System.out.println("asdf123");
//            }
//        }).start();
//        new MyRunnable().run();
//        (new Runnable(){
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//                System.out.println("asdf");
//            }
//        }).run();
//
//        Semaphore semaphore = new Semaphore(5, true);
//
//        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(5);
//        ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, queue);
//        for(int i=0; i<15; i++){
//            MyTask myTask = new MyTask(i);
//            pool.execute(myTask);
//            System.out.println("线程池中的线程数目："+pool.getPoolSize()+",队列中等待执行的任务数量："+pool.getQueue().size()+",已执行完的任务数目："+pool.getCompletedTaskCount());
//
//        }
//        pool.shutdown();
        testCacheThreadPool();
//        testFixThreadPool();
//        testScheduleThreadPool();
//        testSingleThreadPool();
    }

    /**
     * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
     */
    public static void testCacheThreadPool() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
//            try {
//                Thread.sleep(index * 1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            cachedThreadPool.execute(new Runnable() {

                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    System.out.println(index + "====================");
                }
            });
        }
    }

    /**
     * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
     */
    public static void testFixThreadPool() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName());
                        System.out.println(index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * 创建一个定长线程池，支持定时及周期性任务执行
     */
    public static void testScheduleThreadPool(){
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(new Runnable() {

            @Override
            public void run() {
                System.out.println("delay 3 seconds");
            }
        }, 3, TimeUnit.SECONDS);
    }

    public static void testSingleThreadPool(){
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName());
                        System.out.println(index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}

class MyTask implements Runnable{

    private int taskNum;

    public MyTask(int num){
        this.taskNum = num;
    }

    public void run(){
        System.out.println("正在执行task " + taskNum);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task "+taskNum+"执行完毕");
    }
}

class MyRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        System.out.println("this is MyRunnable");
    }
}