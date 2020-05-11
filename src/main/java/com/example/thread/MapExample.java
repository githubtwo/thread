package com.example.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @program: thread
 * @description:
 * @author: huangyizeng
 * @create: 2020-04-28 22:28
 */
@Slf4j
public class MapExample {
    private static Map<Integer,Integer> map = new HashMap<>();
    private static int threadNum = 1;
    private static int clientNum = 5000;

    public static void main(String[] args) {

        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadNum);
        for(int index = 0; index < clientNum; index++){
            final int threadNum = index;
            exec.execute(() ->{
                try {
                    semaphore.acquire();
                    func(threadNum);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        exec.shutdown();
        log.info("size:{}",map.size());
    }

    public static void func(int threadNum){
        map.put(threadNum,threadNum);
    }
}
