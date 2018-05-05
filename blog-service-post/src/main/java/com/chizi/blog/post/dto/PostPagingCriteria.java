package com.chizi.blog.post.dto;

import com.chizi.blog.common.PagingCriteria;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PostPagingCriteria implements PagingCriteria {
  private Integer pageSize;
  private Integer pageNo;
  private String authorId;

  public Integer getPageSize() {
    return pageSize == null ? 10 : pageSize;
  }

  public Integer getPageNo() {
    return pageNo == null ? 0 : pageNo;
  }
}
