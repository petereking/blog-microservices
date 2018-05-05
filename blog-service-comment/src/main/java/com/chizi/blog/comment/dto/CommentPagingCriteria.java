package com.chizi.blog.comment.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

import com.chizi.blog.common.PagingCriteria;

@Data
@Accessors(chain = true)
public class CommentPagingCriteria implements PagingCriteria {
  private Integer pageSize;
  private Integer pageNo;
  @NotNull
  private String postId;

  public Integer getPageSize() {
    return pageSize == null ? 10 : pageSize;
  }

  public Integer getPageNo() {
    return pageNo == null ? 0 : pageNo;
  }
}
