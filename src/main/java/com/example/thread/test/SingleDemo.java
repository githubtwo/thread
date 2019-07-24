package com.example.thread.test;

/**
 单类设计模式

 饿汉式：
 class Single{
    private static final Single s = new Single();
    private Single(){}
    public static Single getInstance(){
        return s;
    }
 }

 */
//懒汉式：
class Single{
    private static Single s = null;
    private Single(){}
    public static  Single getInstance(){
        if(s == null){
            synchronized(Single.class){
                if(s == null){
                    s = new Single();
                }
            }
        }
        return s;
    }
}
public class SingleDemo {
    private boolean flag;
    public static void main(String[] args) {
        SingleDemo s = new SingleDemo();
        System.out.println(s.flag);
    }
}
