package com.example.demo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class UserVo {
  @TableId(value = "id", type = IdType.AUTO)
  private int id;
  private String userName;
  private String password;
  private String type;
}
