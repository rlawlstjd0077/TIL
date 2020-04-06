package com.jinseong.soft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements ApplicationRunner {
    @Autowired
    private MyProperties properties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(String.format("My name: %s, My age: %s, My size: %s", properties.getName(), properties.getAge(), properties.getSize()));
    }
}
