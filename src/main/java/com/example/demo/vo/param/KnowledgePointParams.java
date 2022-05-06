package com.example.demo.vo.param;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 知识点查询参数类
 */
@Data
@AllArgsConstructor
public class KnowledgePointParams {
  private int id = -1;
  private int page = 1;
  private int pageSize = 10;
  private int teachingGoalId = -1;
}
