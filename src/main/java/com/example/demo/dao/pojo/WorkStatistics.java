package com.example.demo.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class WorkStatistics {
  @TableId(value = "id", type = IdType.AUTO)
  private int id;
  private int workId;
  private int userId;
  private String submitStatus;
  private Date submitTime;
  private String correctStatus;
  private int score;
  private String category;
}
