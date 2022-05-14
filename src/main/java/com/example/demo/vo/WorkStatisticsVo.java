package com.example.demo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class WorkStatisticsVo {
  @TableId(value = "id", type = IdType.AUTO)
  private int id;
  private int workId;
  private int userId;
  private String submitStatus;
  private String submitTime;
  private String correctStatus;
  private int score;
  private String category;
}
