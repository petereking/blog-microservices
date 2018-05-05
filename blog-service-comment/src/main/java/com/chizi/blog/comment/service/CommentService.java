package com.chizi.blog.comment.service;

import com.chizi.blog.comment.dto.CommentPagingCriteria;
import com.chizi.blog.comment.model.Comment;
import com.chizi.blog.common.Paging;

/**
 * DATE: 17/3/24 下午1:41 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 *
 * 评论服务
 */

public interface CommentService {

    String createComment(Comment comment);

    Boolean deleteCommentById(String commentId, String operatorId);

    Paging<Comment> findCommentsByBlogId(CommentPagingCriteria commentPagingCriteria);
}
