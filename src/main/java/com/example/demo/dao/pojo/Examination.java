package com.example.demo.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Examination {
  @TableId(value = "id", type = IdType.AUTO)
  private int id;
  private int teachingOutlineId;
  private String name;
  private String description;
  private String difficultyLevel;
  private int suggestFinishTime;
  private BigDecimal proportion;
  private String author;
  private Date publishTime;
  private Date deadlineTime;
  private String status;
}
