package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
  private boolean success;
  private int code;
  private String msg;
  private Object data;
  private Long total;

  public static Result success(Object data, Long total) {
    return new Result(true, 200, "success", data, total);
  }

  public static Result fail(int code, String msg) {
    return new Result(false, code, msg, null, 0L);
  }
}
