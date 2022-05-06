package com.example.demo.service;

import com.example.demo.vo.KnowledgePointVo;
import com.example.demo.vo.Result;
import com.example.demo.vo.param.KnowledgePointParams;
import com.example.demo.vo.param.PageParams;

public interface KnowledgePointService {
  /**
   * 获取教学大纲-教学目标 treeData
   *
   * @param pageParams
   * @return
   */
  Result knowledgePointSelectData(PageParams pageParams);

  /**
   * 添加知识点
   *
   * @param knowledgePointVo
   * @return
   */
  Result addKnowledgePoint(KnowledgePointVo knowledgePointVo);

  /**
   * 获取知识点列表
   *
   * @param knowledgePointParams
   * @return
   */
  Result knowledgePointList(KnowledgePointParams knowledgePointParams);

  /**
   * 更新知识点
   *
   * @param knowledgePointVo
   * @return
   */
  Result updateKnowledgePoint(KnowledgePointVo knowledgePointVo);
}
