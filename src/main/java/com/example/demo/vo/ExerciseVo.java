package com.example.demo.vo;

import lombok.Data;

@Data
public class ExerciseVo {
  private int id;
  private String questionStem;
  private String optionA;
  private String optionB;
  private String optionC;
  private String optionD;
  private String correctAnswer;
  private int score;
  private int knowledgePoint;
  private String category;
  private String type;
  private int workId;
}
