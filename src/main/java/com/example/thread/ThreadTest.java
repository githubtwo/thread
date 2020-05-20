package com.example.thread;

import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @program: thread
 * @description: 2个线程，轮流获取执行权
 * @author: huangyizeng
 * @create: 2020-05-05 17:50
 */
public class ThreadTest {

    private void changeStr(String input){
        input = "abc" + "htf";
        String b = "abchtf";
        System.out.println( b);
        System.out.println("change=" + input);
    }

    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();
        String testStr = "ab"+ "c";
        String a = "abc";
//        System.out.println(a == testStr);
        threadTest.changeStr(testStr);
        System.out.println(testStr);


        String str = "abcdefg";
        String reverse = "";

        for (int i = 0; i < str.length(); i++) {

            reverse = str.charAt(i) + reverse;

        }
        System.out.println(reverse);

        HashMap<String,String> map = new HashMap<>();
        map.put("as","1");
        map.put("3","3");
        map.put("8","8");
        map.put("5","5");
        map.put("2","2");
        System.out.println(map);
        Set<Map.Entry<String, String>> entry = map.entrySet();
        List<Map.Entry<String,String>> list = new ArrayList<>();
        list.addAll(entry);
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getKey().hashCode() - o2.getKey().hashCode();
            }
        });

//        ThreadTest threadTest = new ThreadTest();
//        threadTest.turnExecute();
    }

    public void turnExecute(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> list = Arrays.asList("A","B","C","D","E");
                for(String string : list){
                    synchronized (ThreadTest.class){
                        ThreadTest.class.notifyAll();
                        System.out.println(string);
                        try {
                            ThreadTest.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Integer> list = Arrays.asList(1,2,3,4,5,6);
                for(Integer integer : list){
                    synchronized (ThreadTest.class){
                        ThreadTest.class.notifyAll();
                        System.out.println(integer);
                        try {
                            ThreadTest.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        System.out.println("begin print:");
        t1.start();
        t2.start();
    }
}
