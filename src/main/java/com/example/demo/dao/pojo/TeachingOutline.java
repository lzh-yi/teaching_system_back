package com.example.demo.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class TeachingOutline {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    private String title;
    private String version;
    private Date uploadingTime;
    private String principal;
    private String fileName;
    private String filePath;
}
