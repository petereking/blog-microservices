package com.chizi.blog.web.service.fallback;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.chizi.blog.common.Paging;
import com.chizi.blog.web.model.Post;
import com.chizi.blog.web.service.PostServiceClient;

import feign.hystrix.FallbackFactory;

/**
 * DATE: 2017/4/10 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@Component
public class PostServiceFallbackFactory implements FallbackFactory<PostServiceClient> {
    @Override
    public PostServiceClient create(Throwable throwable) {
        return new PostServiceClient() {
            @Override
            public String createPost(@RequestBody Post post) {
                throw new RuntimeException(throwable.getMessage());
            }

            @Override
            public Boolean deletePost(@PathVariable("id") String id, @RequestParam("operatorId") String operatorId) {
                throw new RuntimeException(throwable.getMessage());
            }

            @Override
            public Boolean updatePost(@PathVariable("id") String id, @RequestParam("title") String title,
                    @RequestParam("content") String content, @RequestParam("operatorId") String operatorId) {
                throw new RuntimeException(throwable.getMessage());
            }

            @Override
            public Post findPost(@PathVariable("id") String id,
                    @RequestParam(value = "operatorId", required = false) String operatorId) {
                throw new RuntimeException(throwable.getMessage());
            }

            @Override
            public Paging<Post> paging(@RequestParam Map<String, Object> criteria) {
                throw new RuntimeException(throwable.getMessage());
            }
        };
    }
}
