package com.example.demo.service.impl;

import com.example.demo.dao.mapper.ExerciseCompleteMapper;
import com.example.demo.dao.pojo.ExerciseComplete;
import com.example.demo.service.ExerciseCompleteService;
import com.example.demo.vo.ExerciseCompleteVo;
import com.example.demo.vo.Result;
import com.example.demo.vo.param.ExerciseCompleteParams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    return null;
  }

  @Override
  public Result exerciseCompleteList(ExerciseCompleteParams exerciseCompleteParams) {
    return null;
  }
}
