package com.example.demo.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class TeachingGoal {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    private String title;
    private String content;
    private int teachingOutlineId;
}
