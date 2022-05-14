package com.example.demo.service;

import com.example.demo.vo.ExerciseCompleteVo;
import com.example.demo.vo.Result;
import com.example.demo.vo.param.ExerciseCompleteParams;

public interface ExerciseCompleteService {
  /**
   * 添加 题目完成情况
   *
   * @param exerciseCompleteVo
   * @return
   */
  Result addExerciseComplete(ExerciseCompleteVo exerciseCompleteVo);

  /**
   * 修改 题目完成情况
   *
   * @param exerciseCompleteVo
   * @return
   */
  Result updateExerciseComplete(ExerciseCompleteVo exerciseCompleteVo);

  /**
   * 获取 题目完成情况列表
   *
   * @param exerciseCompleteParams
   * @return
   */
  Result exerciseCompleteList(ExerciseCompleteParams exerciseCompleteParams);
}
