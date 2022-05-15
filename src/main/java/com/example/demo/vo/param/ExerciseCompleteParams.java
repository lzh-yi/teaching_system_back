package com.example.demo.vo.param;

import lombok.Data;

@Data
public class ExerciseCompleteParams {
  private int page = 1;
  private int pageSize = 1000;
  private int id = -1;
  private int exerciseId = -1;
  private int userId = -1;
  private String answer;
  private int score;
}
