package com.example.thread.test;


class Demo extends Thread{
    @Override
    public void run() {
        for(int i = 0; i < 60; i++){
            System.out.println("demo run " + i);
        }
    }
}

public class ThreadDemo {
    public static void main(String[] args) {

        Demo demo = new Demo();
        demo.start();

        for(int i = 0; i < 60; i++){
            System.out.println("Hello world " + i);
        }
    }
}
