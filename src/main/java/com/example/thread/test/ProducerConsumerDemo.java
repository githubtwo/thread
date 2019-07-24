package com.example.thread.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Resource{
    private String name;
    private int count = 1;
    private boolean flag = false;

    private Lock lock = new ReentrantLock();
    private Condition condition_pro = lock.newCondition();
    private Condition condition_con = lock.newCondition();

    public void set(String name){ // t1 t2
        lock.lock();
        try {
            while(flag) {
                try {
                    condition_pro.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.name = name + "--" + count++;
            System.out.println(Thread.currentThread().getName() + "...生产者..." + this.name);
            this.flag = true;
            condition_con.signalAll();
        }finally {
            lock.unlock();
        }

    }
    public void out(){ //t3 t4
        lock.lock();
        try {
            while(!flag) {
                try {
                    condition_con.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "...消费者......" + this.name);
            this.flag = false;
            condition_pro.signalAll();
        }finally {
            lock.unlock();
        }

    }
}

class Producer implements Runnable{
    private Resource resource;

    public Producer(Resource resource){
        this.resource = resource;
    }

    @Override
    public void run() {
        while(true){
            resource.set("..商品++");
        }
    }
}

class Consumer implements Runnable{

    private Resource resource;
    public Consumer(Resource resource){
        this.resource = resource;
    }
    @Override
    public void run() {
        while(true){
            resource.out();
        }
    }
}
public class ProducerConsumerDemo {

    public static void main(String[] args) {
        Resource resource = new Resource();

        Producer producer = new Producer(resource);
        Consumer consumer = new Consumer(resource);

        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(producer);
        Thread t3 = new Thread(consumer);
        Thread t4 = new Thread(consumer);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

//package com.example.thread.test;
//
//class Resource{
//    private String name;
//    private int count = 1;
//    private boolean flag = false;
//
//    public synchronized void set(String name){ // t1 t2
//        while(flag) {
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        this.name = name + "--" + count++;
//        System.out.println(Thread.currentThread().getName() + "...生产者..." + this.name);
//        this.flag = true;
//        this.notifyAll();
//    }
//    public synchronized void out(){ //t3 t4
//        while(!flag) {
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println(Thread.currentThread().getName() + "...消费者......" + this.name);
//        this.flag = false;
//        this.notifyAll();
//    }
//}
//
//class Producer implements Runnable{
//    private Resource resource;
//
//    public Producer(Resource resource){
//        this.resource = resource;
//    }
//
//    @Override
//    public void run() {
//        while(true){
//            resource.set("..商品++");
//        }
//    }
//}
//
//class Consumer implements Runnable{
//
//    private Resource resource;
//    public Consumer(Resource resource){
//        this.resource = resource;
//    }
//    @Override
//    public void run() {
//        while(true){
//            resource.out();
//        }
//    }
//}
//public class ProducerConsumerDemo {
//
//    public static void main(String[] args) {
//        Resource resource = new Resource();
//
//        Producer producer = new Producer(resource);
//        Consumer consumer = new Consumer(resource);
//
//        Thread t1 = new Thread(producer);
//        Thread t2 = new Thread(producer);
//        Thread t3 = new Thread(consumer);
//        Thread t4 = new Thread(consumer);
//
//        t1.start();
//        t2.start();
//        t3.start();
//        t4.start();
//    }
//}
