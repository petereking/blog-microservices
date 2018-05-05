package com.chizi.blog.user.service.impl;

import com.chizi.blog.user.common.UserServiceException;
import com.chizi.blog.user.model.User;
import com.chizi.blog.user.repo.UserRepo;
import com.chizi.blog.user.service.UserService;
import com.google.common.base.Throwables;

import de.bripkens.gravatar.Gravatar;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceJpaImpl implements UserService {
  private final PasswordEncoder passwordEncoder;
  private final UserRepo userRepo;
  private final Gravatar gravatar;
  private final DozerBeanMapper beanMapper;

  @Autowired
  public UserServiceJpaImpl(PasswordEncoder passwordEncoder, UserRepo userRepo, Gravatar gravatar,
      DozerBeanMapper beanMapper) {
    this.passwordEncoder = passwordEncoder;
    this.userRepo = userRepo;
    this.gravatar = gravatar;
    this.beanMapper = beanMapper;
  }

  @Override
  public Long createUser(User user) {
    try {
      User entity = beanMapper.map(user, User.class);
      entity.setPassword(passwordEncoder.encode(user.getPassword()));
      entity.setAvatar(gravatar.getUrl(user.getEmail()));
      userRepo.save(entity);
      return entity.getId();
    } catch (Exception e) {
      log.error("createUser fail, user={}, cause={}", user, Throwables.getStackTraceAsString(e));
      throw new UserServiceException("user.create.fail");
    }
  }

  @Override
  public User findUserByName(String name) {
    User entity = null;
    try {
      entity = userRepo.findByUsername(name);
    } catch (Exception e) {
      log.error("findUserByName fail, name={}, cause={}", name,
          Throwables.getStackTraceAsString(e));
      throw new UserServiceException("user.find.by.name.fail");
    }
    if (entity == null) {
      throw new UserServiceException("user.not.exist");
    }
    return entity;
  }

  @Override
  public User findUserById(Long id) {
    User entity = null;
    try {
      entity = userRepo.findOne(id);
    } catch (Exception e) {
      log.error("findUserById fail, id={}, cause={}", id, Throwables.getStackTraceAsString(e));
      throw new UserServiceException("user.find.by.id.fail");
    }
    if (entity == null) {
      throw new UserServiceException("user.not.exist");
    }
    return entity;
  }
}
