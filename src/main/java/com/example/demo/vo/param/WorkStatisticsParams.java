package com.example.demo.vo.param;

import lombok.Data;

@Data
public class WorkStatisticsParams {
  //  private int id = -1;
  private int page = 1;
  private int pageSize = 1000;
  private String category;
  private int workId;
  private int userId;
}
