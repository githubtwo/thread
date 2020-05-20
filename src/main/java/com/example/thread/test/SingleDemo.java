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
//懒汉式： 特点：实例的延迟加载，
//    问题：多线程问题
//    解决方式：同步，会产生效率问题，用双重判断提升效率
    // 锁：字节码文件
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
        SingleDemo singleDemo = new SingleDemo();
        System.out.println(singleDemo.isOdd(2));
    }
    public boolean isOdd(int i) {
//        return i % 2 != 1;
        return (i & 1) == 1;
    }
}
