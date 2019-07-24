package com.example.thread.test;

/**
 * 死锁Demo
 */
class Test1 implements Runnable{
    private boolean flag;
    Test1(boolean flag){
        this.flag = flag;
    }

    @Override
    public void run() {
        if(flag){
            synchronized (MyLock.locka){
                System.out.println("if locka");
                synchronized (MyLock.lockb){
                    System.out.println("if lockb");
                }
            }
        }else{
            synchronized (MyLock.lockb){
                System.out.println("else lockb");
                synchronized (MyLock.locka){
                    System.out.println("else locka");
                }
            }
        }
    }
}

class MyLock{
    static Object locka = new Object();
    static Object lockb = new Object();
}

public class DeadLockDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Test1(true));
        Thread t2 = new Thread(new Test1(false));
//        t1.setDaemon(true);
//        t2.setDaemon(true);
        t1.start();
        t2.start();
    }
}
