package com.chizi.blog.web.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.chizi.blog.common.Paging;
import com.chizi.blog.web.model.Comment;
import com.chizi.blog.web.service.fallback.CommentServiceFallbackFactory;

import java.util.Map;

@FeignClient(value = "blog-comment", fallbackFactory = CommentServiceFallbackFactory.class)
@RequestMapping("/v1/blog/comments")
public interface CommentServiceClient {
  @GetMapping("")
  Paging<Comment> paging(@RequestParam Map<String, Object> criteria);

  @PostMapping("")
  String createComment(@RequestBody Comment comment);

  @DeleteMapping("/{id}")
  Boolean deleteComment(@PathVariable("id") String id,
      @RequestParam("operatorId") String operatorId);
}
