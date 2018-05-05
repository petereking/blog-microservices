package com.chizi.blog.comment.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document
@Data
@Accessors(chain = true)
public class Comment implements Serializable {
  private static final long serialVersionUID = 8343096061678282178L;

  @Id
  private String id;
  private String postId;
  private String authorId;
  private String content;
  private Date createdAt;
  private Date updatedAt;
  private User author;
}
