package com;

import de.bripkens.gravatar.Gravatar;
import org.dozer.DozerBeanMapper;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableEurekaClient
public class UserConfiguration {
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public Gravatar gravatar() {
    return new Gravatar();
  }

  @Bean
  public DozerBeanMapper beanMapper() {
    return new DozerBeanMapper();
  }
}
