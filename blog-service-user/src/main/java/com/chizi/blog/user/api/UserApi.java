package com.chizi.blog.user.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chizi.blog.user.model.User;
import com.chizi.blog.user.service.UserService;

/**
 * DATE: 2017/4/10 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@RestController
@RequestMapping("/v1/blog/users")
public class UserApi {

    private final UserService userService;

    @Autowired
    public UserApi(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Long createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User findUserById(@PathVariable Long id){
        return userService.findUserById(id);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public User findUserByName(@PathVariable String name){
        return userService.findUserByName(name);
    }
}
