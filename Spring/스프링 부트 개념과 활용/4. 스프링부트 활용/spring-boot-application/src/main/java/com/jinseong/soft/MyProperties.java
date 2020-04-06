package com.jinseong.soft;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("kim")
public class MyProperties {
    private String name;
    private int age;
    private int size;

    public MyProperties() {
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getSize() {
        return size;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
