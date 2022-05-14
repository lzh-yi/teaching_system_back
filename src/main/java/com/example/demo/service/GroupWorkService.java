package com.example.demo.service;

import com.example.demo.vo.GroupWorkVo;
import com.example.demo.vo.Result;
import com.example.demo.vo.param.GroupWorkParams;
import com.example.demo.vo.param.InsertCompleteListParams;
import com.example.demo.vo.param.KnowledgeAndOutlineParams;

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

  /**
   * 知识点下拉列表
   *
   * @param knowledgeAndOutlineParams
   * @return
   */
  Result knowledgePointSelectData(KnowledgeAndOutlineParams knowledgeAndOutlineParams);

  /**
   * 插入完成情况
   *
   * @param insertCompleteListParams
   * @return
   */
  Result insertCompleteList(InsertCompleteListParams insertCompleteListParams) throws ParseException;

  /**
   * 更新完成情况
   *
   * @param insertCompleteListParams
   * @return
   */
  Result updateCompleteList(InsertCompleteListParams insertCompleteListParams) throws ParseException;

}
