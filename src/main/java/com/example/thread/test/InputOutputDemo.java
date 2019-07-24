package com.example.thread.test;

/**
 线程间通讯：
 其实就是多个线程在操作同一资源
 但是操作的动作不同
 */

class Res{
    private String name;
    private String sex;
    private boolean flag = false;

    public synchronized void set(String name,String sex){
        if(flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.name = name;
        this.sex = sex;
        this.flag = true;
        this.notify();
    }
    public synchronized void out(){
        if(!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(this.name + "...." + this.sex);
        this.flag = false;
        this.notify();
    }
}

class Input implements Runnable{
    private Res r;
    Input(Res r){
        this.r = r;
    }
    @Override
    public void run() {
        int x = 0;
        while(true){
            synchronized (r){
                if(x == 0){
                    r.set("mike","nan");
                }else{
                    r.set("丽丽","女女");
                }
                x = (x + 1) % 2;
            }
        }
    }
}
class Output implements Runnable{

    private Res r;
    Output(Res r){
        this.r = r;
    }
    @Override
    public void run() {
        while(true){
            synchronized (r){
                r.out();
            }

        }
    }
}

public class InputOutputDemo {

    public static void main(String[] args) {
        Res r = new Res();

        new Thread(new Input(r)).start();
        new Thread(new Output(r)).start();
//        Input input = new Input(r);
//        Output output = new Output(r);
//        Thread t1 = new Thread(input);
//        Thread t2 = new Thread(output);
//
//        t1.start();
//        t2.start();
    }
}

/**
 wait();
 notify();
 notifyAll();
 都用在同步中，因为要对持有监视器（锁）的线程操作
 所以要使用在同步中，因为只有同步才具有锁

 为什么这些操作方法要定义在Object中  ？
 因为这些方法在操作同步中线程时，都必须要标识它们所操作只有的锁

 也就是说：等待和唤醒必须是同一个锁
 而锁可以是任意对象，所以可以被任意对象调用的方法定义在Object中
 */
