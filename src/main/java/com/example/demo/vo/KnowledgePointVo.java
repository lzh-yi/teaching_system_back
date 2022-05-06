package com.example.demo.vo;

import lombok.Data;

@Data
public class KnowledgePointVo {
  private int id;
  private String title;
  private String content;
  private String supportStrength;
  private int teachingGoalId;
  private String chapter;
}
