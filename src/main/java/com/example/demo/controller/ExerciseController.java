package com.example.demo.controller;

import com.example.demo.service.ExerciseService;
import com.example.demo.vo.ExerciseVo;
import com.example.demo.vo.Result;
import com.example.demo.vo.param.ExerciseParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// json数据和前端进行交互
@RestController
@RequestMapping("api/exercise")
public class ExerciseController {
  @Autowired
  private ExerciseService exerciseService;

  @PostMapping("/add")
  public Result addExercise(@RequestBody ExerciseVo exerciseVo) {

    return exerciseService.addExercise(exerciseVo);
  }

  @PostMapping("/list")
  public Result exerciseList(@RequestBody ExerciseParams exerciseParams) {

    return exerciseService.exerciseList(exerciseParams);
  }
}
