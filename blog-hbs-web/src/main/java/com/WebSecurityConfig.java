package com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.chizi.blog.web.component.BlogUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/signup").permitAll()
        .antMatchers("/static/**").permitAll().anyRequest().authenticated().and().formLogin()
        .loginPage("/login").defaultSuccessUrl("/posts", false).permitAll().and().logout()
        .logoutUrl("/logout").permitAll().and().csrf().disable();
  }

  @Bean
  public BlogUserDetailsService blogUserDetailsService() {
    return new BlogUserDetailsService();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  public static void main(String[] a) {
    System.err.println(new BCryptPasswordEncoder().encode("1"));
  }
}
