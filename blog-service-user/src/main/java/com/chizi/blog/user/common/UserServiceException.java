package com.chizi.blog.user.common;

public class UserServiceException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private String message = "user.service.exception";

  public UserServiceException() {}

  public UserServiceException(String message) {
    this.message = message;
  }


  public UserServiceException(String message, Throwable cause) {
    super(message, cause);
    this.message = message;
  }

  public UserServiceException(Throwable cause) {
    super(cause);
    this.message = cause.getMessage();
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
