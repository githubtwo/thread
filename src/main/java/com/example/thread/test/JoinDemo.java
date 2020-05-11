package com.example.thread.test;

class Demo2 implements Runnable{

    @Override
    public void run() {
        for(int x = 0; x < 70; x++){
            System.out.println(Thread.currentThread().getName() + "..." + x);
            Thread.yield();
        }
    }
}

public class JoinDemo {
    public static void main(String[] args) throws InterruptedException {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Before Java8, too much code for too little to do");
//            }
//        }).start();
//        new Thread( () -> System.out.println("In Java8, Lambda expression rocks !!") ).start();
        Demo2 d = new Demo2();
        Thread t1 = new Thread(d);
        Thread t2 = new Thread(d);
        t1.start();
//        t1.join();
        t2.start();


        for(int x = 0; x < 80; x++){
//            System.out.println("main" + "..." + x);
        }
        System.out.println("over");
    }
}
