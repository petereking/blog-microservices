package com.chizi.blog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chizi.blog.common.Paging;
import com.chizi.blog.web.dto.CommentPagingCriteria;
import com.chizi.blog.web.dto.PostPagingCriteria;
import com.chizi.blog.web.model.Post;
import com.chizi.blog.web.service.CommentServiceClient;
import com.chizi.blog.web.service.PostServiceClient;
import com.google.common.base.Throwables;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/posts")
public class Posts extends ViewBase {
  private final PostServiceClient postService;
  private final CommentServiceClient commentService;

  @Autowired
  public Posts(PostServiceClient postService, CommentServiceClient commentService) {
    this.postService = postService;
    this.commentService = commentService;
  }

  @GetMapping("")
  public String list(@RequestParam(required = false) Long author,
      @RequestParam(defaultValue = "0") Integer pageNo,
      @RequestParam(defaultValue = "20") Integer pageSize, Model model) {
    try {
      Paging<Post> respPaging = postService.paging(new PostPagingCriteria().setAuthorId(author)
          .setPageNo(pageNo).setPageSize(pageSize).toMap());
      model.addAttribute("posts", respPaging.getData());
      return "posts/list";

    } catch (Exception e) {
      log.error("paging posts fail,  cause={}", Throwables.getStackTraceAsString(e));
      model.addAttribute("error", "paging.post.fail");
      return "error";
    }
  }

  @GetMapping("/create")
  public String createPost() {
    return "posts/create";
  }

  @PostMapping("/create")
  public String createPost(Post post, RedirectAttributes redirectAttributes) {
    post.setAuthorId(getCurrentUserId());
    post.setAuthor(getCurrentUser());
    try {
      String resp = postService.createPost(post);
      redirectAttributes.addFlashAttribute("success", "发表成功");
      return "redirect:/posts/" + resp;
    } catch (Exception e) {
      log.error("createPost fail, post={}, cause={}", post, Throwables.getStackTraceAsString(e));
      redirectAttributes.addFlashAttribute("error", "create.post.fail");
      return "posts/create";
    }
  }

  @GetMapping("/{postId}")
  public String findById(@PathVariable("postId") String postId, Model model) {
    try {
      Post postResp = postService.findPost(postId, getCurrentUserId());
      postResp.setComments(commentService.paging(
          new CommentPagingCriteria().setPostId(postId).setPageNo(0).setPageSize(100).toMap()));

      model.addAttribute("post", postResp);
      return "posts/detail";

    } catch (Exception e) {
      log.error("find post fail,  cause={}", Throwables.getStackTraceAsString(e));
      model.addAttribute("error", "find.post.fail");
      return "error";
    }
  }

  @GetMapping("/{postId}/edit")
  public String editPost(@PathVariable("postId") String postId, Model model) {
    try {
      Post respPost = postService.findPost(postId, getCurrentUserId());
      model.addAttribute("post", respPost);
      return "posts/edit";
    } catch (Exception e) {
      log.error("edit post fail,  cause={}", Throwables.getStackTraceAsString(e));
      model.addAttribute("error", "find.post.fail");
      return "error";
    }
  }

  @PostMapping("/{postId}/edit")
  public String editPost(@PathVariable("postId") String postId, String title, String content,
      RedirectAttributes attributes) {
    try {
      postService.updatePost(postId, title, content, getCurrentUserId());

      attributes.addFlashAttribute("success", "post.edit.success");
      return "redirect:/posts/" + postId;
    } catch (Exception e) {
      log.error("edit post fail,  cause={}", Throwables.getStackTraceAsString(e));
      attributes.addFlashAttribute("error", "find.post.fail");
      return "posts/edit";
    }
  }

  @PostMapping("/{postId}/remove")
  public String removePost(@PathVariable("postId") String postId, RedirectAttributes attributes) {
    try {
      postService.deletePost(postId, getCurrentUserId());
      attributes.addFlashAttribute("success", "post.remove.success");
      return "redirect:/posts";
    } catch (Exception e) {
      log.error("remove post fail,  cause={}", Throwables.getStackTraceAsString(e));
      attributes.addFlashAttribute("error", "remove.post.fail");
      return "posts/list";
    }
  }
}
