package com;

import com.google.common.eventbus.EventBus;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEurekaClient
public class PostConfig {
  @Bean
  public EventBus eventBus() {
    return new EventBus();
  }
}
