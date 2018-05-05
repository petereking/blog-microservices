package com.chizi.blog.web.controller;

import com.chizi.blog.web.model.User;
import com.chizi.blog.web.service.UserServiceClient;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
public abstract class ViewBase {
  private UserServiceClient userService;

  @Autowired
  protected void setUserService(UserServiceClient userService) {
    this.userService = userService;
  }

  @ModelAttribute("currentUser")
  public User getCurrentUser() {
    Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (object instanceof UserDetails) {
      UserDetails userDetails = (UserDetails) object;
      try {
        return userService.findUserByName(userDetails.getUsername());
      } catch (Exception e) {
        log.warn("findUserByName fail, cause={}", Throwables.getStackTraceAsString(e));
        return null;
      }
    } else {
      return null;
    }
  }

  public String getCurrentUserId() {
    User user = getCurrentUser();
    if (user == null) {
      return null;
    } else {
      return user.getId().toString();
    }
  }
}
