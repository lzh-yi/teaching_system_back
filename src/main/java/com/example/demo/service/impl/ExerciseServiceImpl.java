package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dao.mapper.ExerciseMapper;
import com.example.demo.dao.pojo.Exercise;
import com.example.demo.service.ExerciseService;
import com.example.demo.vo.ExerciseVo;
import com.example.demo.vo.Result;
import com.example.demo.vo.param.ExerciseParams;
import com.example.demo.vo.param.PageParams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {
  @Autowired
  private ExerciseMapper exerciseMapper;

  @Override
  public Result addExercise(ExerciseVo exerciseVo) {
    Exercise exercise = new Exercise();
    BeanUtils.copyProperties(exerciseVo, exercise);

    int count = exerciseMapper.insert(exercise);
    if (count == 1) {
      return Result.success(null, null);
    } else {
      return Result.fail(300, "插入习题失败");
    }

  }

  @Override
  public Result exerciseList(ExerciseParams exerciseParams) {
    System.out.println(exerciseParams);
    // 获取分页对象
    PageParams pageParams = new PageParams(exerciseParams.getPage(), exerciseParams.getPageSize());
    Page<Exercise> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
    LambdaQueryWrapper<Exercise> queryWrapper = new LambdaQueryWrapper<>();
    // 获取查询参数
    if (exerciseParams.getCategory() != "") {
      queryWrapper.eq(Exercise::getCategory, exerciseParams.getCategory());
    }
    if (exerciseParams.getWorkId() != -1) {
      queryWrapper.eq(Exercise::getWorkId, exerciseParams.getWorkId());
    }
    if (exerciseParams.getKnowledgePointId() != -1) {
      queryWrapper.eq(Exercise::getKnowledgePoint, exerciseParams.getKnowledgePointId());
    }
    queryWrapper.orderByAsc(Exercise::getId);
    Page<Exercise> exercisePage = exerciseMapper.selectPage(page, queryWrapper);
    List<Exercise> list = exercisePage.getRecords();

    return Result.success(list, exercisePage.getTotal());

  }
}
