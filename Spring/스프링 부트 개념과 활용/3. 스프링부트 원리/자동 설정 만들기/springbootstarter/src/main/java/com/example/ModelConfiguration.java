package com.example;

import javax.jws.WebParam.Mode;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 자동설정 해주는 설정 파일.
 * 원래는 뭔가를 설정하는 대상이 되는 클래스는 다른 프로젝트인 경우가 많음
 * (Model, ModelConfiguration이 보통은 각각 다른 프로젝트에 있을 것임)
 */
@Configuration
@EnableConfigurationProperties(ModelProperties.class)
public class ModelConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public Model model(ModelProperties properties) {
    Model model = new Model(properties.getName(), properties.getAge());
    return model;
  }
}
