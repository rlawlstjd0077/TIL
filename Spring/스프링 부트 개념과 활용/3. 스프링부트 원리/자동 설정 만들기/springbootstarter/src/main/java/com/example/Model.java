package com.example;

public class Model {
  private String name;
  private int age;

  public Model(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  @Override
  public String toString() {
    return "Model{" +
        "name='" + name + '\'' +
        ", age=" + age +
        '}';
  }
}
