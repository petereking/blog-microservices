package com.chizi.blog.post.service.impl;

import com.chizi.blog.common.Paging;
import com.chizi.blog.post.common.PostServiceException;
import com.chizi.blog.post.dto.PostPagingCriteria;
import com.chizi.blog.post.event.PostVisitedEvent;
import com.chizi.blog.post.model.Post;
import com.chizi.blog.post.repo.PostRepo;
import com.chizi.blog.post.service.PostService;
import com.google.common.base.Throwables;
import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PostServiceJpaImpl implements PostService {
  private final PostRepo postRepo;
  private final EventBus eventBus;

  @Autowired
  public PostServiceJpaImpl(PostRepo postRepo, EventBus eventBus) {
    this.postRepo = postRepo;
    this.eventBus = eventBus;
  }

  @Override
  public String createPost(Post post) {
    if (post.getAuthor() == null) {
      throw new PostServiceException("post.author.required");
    }
    try {
      post.setPv(0);
      postRepo.save(post);
      return post.getId();
    } catch (Exception e) {
      log.error("createPost fail, post={}, cause={}", post, Throwables.getStackTraceAsString(e));
      throw new PostServiceException("post.create.fail");
    }
  }

  @Override
  public Paging<Post> pagingPosts(PostPagingCriteria criteria) {
    try {
      Page<Post> postEntities;
      Pageable pageable =
          new PageRequest(criteria.getPageNo(), criteria.getPageSize(), Sort.Direction.DESC, "id");
      if (criteria.getAuthorId() != null) {
        postEntities = postRepo.findByAuthorId(criteria.getAuthorId(), pageable);
      } else {
        postEntities = postRepo.findAll(pageable);
      }
      return new Paging<>(postEntities.getTotalElements(), postEntities.getContent());
    } catch (Exception e) {
      log.error("pagingPosts posts fail, criteria={}, cause={}", criteria,
          Throwables.getStackTraceAsString(e));
      throw new PostServiceException("post.paging.fail");
    }
  }

  @Override
  public Post findPostById(String id, String operatorId) {
    try {
      Post post = postRepo.findOne(id);
      eventBus.post(new PostVisitedEvent(id, operatorId));
      return post;
    } catch (Exception e) {
      log.error("findPostById fail, id={}, cause={}", id, Throwables.getStackTraceAsString(e));
      throw new PostServiceException("post.find.by.id.fail");
    }
  }

  @Override
  public Boolean updatePostTitleAndContent(String id, String title, String content,
      String operatorId) {
    try {
      Post entity = postRepo.findOne(id);
      if (entity == null) {
        return Boolean.FALSE;
      }
      entity.setTitle(title);
      entity.setContent(content);
      postRepo.save(entity);
      return Boolean.TRUE;
    } catch (Exception e) {
      log.error("updatePostTitleAndContent fail, id={}, title={}, content={}, cause={}", id, title,
          content, Throwables.getStackTraceAsString(e));
      throw new PostServiceException("post.update.fail");
    }
  }

  @Override
  public Boolean deletePostById(String id, String operatorId) {
    try {
      Post entity = postRepo.findOne(id);
      if (entity == null) {
        return Boolean.FALSE;
      }
      postRepo.delete(id);
      return Boolean.TRUE;
    } catch (Exception e) {
      log.error("deletePostById fail, id={}, cause={}", id, Throwables.getStackTraceAsString(e));
      throw new PostServiceException("post.delete.fail");
    }
  }
}
