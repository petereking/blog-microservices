package com.chizi.blog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chizi.blog.web.model.Comment;
import com.chizi.blog.web.service.CommentServiceClient;
import com.google.common.base.Throwables;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/posts/{postId}/comments")
public class Comments extends ViewBase {
  private final CommentServiceClient commentService;

  @Autowired
  public Comments(CommentServiceClient commentService) {
    this.commentService = commentService;
  }

  @PostMapping("")
  public String comment(@PathVariable String postId, String content,
      RedirectAttributes attributes) {
    Comment comment = new Comment().setPostId(postId).setContent(content)
        .setAuthor(getCurrentUser()).setAuthorId(getCurrentUserId());
    try {
      commentService.createComment(comment);

      attributes.addFlashAttribute("success", "comment.create.success");
      return "redirect:/posts/" + postId;
    } catch (Exception e) {
      log.error("createComment fail, comment={}, cause={}", comment,
          Throwables.getStackTraceAsString(e));
      attributes.addFlashAttribute("error", "create.comment.fail");
      return "redirect:/posts/" + postId;
    }
  }

  @PostMapping("/{commentId}/remove")
  public String removeComment(@PathVariable String commentId, @PathVariable String postId,
      RedirectAttributes attributes) {
    try {
      commentService.deleteComment(commentId, getCurrentUserId());
      attributes.addFlashAttribute("success", "comment.remove.success");
      return "redirect:/posts/" + postId;
    } catch (Exception e) {
      log.error("removeComment fail, commentId={}, cause={}", commentId,
          Throwables.getStackTraceAsString(e));
      attributes.addFlashAttribute("success", "comment.remove.fail");
      return "redirect:/posts/" + postId;
    }
  }
}
