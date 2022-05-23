package com.example.demo.vo.param;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 题组习题查询参数类
 */
@Data
@AllArgsConstructor
public class ExerciseParams {
  //  private int id = -1;
  private int page = 1;
  private int pageSize = 1000;
  private int workId;
  private String category;
  private int knowledgePointId = -1;
}
