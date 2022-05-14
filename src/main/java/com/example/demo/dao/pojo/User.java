package com.example.demo.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {
  @TableId(value = "id", type = IdType.AUTO)
  private int id;
  private String userName;
  private String password;
  private String type;
  private int courseId;

}
