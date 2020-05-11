package com.example.thread;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * @program: thread
 * @description: 用户
 * @author: liu yan
 * @create: 2020-03-06 11:39
 */
@Getter
@Setter
@AllArgsConstructor
public class User  {
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
