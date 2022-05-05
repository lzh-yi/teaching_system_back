package com.example.demo.service;

import com.example.demo.vo.Result;
import com.example.demo.vo.TeachingGoalVo;
import com.example.demo.vo.param.TeachingGoalParams;

public interface TeachingGoalService {

    /**
     * 获取教学目标列表
     * @param teachingGoalParams
     * @return
     */
    Result teachingGoalList(TeachingGoalParams teachingGoalParams);

    /**
     * 添加教学目标
     * @param teachingGoalVo
     * @return
     */
    Result addTeachingGoal(TeachingGoalVo teachingGoalVo);


    /**
     * 修改教学目标
     * @param teachingGoalVo
     * @return
     */
    Result updateTeachingGaol(TeachingGoalVo teachingGoalVo);
}

