package com.example.demo.controller;

import com.example.demo.service.ExerciseCompleteService;
import com.example.demo.vo.ExerciseCompleteVo;
import com.example.demo.vo.Result;
import com.example.demo.vo.param.ExerciseCompleteParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// json数据和前端进行交互
@RestController
@RequestMapping("api/exercise_complete")
public class ExerciseCompleteController {
  @Autowired
  private ExerciseCompleteService exerciseCompleteService;

  @PostMapping("/add")
  public Result addExercise(@RequestBody ExerciseCompleteVo exerciseCompleteVo) {

    return exerciseCompleteService.addExerciseComplete(exerciseCompleteVo);
  }

  @PostMapping("/list")
  public Result exerciseCompleteList(@RequestBody ExerciseCompleteParams exerciseCompleteParams) {

    return exerciseCompleteService.exerciseCompleteList(exerciseCompleteParams);
  }

  @PostMapping("/update")
  public Result updateExercise(@RequestBody ExerciseCompleteVo exerciseCompleteVo) {

    return exerciseCompleteService.updateExerciseComplete(exerciseCompleteVo);
  }
}
