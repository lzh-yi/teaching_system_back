package com.example.demo.vo.param;

import lombok.Data;

/**
 * 教学大纲查询参数类
 */
@Data
public class TeachingOutlineParams {
    private int page = 1;
    private int pageSize = 10;
    private String title = "";
    private int id;
}
