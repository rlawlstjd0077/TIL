package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication application = new SpringApplication(Application.class);
    application.setWebApplicationType(WebApplicationType.NONE);
    application.run(args);
  }

  /**
   * 이렇게 Bean으로 Model을 만들어도 적용되지 않는다.
   * 왜냐하면 ComponenetScan -> EnableAutoConfiguration 순으로 진행되므로 덮어쓰워지게 됨
   * 2부 때문에 주석처리.
   */
  /*@Bean
  public Model model() {
    Model model = new Model("lee", 30);
    return model;
  }*/
}
