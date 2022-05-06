package com.example.demo.vo.param;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 教学目标查询参数类
 */
@Data
@AllArgsConstructor
public class TeachingGoalParams {
    private int id = -1;
    private int page = 1;
    private int pageSize = 10;
    private int teachingOutlineId;
}
