package com.chizi.blog.post.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chizi.blog.common.Paging;
import com.chizi.blog.post.dto.PostPagingCriteria;
import com.chizi.blog.post.model.Post;
import com.chizi.blog.post.service.PostService;

@RestController
@RequestMapping("/v1/blog/posts")
public class PostApi {
  private final PostService postService;

  @Autowired
  public PostApi(PostService postService) {
    this.postService = postService;
  }

  @PostMapping("")
  public String createPost(@RequestBody Post post) {
    return postService.createPost(post);
  }

  @DeleteMapping("/{id}")
  public Boolean deletePost(@PathVariable String id, @RequestParam String operatorId) {
    return postService.deletePostById(id, operatorId);
  }

  @PutMapping("/{id}")
  public Boolean updatePost(@PathVariable String id, @RequestParam String title,
      @RequestParam String content, @RequestParam String operatorId) {
    return postService.updatePostTitleAndContent(id, title, content, operatorId);
  }

  @GetMapping("/{id}")
  public Post findPost(@PathVariable String id, @RequestParam(required = false) String operatorId) {
    return postService.findPostById(id, operatorId);
  }

  @GetMapping("")
  public Paging<Post> paging(PostPagingCriteria criteria) {
    return postService.pagingPosts(criteria);
  }
}
