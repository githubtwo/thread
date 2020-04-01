package com.example.thread;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

class ListNode{
    User val;
    ListNode next;
    ListNode(User val){
        this.val = val;
    }
}

/**
 * @program: thread
 * @description: 队列、栈用法
 * @author: liu yan
 * @create: 2020-03-07 17:05
 */
public class StackAndQueue {
    public static void main(String[] args) {
        User user = new User("11",1);
        ListNode listNode = new ListNode(user);
        System.out.println(listNode.val);

//        testStack();

//        testQueue();

//        queueSimulateStack();

        testJDKProxy();


    }

    /**
     * @describe  Stack用法
     * @author: huangyizeng
     * @create: 2020/3/7 17:08
     * @param
     * @return: void
     */
    public static void testStack(){
        Stack<Character> stack = new Stack<>();
        // 元素入栈
        stack.push('a');
        stack.push('b');
        stack.push('c');
        stack.push('d');

        try {
            // 栈空时会抛出 EmptyStackException 异常进入catch块，从而结束打印
            while(stack.peek() != null) {
                // 出栈栈顶元素，并打印出栈元素
//                System.out.println(stack.peek());
                System.out.println(stack.pop());
            }
        } catch(Exception e) {
            System.out.println("栈空");
        }
    }

    public static void testQueue(){
        // LinkedList是Queue的子类，一般采用其进行队列操作
        Queue<Character> queue = new LinkedList<>();
        queue.offer('a');
        queue.offer('b');
        queue.offer('c');
        queue.offer('d');

        // LinkedList的peek()方法在空队列时会返回一个null
        while(queue.peek() != null) {
            // 出队列队首元素并打印
            System.out.println(queue.poll());
        }
    }
/**
 * @describe 两个队列模拟一个堆栈，队列是先进先出，而堆栈是先进后出。模拟如下
 * 队列 a 和 b
 * （1）入栈：a 队列为空，b 为空。例：则将”a,b,c,d,e”需要入栈的元素先放 a 中，a 进栈为”a,b,c,d,e”
 * （2）出栈：a 队列目前的元素为”a,b,c,,d,e”。将 a 队列依次加入 Arraylist 集合 a 中。以倒序的方法，将 a 中的集
 * 合取出，放入 b 队列中，再将 b 队列出列
 * @author: huangyizeng
 * @create: 2020/3/8 10:25
 * @param
 * @return: void
 */
    public static void queueSimulateStack(){
        Queue<String> queue = new LinkedList<>();
        Queue<String> queue1 = new LinkedList<>();
        List<String> list = new ArrayList<>();

        queue.add("a");
        queue.add("b");
        queue.add("c");
        while(queue.peek() != null){
            String tmp = queue.poll();
            list.add(tmp);
            System.out.println(tmp);
        }
        System.out.println("进栈");

//        for(String s : queue){
//            list.add(s);
//        }
//        System.out.println("tmp转存");
        for(int i = list.size() - 1; i >= 0;i--){
            queue1.add(list.get(i));
        }
        while(queue1.peek() != null){
            System.out.println(queue1.poll());
        }
        System.out.println("出栈");

    }

    public static void testJDKProxy(){
        List<String> list = new ArrayList<>();
        List<String> proxy = (List<String>) Proxy.newProxyInstance(list.getClass().getClassLoader(), list.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("I'm the proxy of ArrayList");
                        return method.invoke(list,args);
                    }
                });
//        proxy.add("abc");
        System.out.println(proxy);
    }
}
