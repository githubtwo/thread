package com.example.thread;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
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
        LinkedHashMap<String,String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("a","123");
        linkedHashMap.put("c","aaa");
        linkedHashMap.put("b","aaa");
        linkedHashMap.get("a");
        System.out.println(linkedHashMap);

        TreeMap<String,String> treeMap = new TreeMap<>();
        treeMap.put("a","123");
        treeMap.put("c","aaa");
        treeMap.put("b","aaa");
        System.out.println(treeMap);

        //将一整个语句直接定义了字符串
//        String str = "(\"Hello \" + \"World!!!\").substring(6, 9)";
//        //1定义一个专属的表达式解析工具
//        ExpressionParser parser = new SpelExpressionParser() ;
//        //2定义一个表达式处理类
//        Expression exp = parser.parseExpression(str);
//        //3进行最终的表达式计算
//        EvaluationContext context = new StandardEvaluationContext() ;
//        //4通过表达式进行结果计算
//        System.out.println(exp.getValue());
//
//        System.out.println(str);

        User user = new User();
        user.setAge(20);
        user.setName("zhangwei");
        SpelExpressionParser parser = new SpelExpressionParser();
        ParserContext context = new TemplateParserContext();//sql注入问题
        Expression exp = parser.parseExpression("select * from user u where name = '#{name}' and age >= #{age};",context );
        System.out.println(exp.getValue(user));
//结果：select * from user u where name = ‘zhangwei’ and age >= 20;


        ThreadTest threadTest = new ThreadTest();
        String testStr = "ab"+ "c";
        String a = "abc";
//        System.out.println(a == testStr);
        threadTest.changeStr(testStr);
        System.out.println(testStr);


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
