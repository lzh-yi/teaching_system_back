package com.example.demo.service;

import com.example.demo.vo.GroupWorkVo;
import com.example.demo.vo.Result;
import com.example.demo.vo.param.GroupWorkParams;

import java.text.ParseException;

public interface GroupWorkService {
  /**
   * 添加习题组
   *
   * @param groupWorkVo
   * @return
   */
  Result addGroupWork(GroupWorkVo groupWorkVo) throws ParseException;

  /**
   * 题组列表
   *
   * @param groupWorkParams
   * @return
   */
  Result groupWorkList(GroupWorkParams groupWorkParams);

  /**
   * 更新题组
   *
   * @param groupWorkVo
   * @return
   */
  Result updateGroupWork(GroupWorkVo groupWorkVo) throws ParseException;

}
