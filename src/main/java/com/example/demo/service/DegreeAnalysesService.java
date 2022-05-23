package com.example.demo.service;

import com.example.demo.vo.Result;
import com.example.demo.vo.param.DegreeAnalysesParams;

public interface DegreeAnalysesService {
  /**
   * 教学目标达成度分析
   *
   * @param degreeAnalysesParams
   * @return
   */
  Result degreeAnalysesDetail(DegreeAnalysesParams degreeAnalysesParams);
}
