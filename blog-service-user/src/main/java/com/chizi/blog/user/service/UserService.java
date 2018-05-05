package com.chizi.blog.user.service;

import com.chizi.blog.user.model.User;

public interface UserService {
  Long createUser(User user);

  User findUserByName(String name);

  User findUserById(Long id);
}
