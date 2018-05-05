package com.chizi.blog.web.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chizi.blog.web.model.User;
import com.chizi.blog.web.service.fallback.UserServiceFallbackFactory;

@FeignClient(value = "blog-user", fallbackFactory = UserServiceFallbackFactory.class)
@RequestMapping("/v1/blog/users")
public interface UserServiceClient {
  @PostMapping("")
  Long createUser(@RequestBody User user);

  @GetMapping("/{id}")
  User findUserById(@PathVariable("id") Long id);

  @GetMapping("/name/{name}")
  User findUserByName(@PathVariable("name") String name);
}
