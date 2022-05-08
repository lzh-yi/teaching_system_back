package com.example.demo.vo.param;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 知识点查询参数类
 */
@Data
@AllArgsConstructor
public class KnowledgeAndOutlineParams {
  private int page = 1;
  private int pageSize = 1000;
  private int teachingOutlineId = -1;
}
