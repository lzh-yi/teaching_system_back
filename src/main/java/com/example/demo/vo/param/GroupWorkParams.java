package com.example.demo.vo.param;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 题组查询参数类
 */
@Data
@AllArgsConstructor
public class GroupWorkParams {
  private int id = -1;
  private int page = 1;
  private int pageSize = 1000;
  private String name = "";
}
