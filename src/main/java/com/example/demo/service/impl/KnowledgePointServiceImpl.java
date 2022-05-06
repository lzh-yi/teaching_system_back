package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dao.mapper.KnowledgePointMapper;
import com.example.demo.dao.pojo.KnowledgePoint;
import com.example.demo.dao.pojo.TeachingGoal;
import com.example.demo.dao.pojo.TeachingOutline;
import com.example.demo.service.KnowledgePointService;
import com.example.demo.service.TeachingGoalService;
import com.example.demo.service.TeachingOutlineService;
import com.example.demo.vo.KnowledgeAndOutlineTree;
import com.example.demo.vo.KnowledgePointVo;
import com.example.demo.vo.Result;
import com.example.demo.vo.TreeNode;
import com.example.demo.vo.param.KnowledgePointParams;
import com.example.demo.vo.param.PageParams;
import com.example.demo.vo.param.TeachingGoalParams;
import com.example.demo.vo.param.TeachingOutlineParams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KnowledgePointServiceImpl implements KnowledgePointService {

  @Autowired
  private TeachingOutlineService teachingOutlineService;
  @Autowired
  private TeachingGoalService teachingGoalService;
  @Autowired
  private KnowledgePointMapper knowledgePointMapper;

  @Override
  public Result knowledgePointSelectData(PageParams pageParams) {
    List<KnowledgeAndOutlineTree> knowledgeAndOutlineTreeList = new ArrayList<>();

    // 获取教学大纲列表
    TeachingOutlineParams teachingOutlineParams = new TeachingOutlineParams();
    BeanUtils.copyProperties(pageParams, teachingOutlineParams);
    teachingOutlineParams.setId(-1);
    teachingOutlineParams.setTitle("");
    List<TeachingOutline> teachingOutlineList = (List<TeachingOutline>) teachingOutlineService.teachingOutlineList(teachingOutlineParams).getData();
    // 查询每个教学大纲下的教学目标
    for (TeachingOutline teachingOutline : teachingOutlineList) {
      KnowledgeAndOutlineTree treeParent = new KnowledgeAndOutlineTree();
      treeParent.setTitle(teachingOutline.getTitle() + "(" + teachingOutline.getVersion() + ")");
      treeParent.setValue(teachingOutline.getId() + "{}");

      TeachingGoalParams teachingGoalParams = new TeachingGoalParams(-1, pageParams.getPage(), pageParams.getPageSize(), teachingOutline.getId());
      List<TeachingGoal> teachingGoalList = (List<TeachingGoal>) teachingGoalService.teachingGoalList(teachingGoalParams).getData();

      List<TreeNode> treeNodeChildList = new ArrayList<>();
      for (TeachingGoal teachingGoal : teachingGoalList) {
        TreeNode treeChild = new TreeNode();
        // 构造数据
        treeChild.setTitle(teachingGoal.getTitle());
        treeChild.setValue(teachingGoal.getId());
        treeNodeChildList.add(treeChild);
      }
      treeParent.setChildren(treeNodeChildList);
      knowledgeAndOutlineTreeList.add(treeParent);
    }
//    System.out.println(knowledgeAndOutlineTreeList);
    return Result.success(knowledgeAndOutlineTreeList, null);

  }

  @Override
  public Result addKnowledgePoint(KnowledgePointVo knowledgePointVo) {
    KnowledgePoint knowledgePoint = new KnowledgePoint();
    BeanUtils.copyProperties(knowledgePointVo, knowledgePoint);
    int count = knowledgePointMapper.insert(knowledgePoint);
    if (count == 1) {
      return Result.success(null, null);
    } else {
      return Result.fail(300, "插入知识点失败");
    }
  }

  @Override
  public Result knowledgePointList(KnowledgePointParams knowledgePointParams) {
    // 获取分页对象
    PageParams pageParams = new PageParams(knowledgePointParams.getPage(), knowledgePointParams.getPageSize());
    Page<KnowledgePoint> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
    LambdaQueryWrapper<KnowledgePoint> queryWrapper = new LambdaQueryWrapper<>();
    // 获取查询参数
    if (knowledgePointParams.getId() != -1) {
      queryWrapper.eq(KnowledgePoint::getId, knowledgePointParams.getId());
    }
    if (knowledgePointParams.getTeachingGoalId() != -1) {
      queryWrapper.eq(KnowledgePoint::getTeachingGoalId, knowledgePointParams.getTeachingGoalId());
    }
    queryWrapper.orderByDesc(KnowledgePoint::getId);
    Page<KnowledgePoint> knowledgePointPage = knowledgePointMapper.selectPage(page, queryWrapper);
    List<KnowledgePoint> list = knowledgePointPage.getRecords();

    return Result.success(list, knowledgePointPage.getTotal());
  }

  @Override
  public Result updateKnowledgePoint(KnowledgePointVo knowledgePointVo) {
    KnowledgePoint knowledgePoint = new KnowledgePoint();
    BeanUtils.copyProperties(knowledgePointVo, knowledgePoint);
    int count = knowledgePointMapper.updateById(knowledgePoint);
    if (count == 1) {
      return Result.success(null, null);
    } else {
      return Result.fail(300, "更新知识点失败");
    }
  }
}
