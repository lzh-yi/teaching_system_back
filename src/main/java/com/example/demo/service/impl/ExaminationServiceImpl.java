package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dao.mapper.ExaminationMapper;
import com.example.demo.dao.pojo.*;
import com.example.demo.service.*;
import com.example.demo.vo.*;
import com.example.demo.vo.param.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExaminationServiceImpl implements ExaminationService {
  @Autowired
  private ExaminationMapper examinationMapper;
  @Autowired
  private TeachingGoalService teachingGoalService;
  @Autowired
  private KnowledgePointService knowledgePointService;
  @Autowired
  private WorkStatisticsService workStatisticsService;
  @Autowired
  private UserService userService;

  @Override
  public Result addGroupWork(GroupWorkVo groupWorkVo) throws ParseException {
    Examination examination = new Examination();
    BeanUtils.copyProperties(groupWorkVo, examination);
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // 设置发布和截止时间
    if (groupWorkVo.getPublishTime() != "" && groupWorkVo.getDeadlineTime() != "") {
      examination.setPublishTime(formatter.parse(groupWorkVo.getPublishTime()));
      examination.setDeadlineTime(formatter.parse(groupWorkVo.getDeadlineTime()));
    }
    // 设置占比
    examination.setProportion(new BigDecimal(groupWorkVo.getProportion()));

    int count = examinationMapper.insert(examination);
    if (count == 1) {
      return Result.success(null, null);
    } else {
      return Result.fail(300, "插入考试失败");
    }
  }

  @Override
  public Result groupWorkList(GroupWorkParams groupWorkParams) {
    // 获取分页对象
    PageParams pageParams = new PageParams(groupWorkParams.getPage(), groupWorkParams.getPageSize());
    Page<Examination> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
    LambdaQueryWrapper<Examination> queryWrapper = new LambdaQueryWrapper<>();
    // 获取查询参数
    if (groupWorkParams.getName() != "") {
      queryWrapper.eq(Examination::getName, groupWorkParams.getName());
    }
    if (groupWorkParams.getId() != -1) {
      queryWrapper.eq(Examination::getId, groupWorkParams.getId());
    }

    queryWrapper.orderByDesc(Examination::getId);
    Page<Examination> groupWorkPage = examinationMapper.selectPage(page, queryWrapper);
    List<Examination> list = groupWorkPage.getRecords();

    return Result.success(list, groupWorkPage.getTotal());
  }

  @Override
  public Result updateGroupWork(GroupWorkVo groupWorkVo) throws ParseException {
    Examination examination = new Examination();
    BeanUtils.copyProperties(groupWorkVo, examination);
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // 设置发布和截止时间
    if (groupWorkVo.getPublishTime() != "" && groupWorkVo.getDeadlineTime() != "") {
      examination.setPublishTime(formatter.parse(groupWorkVo.getPublishTime()));
      examination.setDeadlineTime(formatter.parse(groupWorkVo.getDeadlineTime()));
    }
    // 设置占比
    examination.setProportion(new BigDecimal(groupWorkVo.getProportion()));
    int count = examinationMapper.updateById(examination);
    System.out.println(count);
    if (count == 1) {
      return Result.success(null, null);
    } else {
      return Result.fail(300, "更新考试失败");
    }
  }

  @Override
  public Result knowledgePointSelectData(KnowledgeAndOutlineParams knowledgeAndOutlineParams) {
    List<KnowledgeAndOutlineTree> knowledgeAndGoalTreeList = new ArrayList<>();
    // 获取指定教学大纲下的所有教学目标
    TeachingGoalParams teachingGoalParams = new TeachingGoalParams(-1, 1, 1000, knowledgeAndOutlineParams.getTeachingOutlineId());

    List<TeachingGoal> teachingGoalList = (List<TeachingGoal>) teachingGoalService.teachingGoalList(teachingGoalParams).getData();
    // 查询每个教学目标下的所有知识点
    for (TeachingGoal teachingGoal : teachingGoalList) {
      KnowledgeAndOutlineTree treeParent = new KnowledgeAndOutlineTree();
      treeParent.setTitle(teachingGoal.getTitle());
      treeParent.setValue(teachingGoal.getId() + "{}");


      KnowledgePointParams knowledgePointParams = new KnowledgePointParams(-1, 1, 1000, teachingGoal.getId());
      List<KnowledgePoint> knowledgePointList = (List<KnowledgePoint>) knowledgePointService.knowledgePointList(knowledgePointParams).getData();
      List<TreeNode> treeNodeChildList = new ArrayList<>();
      for (KnowledgePoint knowledgePoint : knowledgePointList) {
        TreeNode treeChild = new TreeNode();
        // 构造数据
        treeChild.setTitle(knowledgePoint.getTitle());
        treeChild.setValue(knowledgePoint.getId());
        treeNodeChildList.add(treeChild);
      }
      treeParent.setChildren(treeNodeChildList);
      knowledgeAndGoalTreeList.add(treeParent);
    }

    return Result.success(knowledgeAndGoalTreeList, null);

  }

  @Override
  public Result insertCompleteList(InsertCompleteListParams insertCompleteListParams) throws ParseException {
    // 获取所有的学生
    UserVo userVo = new UserVo();
    userVo.setType("student");
    List<User> userList = (List<User>) userService.userList(userVo).getData();

    for (User user : userList) {
      WorkStatisticsVo workStatisticsVo = new WorkStatisticsVo();
      workStatisticsVo.setWorkId(insertCompleteListParams.getWorkId());
      workStatisticsVo.setCategory("1");
      workStatisticsVo.setUserId(user.getId());
      workStatisticsVo.setSubmitStatus(insertCompleteListParams.getSubmitStatus());
      workStatisticsVo.setCorrectStatus("0");
      workStatisticsVo.setScore(0);
      workStatisticsService.insertComplete(workStatisticsVo);
    }
    return Result.success(null, null);
  }

  @Override
  public Result updateCompleteList(InsertCompleteListParams insertCompleteListParams) throws ParseException {
    // 获取指定题组下的所
    WorkStatisticsParams workStatisticsParams = new WorkStatisticsParams();
    workStatisticsParams.setWorkId(insertCompleteListParams.getWorkId());
    workStatisticsParams.setCategory("1");
    workStatisticsParams.setUserId(-1);
    List<WorkStatistics> workStatisticsList = (List<WorkStatistics>) workStatisticsService.completeList(workStatisticsParams).getData();
    System.out.println(workStatisticsList);
    // 更新
    for (WorkStatistics workStatistics : workStatisticsList) {
      workStatistics.setSubmitStatus("1");
      workStatistics.setCorrectStatus("1");
      WorkStatisticsVo workStatisticsVo = new WorkStatisticsVo();
      BeanUtils.copyProperties(workStatistics, workStatisticsVo);
      workStatisticsService.update(workStatisticsVo);
    }

    return Result.success(null, null);
  }
}
