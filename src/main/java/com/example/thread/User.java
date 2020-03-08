package com.example.thread;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: thread
 * @description: 用户
 * @author: liu yan
 * @create: 2020-03-06 11:39
 */
@Data
@AllArgsConstructor
public class User implements Serializable,Cloneable {
    private String name;
    private Integer age;

    public User(){}

    @Override
    protected Object clone() throws CloneNotSupportedException {
        User user = null;
        try{
            user = (User)super.clone();
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return user;
    }
}
