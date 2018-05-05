package com.chizi.blog.post.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostVisitedEvent implements Serializable {
  private static final long serialVersionUID = 5566971764380210366L;

  private String postId;

  private String visitorId;
}
