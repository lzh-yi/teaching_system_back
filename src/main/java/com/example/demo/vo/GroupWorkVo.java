package com.example.demo.vo;

import lombok.Data;

@Data
public class GroupWorkVo {
  private int id;
  private int teachingOutlineId;
  private String name;
  private String description;
  private String difficultyLevel;
  private int suggestFinishTime;
  /**
   * 题组占比
   */
  private String proportion;
  private String author;
  private String publishTime = "";
  private String deadlineTime = "";
  private String status = "0";
}
