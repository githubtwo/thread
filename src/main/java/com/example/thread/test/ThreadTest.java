package com.example.thread.test;

/**
 * 新建2个线程，跟主线程交替运行
 *
 * 线程有自己的名称
 * Thread-0 从0开始
 *
 * currentThread(): 获取当前线程对象
 * getName(): 获取线程名称
 * 设置线程名称： setName 或者构造函数
 */
class Test extends Thread{

//    private String name;
    Test(String name){
//        this.name = name;
        super(name);
    }
    @Override
    public void run() {
        for(int i = 0; i < 60; i++){
            System.out.println( (Thread.currentThread() == this) + " " + this.getName()  + "  run ..." + i);
        }
    }
}

public class ThreadTest {
    public static void main(String[] args) {
        Test t1 = new Test("one");
        Test t2 = new Test("two");
        t1.start();
        t2.start();

        for(int i = 0; i < 60; i++){
            System.out.println("main run ... " + i);
        }
    }


}
