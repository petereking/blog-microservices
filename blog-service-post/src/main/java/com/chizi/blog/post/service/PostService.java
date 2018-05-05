package com.chizi.blog.post.service;


import com.chizi.blog.common.Paging;
import com.chizi.blog.post.dto.PostPagingCriteria;
import com.chizi.blog.post.model.Post;

/**
 * DATE: 17/3/24 下午1:37 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 *
 * 博客服务
 */
public interface PostService {

    String createPost(Post post);

    Paging<Post> pagingPosts(PostPagingCriteria criteria);

    Post findPostById(String id, String operatorId);

    Boolean updatePostTitleAndContent(String id, String title, String content, String operatorId);

    Boolean deletePostById(String id, String operatorId);
}
