package com.example.logDemo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResult {
  private String code;
  private Object data;
  private String message;

  public ApiResult() {
  }

  public ApiResult(String errorCode, String msg, Object data) {
    this.code = errorCode;
    this.message = msg;
    this.data = data;
  }

  public static ApiResult success() {
    return success(null);
  }

  public static ApiResult success(Object data) {
    return new ApiResult("OK", "", data);
  }

}
