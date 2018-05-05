package com.chizi.blog.user.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chizi.blog.user.model.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
  User findByUsername(String username);
}
