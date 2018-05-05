package com.chizi.blog.web.service.fallback;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.chizi.blog.web.model.User;
import com.chizi.blog.web.service.UserServiceClient;

import feign.hystrix.FallbackFactory;

/**
 * DATE: 2017/4/10 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@Component
public class UserServiceFallbackFactory implements FallbackFactory<UserServiceClient> {

    @Override
    public UserServiceClient create(Throwable throwable) {
        return new UserServiceClient() {

            @Override
            public Long createUser(@RequestBody User user) {
                throw new RuntimeException(throwable.getMessage());
            }

            @Override
            public User findUserById(@PathVariable("id") Long id) {
                throw new RuntimeException(throwable.getMessage());
            }

            @Override
            public User findUserByName(@PathVariable("name") String name) {
                throw new RuntimeException(throwable.getMessage());
            }
        };
    }
}
