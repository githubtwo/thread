package com.example.thread.test;

/**
 需求：
 银行有一个金库
 有两个储户分别存300元，每次存100，存3次

 目的：该程序是否有安全问题，如果有，该如何解决

 如何找问题：
 1、明确哪些代码是多线程运行代码
 2、明确共享数据
 3、明确多线程运行代码中哪些语句是操作共享数据的
 */
class Bank{
    private int sum;
    Object obj = new Object();
    public synchronized void add(int n){
//        synchronized (obj){
            sum = sum + n;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("sum= " + sum);
//        }

    }
}

class Cus implements Runnable{

    private Bank b = new Bank();

    @Override
    public void run() {
        for(int i = 0; i < 3; i++){
            b.add(100);
        }
    }
}
public class BankDemo {

    public static void main(String[] args) {
        Cus c = new Cus();
        Thread t1 = new Thread(c);
        Thread t2 = new Thread(c);
        t1.start();
        t2.start();
    }
}
