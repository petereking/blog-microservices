package com.chizi.blog.post.service;


import com.chizi.blog.common.Paging;
import com.chizi.blog.post.dto.PostPagingCriteria;
import com.chizi.blog.post.model.Post;

public interface PostService {
  String createPost(Post post);

  Paging<Post> pagingPosts(PostPagingCriteria criteria);

  Post findPostById(String id, String operatorId);

  Boolean updatePostTitleAndContent(String id, String title, String content, String operatorId);

  Boolean deletePostById(String id, String operatorId);
}
