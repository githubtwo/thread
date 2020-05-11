package com.example.thread.thinkingjava;

/**
 * @program: thread
 * @description: concurrent
 * @author: huangyizeng
 * @create: 2020-05-05 18:03
 */
public class LiftOff implements Runnable{
    protected int countDown = 10; // default
    private static int taskCount = 0;
    private final int id = taskCount++;
    public LiftOff(){}
    public LiftOff(int countDown){
        this.countDown = countDown;
    }
    public String status(){
        return "#" + id + "(" +
                (countDown > 0 ? countDown : "Liftoff!") + "), ";
    }
    @Override
    public void run() {
        while (countDown-- > 0){

            System.out.println(Thread.currentThread().toString() + "  " + status());
            Thread.yield();
        }
    }
}
