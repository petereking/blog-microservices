package com.chizi.blog.web.service.fallback;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.chizi.blog.common.Paging;
import com.chizi.blog.web.model.Comment;
import com.chizi.blog.web.service.CommentServiceClient;

import feign.hystrix.FallbackFactory;

/**
 * DATE: 2017/4/10 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@Component
public class CommentServiceFallbackFactory implements FallbackFactory<CommentServiceClient> {

    @Override
    public CommentServiceClient create(Throwable throwable) {

        return new CommentServiceClient() {
            @Override
            public Paging<Comment> paging(@RequestParam Map<String, Object> criteria) {
                throw new RuntimeException(throwable.getMessage());
            }

            @Override
            public String createComment(@RequestBody Comment comment) {
                throw new RuntimeException(throwable.getMessage());
            }

            @Override
            public Boolean deleteComment(@PathVariable("id") String id, @RequestParam("operatorId") String operatorId) {
                throw new RuntimeException(throwable.getMessage());
            }
        };
    }
}
