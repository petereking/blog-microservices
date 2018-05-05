package com.chizi.blog.post.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.chizi.blog.post.model.Post;

@Repository
public interface PostRepo extends PagingAndSortingRepository<Post, String> {
  Page<Post> findByAuthorId(String authorId, Pageable pageable);
}
