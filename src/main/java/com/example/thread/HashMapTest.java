package com.example.thread;

import org.springframework.core.convert.converter.Converter;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class HashMapTest {

    public Object instance = null;
    private static final int _1MB = 1024 * 1024;
    private byte[] bigSize = new byte[2 * _1MB];

    public static void main(String[] args) throws IOException, ClassNotFoundException, CloneNotSupportedException {

        User a = new User("zhangsan",18);
        User b = new User("zhangsan",19);
        int a1 = a.hashCode() ;//^ (a.hashCode() >>> 16);
        int b1 = a.hashCode() >>> 16 ;//^ (b.hashCode() >>> 16);
        System.out.println(a1);
        System.out.println(b1);
        System.out.println(a1 ^ b1);
        HashMap<User, String> map = new HashMap<User, String>();
        map.put(new User("zick",17),"123");
        map.put(new User("zhangsan",18),"123");
        map.put(new User("zhangsan",19),"123");

        Set<Map.Entry<User, String>> entrySet = map.entrySet();
        for(Map.Entry<User,String> m : entrySet){
            System.out.println(m.getKey());
        }
//        for (int i = 0; i < 100; i++) {
//
//            map.put("" + i, "keySet");
//            map.put("" + i, "keySet1");
//        }
//
//        // for each map.entrySet()
//        long time1 = System.currentTimeMillis();
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            entry.getKey();
//            entry.getValue();
//        }
//        System.out.println("time1= " + (System.currentTimeMillis() - time1));
//
//        // 显示调用map.entrySet()的集合迭代器
//        long time2 = System.currentTimeMillis();
//        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<String, String> entry = iterator.next();
//            entry.getKey();
//            entry.getValue();
//        }
//        System.out.println("time2= " + (System.currentTimeMillis() - time2));
//
//        // for each map.keySet()，再调用get获取
//        long time3 = System.currentTimeMillis();
//        for (String key : map.keySet()) {
//            map.get(key);
//        }
//        System.out.println("time3= " + (System.currentTimeMillis() - time3));
//
//        // for each map.entrySet()，用临时变量保存map.entrySet()
//        long time4 = System.currentTimeMillis();
//        Set<Map.Entry<String, String>> entrySet = map.entrySet();
//        for (Map.Entry<String, String> entry : entrySet) {
//            entry.getKey();
//            entry.getValue();
//        }
//        System.out.println("time4= " + (System.currentTimeMillis() - time4));



//        String aa = " ? One ? ";
//        aa = aa.toUpperCase();
//        aa = aa.trim();
//        System.out.println(aa);
//        String aStr = "? one ?";
//        String bStr = aStr;
//        aStr.toUpperCase();
//        aStr.trim();
//        System.out.println("[" + aStr + "," + bStr + "]");
//
//        Converter<String, Integer> converter = Integer::valueOf;
//        Integer converted = converter.convert("123");
//        System.out.println(converted);   // 123
//
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("D://obj")));
//        objectOutputStream.writeObject(new User("zhansan",14));
//        objectOutputStream.close();
//
//        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File("D://obj")));
//        User user = (User) objectInputStream.readObject();
////        System.out.println(user);
//        objectInputStream.close();
//
//        User user1 = (User) user.clone();
//        user.setAge(18);
////        System.out.println(user);
////        System.out.println(user1);
////        System.out.println(user == user1);
//
//        HashMap<Integer,User> hashMap = new HashMap<>();
//        hashMap.put(2,new User("lisi",19));
//        hashMap.put(3,new User("wangwu",18));
//        hashMap.put(4,new User("liuer",15));
//        hashMap.put(5,new User("huangyi",30));
//        Iterator<Map.Entry<Integer, User>> it = hashMap.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry<Integer, User> entry = it.next();
//            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
//        }
////        Arrays.asList();
////        LinkedList;
////        LinkedHashMap;
////        CollectionUtils;
////        Collections;
//        User a = null;
//        int[] as = {1,2,3,4};
//        List<Integer> list1 = Arrays.stream(as).boxed().collect(Collectors.toList());
//        System.out.println(list1);
////        ArrayList<Integer> integerList = Arrays.asList(as);
//        a = Optional.ofNullable(a).orElseGet(User::new);
//        System.out.println(a);
//        // map 的键值对集合
//        Set<Map.Entry<Integer,User>> set = hashMap.entrySet();
//        for(Map.Entry<Integer,User> m : set){
//            System.out.println(m);
//        }
//        // key值列表
//        for(Integer key : hashMap.keySet()){
//            System.out.println(key);
//        }
//        // values 列表
//        for(User key : hashMap.values()){
//            System.out.println(key);
//        }
//        HashMap<Integer,User> map = HashMapTest.sort(hashMap);
//        System.out.println(map);

    }

    /**
     * @describe 已知一个 HashMap<Integer，User>集合， User 有 name（String）和 age（int）属性。请写一个方法实现对
     * HashMap 的排序功能，该方法接收 HashMap<Integer，User>为形参，返回类型为 HashMap<Integer，User>，
     * 要求对 HashMap 中的 User 的 age 倒序进行排序。排序时 key=value 键值对不得拆散
     * @author: huangyizeng
     * @create: 2020/3/6 14:09
     * @param map
     * @return: java.util.HashMap<java.lang.Integer,com.example.thread.User>
     */
    public static HashMap<Integer,User> sort(HashMap<Integer,User> map){
        // 首先拿到 map 的键值对集合
        Set<Map.Entry<Integer, User>> entrySet = map.entrySet();
//        System.out.println(entrySet);
        // 将 set 集合转为 List 集合，为什么，为了使用工具类的排序方法
        List<Map.Entry<Integer, User>> list2 = new ArrayList<Map.Entry<Integer, User>>(entrySet);
//        List<Map.Entry<Integer, User>> list = list2.stream().sorted(new Comparator<Map.Entry<Integer, User>>() {
//            @Override
//            public int compare(Map.Entry<Integer, User> o1, Map.Entry<Integer, User> o2) {
//                return o1.getValue().getAge() - o2.getValue().getAge();
//            }
//        }).collect(Collectors.toList());

        List<Map.Entry<Integer,User>> list = list2.stream().sorted((o1,o2) -> o1.getValue().getAge() - o2.getValue().getAge()).collect(Collectors.toList());
//        Collections.sort(list, new Comparator<Map.Entry<Integer, User>>() {
//            @Override
//            public int compare(Map.Entry<Integer, User> o1, Map.Entry<Integer, User> o2) {
//                return o1.getValue().getAge() - o2.getValue().getAge();
//            }
//        });
        LinkedHashMap<Integer,User> linkedHashMap = new LinkedHashMap<Integer,User>();
        for(Map.Entry<Integer,User> entry : list){
            linkedHashMap.put(entry.getKey(),entry.getValue());
        }
        return linkedHashMap;
    }
}
