package com.chizi.blog.post.model;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User implements Serializable {
  private static final long serialVersionUID = -1575177945061174211L;

  private String id;
  private String username;
  private String avatar;
  private String email;
  private String gender;
  private String bio;
}
