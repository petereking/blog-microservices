package com.chizi.blog.comment.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.chizi.blog.comment.model.Comment;

@Repository
public interface CommentRepo extends PagingAndSortingRepository<Comment, String> {
  Page<Comment> findByPostId(String postId, Pageable pageable);
}