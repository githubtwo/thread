package com.example.thread;

import java.util.Arrays;
import java.util.List;

/**
 * @program: thread
 * @description: 2个线程，轮流获取执行权
 * @author: huangyizeng
 * @create: 2020-05-05 17:50
 */
public class ThreadTest {

    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();
        threadTest.turnExecute();
    }

    public void turnExecute(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> list = Arrays.asList("A","B","C","D","E");
                for(String string : list){
                    synchronized (ThreadTest.class){
                        ThreadTest.class.notifyAll();
                        System.out.println(string);
                        try {
                            ThreadTest.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Integer> list = Arrays.asList(1,2,3,4,5,6);
                for(Integer integer : list){
                    synchronized (ThreadTest.class){
                        ThreadTest.class.notifyAll();
                        System.out.println(integer);
                        try {
                            ThreadTest.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        System.out.println("begin print:");
        t1.start();
        t2.start();
    }
}
