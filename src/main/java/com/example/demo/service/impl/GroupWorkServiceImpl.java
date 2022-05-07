package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dao.mapper.GroupWorkMapper;
import com.example.demo.dao.pojo.GroupWork;
import com.example.demo.service.GroupWorkService;
import com.example.demo.vo.GroupWorkVo;
import com.example.demo.vo.Result;
import com.example.demo.vo.param.GroupWorkParams;
import com.example.demo.vo.param.PageParams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class GroupWorkServiceImpl implements GroupWorkService {
  @Autowired
  private GroupWorkMapper groupWorkMapper;

  @Override
  public Result addGroupWork(GroupWorkVo groupWorkVo) throws ParseException {
    GroupWork groupWork = new GroupWork();
    BeanUtils.copyProperties(groupWorkVo, groupWork);
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // 设置发布和截止时间
    if (groupWorkVo.getPublishTime() != "" && groupWorkVo.getDeadlineTime() != "") {
      groupWork.setPublishTime(formatter.parse(groupWorkVo.getPublishTime()));
      groupWork.setDeadlineTime(formatter.parse(groupWorkVo.getDeadlineTime()));
    }
    // 设置占比
    groupWork.setProportion(new BigDecimal(groupWorkVo.getProportion()));

    int count = groupWorkMapper.insert(groupWork);
    if (count == 1) {
      return Result.success(null, null);
    } else {
      return Result.fail(300, "插入题组失败");
    }
  }

  @Override
  public Result groupWorkList(GroupWorkParams groupWorkParams) {
    // 获取分页对象
    PageParams pageParams = new PageParams(groupWorkParams.getPage(), groupWorkParams.getPageSize());
    Page<GroupWork> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
    LambdaQueryWrapper<GroupWork> queryWrapper = new LambdaQueryWrapper<>();
    // 获取查询参数
    if (groupWorkParams.getName() != "") {
      queryWrapper.eq(GroupWork::getName, groupWorkParams.getName());
    }
    if (groupWorkParams.getId() != -1) {
      queryWrapper.eq(GroupWork::getId, groupWorkParams.getId());
    }

    queryWrapper.orderByDesc(GroupWork::getId);
    Page<GroupWork> groupWorkPage = groupWorkMapper.selectPage(page, queryWrapper);
    List<GroupWork> list = groupWorkPage.getRecords();

    return Result.success(list, groupWorkPage.getTotal());
  }

  @Override
  public Result updateGroupWork(GroupWorkVo groupWorkVo) throws ParseException {
    GroupWork groupWork = new GroupWork();
    BeanUtils.copyProperties(groupWorkVo, groupWork);
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // 设置发布和截止时间
    if (groupWorkVo.getPublishTime() != "" && groupWorkVo.getDeadlineTime() != "") {
      groupWork.setPublishTime(formatter.parse(groupWorkVo.getPublishTime()));
      groupWork.setDeadlineTime(formatter.parse(groupWorkVo.getDeadlineTime()));
    }
    // 设置占比
    groupWork.setProportion(new BigDecimal(groupWorkVo.getProportion()));
    int count = groupWorkMapper.updateById(groupWork);
    System.out.println(count);
    if (count == 1) {
      return Result.success(null, null);
    } else {
      return Result.fail(300, "更新题组失败");
    }
  }
}
