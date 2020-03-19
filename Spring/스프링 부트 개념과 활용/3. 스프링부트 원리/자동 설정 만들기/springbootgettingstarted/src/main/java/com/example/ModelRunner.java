package com.example;

import javax.jws.WebParam.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * ApplicationRunner: 스프링애플리케이션이 만들어지고 띄웠을 때 자동으로 실행되는 Bean을 만들고자 할때 사용함
 */

@Component
public class ModelRunner implements ApplicationRunner {
  @Autowired
  Model model;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    System.out.println(model);
  }
}
