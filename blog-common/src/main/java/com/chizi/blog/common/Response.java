package com.chizi.blog.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@EqualsAndHashCode
public class Response<T> implements Serializable {
  private static final long serialVersionUID = 7792969777029326271L;

  private boolean success;
  @Getter
  private T result;
  @Getter
  private String error;
  @Getter
  @Setter
  private String errorDetail;

  public Response() {}

  public boolean isSuccess() {
    return this.success;
  }

  public boolean isFail() {
    return !this.success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }


  public void setResult(T result) {
    this.success = true;
    this.result = result;
  }

  public void setError(String error) {
    this.success = false;
    this.error = error;
  }

  public static <T> Response<T> ok(T data) {
    Response<T> resp = new Response<>();
    resp.setResult(data);
    return resp;
  }

  public static <T> Response<T> fail(String error) {
    Response<T> resp = new Response<>();
    resp.setError(error);
    return resp;
  }

  public static <T> Response<T> fail(String error, String errorDetail) {
    Response<T> resp = new Response<>();
    resp.setError(error);
    resp.setErrorDetail(errorDetail);
    return resp;
  }
}
