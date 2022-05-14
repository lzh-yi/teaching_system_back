package com.example.demo.vo.param;

import lombok.Data;

@Data
public class ExerciseCompleteParams {
  private int page = 1;
  private int pageSize = 1000;
  private int id;
  private int exerciseId;
  private int userId;
  private String answer;
  private int score;
}
