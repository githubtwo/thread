package com.example.thread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThreadApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreadApplication.class, args);
    }

    public void out(){
        System.out.println("test merger one 2");
        System.out.println("test merger two 1");
        System.out.println("test merger one");
    }
}
