package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dao.mapper.ExerciseCompleteMapper;
import com.example.demo.dao.pojo.ExerciseComplete;
import com.example.demo.service.ExerciseCompleteService;
import com.example.demo.vo.ExerciseCompleteVo;
import com.example.demo.vo.Result;
import com.example.demo.vo.param.ExerciseCompleteParams;
import com.example.demo.vo.param.PageParams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseCompleteImpl implements ExerciseCompleteService {
  @Autowired
  private ExerciseCompleteMapper exerciseCompleteMapper;

  @Override
  public Result addExerciseComplete(ExerciseCompleteVo exerciseCompleteVo) {
    ExerciseComplete exerciseComplete = new ExerciseComplete();
    BeanUtils.copyProperties(exerciseCompleteVo, exerciseComplete);

    int count = exerciseCompleteMapper.insert(exerciseComplete);
    if (count == 1) {
      return Result.success(null, null);
    } else {
      return Result.fail(300, "插入失败");
    }
  }

  @Override
  public Result updateExerciseComplete(ExerciseCompleteVo exerciseCompleteVo) {
    ExerciseComplete exerciseComplete = new ExerciseComplete();
    BeanUtils.copyProperties(exerciseCompleteVo, exerciseComplete);

    int count = exerciseCompleteMapper.updateById(exerciseComplete);
    if (count == 1) {
      return Result.success(null, null);
    } else {
      return Result.fail(300, "更新失败");
    }
  }

  @Override
  public Result exerciseCompleteList(ExerciseCompleteParams exerciseCompleteParams) {
    // 获取分页对象
    PageParams pageParams = new PageParams(1, 10000);
    Page<ExerciseComplete> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
    LambdaQueryWrapper<ExerciseComplete> queryWrapper = new LambdaQueryWrapper<>();

    if (exerciseCompleteParams.getId() != -1) {
      queryWrapper.eq(ExerciseComplete::getId, exerciseCompleteParams.getId());
    }
    if (exerciseCompleteParams.getUserId() != -1) {
      queryWrapper.eq(ExerciseComplete::getUserId, exerciseCompleteParams.getUserId());
    }

    queryWrapper.orderByAsc(ExerciseComplete::getId);
    Page<ExerciseComplete> userPage = exerciseCompleteMapper.selectPage(page, queryWrapper);
    List<ExerciseComplete> list = userPage.getRecords();

    return Result.success(list, userPage.getTotal());
  }
}
