package com.example.thread.test;

/**
 同步函数调用的时哪个锁？
    this


 静态进内存时，内存中没有本类对象，但一定有该类的对应的字节码对象
 类型.class

 静态的同步方法使用的锁是：
    该方法所在类的字节码文件对象
 */

class Ticket1  implements Runnable{ //extends Thread{
    private int ticket = 100;
    Object object = new Object();
    boolean flag = true;
    @Override
    public void run() {
        if(flag){
            while (true){
                synchronized (this){
                    if(ticket > 0){
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + "...sale : " + ticket--);
                    }
                }
            }
        }else{
            this.show();
        }

    }

    public synchronized void show(){
        if(ticket > 0){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "...sale : " + ticket--);
        }
    }
}

public class ThisLockDemo {

    public static void main(String[] args) {

        Ticket1 t = new Ticket1();

        Thread t1 = new Thread(t); // 创建一个线程，同时指定运行对象
        Thread t2 = new Thread(t);
        t1.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.flag = false;
        t2.start();

//        Ticket t1 = new Ticket();
//        Ticket t2 = new Ticket();
//        Ticket t3 = new Ticket();
//        Ticket t4 = new Ticket();
//
//        t1.start();
//        t1.start();
//        t1.start();
//        t1.start();

    }
}
