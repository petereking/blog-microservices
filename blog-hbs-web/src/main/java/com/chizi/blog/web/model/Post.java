package com.chizi.blog.web.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

import com.chizi.blog.common.Paging;

@Data
@Accessors(chain = true)
public class Post implements Serializable {
  private static final long serialVersionUID = -2826939241838421885L;

  private String id;
  private String authorId;
  private String title;
  private String content;
  private Integer pv;
  private Date createdAt;
  private Date updatedAt;
  private User author;
  private Paging<Comment> comments;
}
