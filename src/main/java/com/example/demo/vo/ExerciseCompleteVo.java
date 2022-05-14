package com.example.demo.vo;

import lombok.Data;

@Data
public class ExerciseCompleteVo {
  private int id;
  private int exerciseId;
  private int userId;
  private String answer;
  private int score;
}
