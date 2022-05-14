package com.example.demo.service;

import com.example.demo.vo.Result;
import com.example.demo.vo.WorkStatisticsVo;
import com.example.demo.vo.param.WorkStatisticsParams;

import java.text.ParseException;

public interface WorkStatisticsService {
  /**
   * 学生-题组/考试完成情况列表
   *
   * @param workStatisticsParams
   * @return
   */
  Result completeList(WorkStatisticsParams workStatisticsParams);

  /**
   * 插入 学生-题组/考试完成情况
   *
   * @param workStatisticsVo
   * @return
   */
  Result insertComplete(WorkStatisticsVo workStatisticsVo) throws ParseException;

  /**
   * 更新 学生-题组/考试完成情况
   *
   * @param workStatisticsVo
   * @return
   */
  Result update(WorkStatisticsVo workStatisticsVo) throws ParseException;
}
