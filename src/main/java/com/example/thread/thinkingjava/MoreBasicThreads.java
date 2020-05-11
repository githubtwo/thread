package com.example.thread.thinkingjava;

/**
 * @program: thread
 * @description:
 * @author: huangyizeng
 * @create: 2020-05-05 18:52
 */
public class MoreBasicThreads {
    public static void main(String[] args) {
        for(int i = 0;i < 2; i++){
            new Thread(new LiftOff()).start();
        }
        System.out.println("waiting for LiftOff");
    }
}
