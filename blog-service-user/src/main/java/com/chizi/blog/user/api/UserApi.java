package com.chizi.blog.user.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chizi.blog.user.model.User;
import com.chizi.blog.user.service.UserService;

@RestController
@RequestMapping("/v1/blog/users")
public class UserApi {
  private final UserService userService;

  @Autowired
  public UserApi(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("")
  public Long createUser(@RequestBody User user) {
    return userService.createUser(user);
  }

  @GetMapping("/{id}")
  public User findUserById(@PathVariable Long id) {
    return userService.findUserById(id);
  }

  @GetMapping("/name/{name}")
  public User findUserByName(@PathVariable String name) {
    return userService.findUserByName(name);
  }
}
