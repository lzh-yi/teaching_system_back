package com.example.demo.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Exercise {
  @TableId(value = "id", type = IdType.AUTO)
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
