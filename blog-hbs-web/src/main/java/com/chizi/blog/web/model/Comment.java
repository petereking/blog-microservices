package com.chizi.blog.web.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class Comment implements Serializable {
  private static final long serialVersionUID = 8343096061678282178L;

  private String id;
  private String postId;
  private String authorId;
  private String content;
  private Date createdAt;
  private Date updatedAt;
  private User author;
  private Post post;
}
