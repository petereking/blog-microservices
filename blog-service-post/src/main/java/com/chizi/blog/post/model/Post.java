package com.chizi.blog.post.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document(collection = "posts")
@Data
@Accessors(chain = true)
public class Post implements Serializable {
  private static final long serialVersionUID = -2826939241838421885L;

  @Id
  private String id;
  private String authorId;
  private String title;
  private String content;
  private Integer pv;
  private Date createdAt;
  private Date updatedAt;
  private User author;
}
