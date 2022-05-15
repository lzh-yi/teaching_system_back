package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dao.mapper.WorkStatisticsMapper;
import com.example.demo.dao.pojo.WorkStatistics;
import com.example.demo.service.WorkStatisticsService;
import com.example.demo.vo.Result;
import com.example.demo.vo.WorkStatisticsVo;
import com.example.demo.vo.param.PageParams;
import com.example.demo.vo.param.WorkStatisticsParams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class WorkStatisticsServiceImpl implements WorkStatisticsService {

  @Autowired
  private WorkStatisticsMapper workStatisticsMapper;

  @Override
  public Result completeList(WorkStatisticsParams workStatisticsParams) {
    // 获取分页对象
    PageParams pageParams = new PageParams(1, 10000);
    Page<WorkStatistics> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
    LambdaQueryWrapper<WorkStatistics> queryWrapper = new LambdaQueryWrapper<>();

    if (workStatisticsParams.getId() != -1) {
      queryWrapper.eq(WorkStatistics::getId, workStatisticsParams.getId());
    }
    if (workStatisticsParams.getCategory() != null) {
      queryWrapper.eq(WorkStatistics::getCategory, workStatisticsParams.getCategory());
    }
    if (workStatisticsParams.getUserId() != -1) {
      queryWrapper.eq(WorkStatistics::getUserId, workStatisticsParams.getUserId());
    }
    if (workStatisticsParams.getWorkId() != -1) {
      queryWrapper.eq(WorkStatistics::getWorkId, workStatisticsParams.getWorkId());
    }
    queryWrapper.orderByAsc(WorkStatistics::getId);
    Page<WorkStatistics> userPage = workStatisticsMapper.selectPage(page, queryWrapper);
    List<WorkStatistics> list = userPage.getRecords();

    return Result.success(list, userPage.getTotal());

  }

  @Override
  public Result insertComplete(WorkStatisticsVo workStatisticsVo) throws ParseException {
    WorkStatistics workStatistics = new WorkStatistics();
    BeanUtils.copyProperties(workStatisticsVo, workStatistics);

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    if (workStatisticsVo.getSubmitTime() != null) {
      workStatistics.setSubmitTime(formatter.parse(workStatisticsVo.getSubmitTime()));
    }
    int count = workStatisticsMapper.insert(workStatistics);
    if (count == 1) {
      return Result.success(null, null);
    } else {
      return Result.fail(300, "插入失败");
    }

  }

  @Override
  public Result update(WorkStatisticsVo workStatisticsVo) throws ParseException {
    WorkStatistics workStatistics = new WorkStatistics();
    BeanUtils.copyProperties(workStatisticsVo, workStatistics);

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    if (workStatisticsVo.getSubmitTime() != null) {
      workStatistics.setSubmitTime(formatter.parse(workStatisticsVo.getSubmitTime()));
    }

    int count = workStatisticsMapper.updateById(workStatistics);
    if (count == 1) {
      return Result.success(null, null);
    } else {
      return Result.fail(300, "更新失败");
    }
  }
}
