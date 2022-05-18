package com.example.demo.service;

import com.example.demo.vo.Result;
import com.example.demo.vo.param.ScoreParams;

public interface ScoreService {
  /**
   * 获取成绩详情
   *
   * @param scoreParams
   * @return
   */
  Result getScoreDetail(ScoreParams scoreParams);

  /**
   * 获取成绩详情
   *
   * @param scoreParams
   * @return
   */
  Result getScoreDetailById(ScoreParams scoreParams);

  /**
   * 获取成绩详情
   *
   * @return
   */
  Result getFinalScoreList();
}
