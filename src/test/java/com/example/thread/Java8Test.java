package com.example.thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @program: thread
 * @description: 1
 * @author: liu yan
 * @create: 2020-04-02 22:28
 */
public class Java8Test {
    @Test
    public void testLambda(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("before java8,too much code for too little to do");
            }
        }).start();

        new Thread(() -> System.out.println("In java8,lambda expression rocks !!")).start();

        // 使用 lambda 表达式对列表进行迭代
        // Java 8 之前：
        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        for (String feature : features) {
            System.out.println(feature);
        }
        // Java 8 之后：
        List features1 = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features1.forEach(n -> System.out.println(n));
        // 使用 Java 8 的方法引用更方便，方法引用由::双冒号操作符标示，
        features1.forEach(System.out::println);
    }

    @Test
    public void testPredicate(){
        int[] numbers= {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        List<Integer> list=new ArrayList<>();
        for(int i:numbers) {
            list.add(i);
        }
        Predicate<Integer> p1 = i -> i > 5;
        Predicate<Integer> p2 = i -> i < 20;
        Predicate<Integer> p3 = i -> i % 2 == 0;
        List test = list.stream().filter(p1.or(p3)).collect(Collectors.toList());
        System.out.println(test.toString());
    }


}
