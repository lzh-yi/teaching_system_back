package com.example.demo.service;

import com.example.demo.vo.ExerciseVo;
import com.example.demo.vo.Result;
import com.example.demo.vo.param.ExerciseParams;

public interface ExerciseService {
  /**
   * 添加习题组
   *
   * @param exerciseVo
   * @return
   */
  Result addExercise(ExerciseVo exerciseVo);

  /**
   * 习题组题目列表
   *
   * @param exerciseParams
   * @return
   */
  Result exerciseList(ExerciseParams exerciseParams);
}
