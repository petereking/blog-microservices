package com.chizi.blog.web.controller;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chizi.blog.web.model.User;
import com.chizi.blog.web.service.UserServiceClient;
import com.google.common.base.Throwables;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class Users {
  private final UserServiceClient userService;

  @Autowired
  public Users(UserServiceClient userService) {
    this.userService = userService;
  }

  @GetMapping("/signup")
  public String signupForm(Model model) throws IOException, ParseException {
    return "user/signup";
  }

  @PostMapping("/signup")
  public String signup(User user, BindingResult result, RedirectAttributes attributes) {
    if (result.hasErrors()) {
      return "user/signup";
    }
    try {
      userService.createUser(user);
      attributes.addFlashAttribute("success", "注册成功");
      return "redirect:/login";
    } catch (Exception e) {
      log.error("signup fail, cause={}", Throwables.getStackTraceAsString(e));
      attributes.addFlashAttribute("error", "signup.fail");
      return "user/signup";
    }
  }

  @GetMapping("/login")
  public String loginForm() {
    return "user/login";
  }
}
