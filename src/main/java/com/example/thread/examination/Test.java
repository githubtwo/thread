package com.example.thread.examination;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: thread
 * @description:
 * @author: huangyizeng
 * @create: 2020-05-21 00:18
 */
public class Test {

    public static void main(String[] args) {

    }

    // 计算一个字符串中每个字符出现的次数
    public Map countString(){
        String str = "aabbc";
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        char[] arr = str.toCharArray();

        for (char ch : arr) {
            if (map.containsKey(ch)) {
                Integer old = map.get(ch);
                map.put(ch, old + 1);
            } else {
                map.put(ch,1);
            }
        }
        System.out.println(map);
        return map;
    }

}
