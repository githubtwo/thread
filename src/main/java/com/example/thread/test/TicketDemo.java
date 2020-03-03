package com.example.thread.test;

/**
 需求：简单的卖票程序。
 多个窗口同时买票。

 创建线程的第二种方式：实现Runnable接口
 步骤：
 1、定义类实现Runnable接口
 2、覆盖Runnable接口中的run方法
 3、通过Thread建立线程对象
 4、将Runnable的子类对象作为参数传入Thread的构造函数
 5、调用Thread.start（）方法开启线程并调用Runnable接口的run方法


 实现方式和继承方式有什么区别呢？

 实现方式好处：避免了单继承的局限性
 在定义线程时，建议使用实现方式

 继承Thread：线程代码存放在Thread子类的run方法中
 实现方式：线程代码存在的Runnable接口的子类的run方法中
 */

/**
 通过分析，发现，打印出了0，-1，-2等错票
 多线程的运行出现了安全问题。

 问题原因：
    当多条语句在执行同一个线程共享数据时，一个线程对多条语句只执行了一部分，还没有执行完，另一个线程参与进来执行，导致共享数据的错误。

 解决办法：
    对多条操作共享数据的语句，只能让一个线程都执行完，在执行过程中，其它线程不可以参与执行。

 Java对于多线程的安全问题提供了专业的解决问题：
 就是同步代码块

 synchronized(对象){
    需要被同步的代码
 }
对象如同锁，持有锁的线程可以在同步代码中执行
 没有持有锁的线程，即使获取cpu执行权，也进不去，因为没有获取锁

 同步的前提：
 1、必须要有两个或者两个以上的线程
 2、必须是多个线程使用同一个锁

 必须保证同步中只能有一个线程在运行

 好处：解决了多线程的安全问题
 弊端：多个线程需要判断锁，较为消耗资源
 */

class Ticket  implements Runnable{ //extends Thread{
    private int ticket = 1000;
    Object object = new Object();
    boolean flag = true;
    @Override
    public void run() {
        if(flag){
            while (true){
                synchronized(this){
                    if(ticket > 0){
                  /*  try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                        System.out.println(Thread.currentThread().getName() + "...sale : " + ticket--);
                    }
                }

            }
        }else{
            while (true)
                 this.show();
        }

    }

    public synchronized void show(){
        if(ticket > 0) {
            System.out.println(Thread.currentThread().getName() + "...show : " + ticket--);
        }
    }
}
public class TicketDemo {

    public static void main(String[] args) {

        Ticket t = new Ticket();

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

//        Thread t3 = new Thread(t);
//        Thread t4 = new Thread(t);
//        t3.start();
//        t4.start();

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
