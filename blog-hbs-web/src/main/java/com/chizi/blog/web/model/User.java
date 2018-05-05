package com.chizi.blog.web.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class User implements Serializable {
  private static final long serialVersionUID = -1575177945061174211L;

  private Long id;
  private String username;
  private String password;
  private String avatar;
  private String email;
  private String gender;
  private String bio;
  private Date createdAt;
  private Date updatedAt;
}
