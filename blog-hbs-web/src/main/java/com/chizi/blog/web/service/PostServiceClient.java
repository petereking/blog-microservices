package com.chizi.blog.web.service;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chizi.blog.common.Paging;
import com.chizi.blog.web.model.Post;
import com.chizi.blog.web.service.fallback.PostServiceFallbackFactory;

@FeignClient(value = "blog-post", fallbackFactory = PostServiceFallbackFactory.class)
@RequestMapping("/v1/blog/posts")
public interface PostServiceClient {
  @PostMapping("")
  String createPost(@RequestBody Post post);

  @DeleteMapping("/{id}")
  Boolean deletePost(@PathVariable("id") String id, @RequestParam("operatorId") String operatorId);

  @PutMapping("/{id}")
  Boolean updatePost(@PathVariable("id") String id, @RequestParam("title") String title,
      @RequestParam("content") String content, @RequestParam("operatorId") String operatorId);

  @GetMapping("/{id}")
  Post findPost(@PathVariable("id") String id,
      @RequestParam(value = "operatorId", required = false) String operatorId);

  @GetMapping("")
  Paging<Post> paging(@RequestParam Map<String, Object> criteria);
}
