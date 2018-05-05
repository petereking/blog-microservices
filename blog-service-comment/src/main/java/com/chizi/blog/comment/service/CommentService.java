package com.chizi.blog.comment.service;

import com.chizi.blog.comment.dto.CommentPagingCriteria;
import com.chizi.blog.comment.model.Comment;
import com.chizi.blog.common.Paging;

public interface CommentService {
  String createComment(Comment comment);

  Boolean deleteCommentById(String commentId, String operatorId);

  Paging<Comment> findCommentsByBlogId(CommentPagingCriteria commentPagingCriteria);
}
