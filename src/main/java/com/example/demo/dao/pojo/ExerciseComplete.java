package com.example.demo.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ExerciseComplete {
  @TableId(value = "id", type = IdType.AUTO)
  private int id;
  private int exerciseId;
  private int userId;
  private String answer;
  private int score;
}
