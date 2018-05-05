package com.chizi.blog.web.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

import com.chizi.blog.common.PagingCriteria;

@Data
@Accessors(chain = true)
public class PostPagingCriteria implements PagingCriteria {
  private Integer pageSize;
  private Integer pageNo;
  private Long authorId;

  public Integer getPageSize() {
    return pageSize == null ? 10 : pageSize;
  }

  public Integer getPageNo() {
    return pageNo == null ? 0 : pageNo;
  }

  public Map<String, Object> toMap() {
    Map<String, Object> map = new HashMap<>();
    map.put("authorId", authorId);
    map.put("pageSize", pageSize);
    map.put("pageNo", pageNo);
    return map;
  }
}
