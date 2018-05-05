package com;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class BlogHbsWebConfig extends WebMvcConfigurerAdapter {
  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    super.addResourceHandlers(registry);
    registry.addResourceHandler("/static/css/**").addResourceLocations("classpath:/static/css/");
    registry.addResourceHandler("/static/js/**").addResourceLocations("classpath:/static/js/");
  }
}
