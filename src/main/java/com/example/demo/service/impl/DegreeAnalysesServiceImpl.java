package com.example.demo.service.impl;

import com.example.demo.dao.pojo.Exercise;
import com.example.demo.dao.pojo.ExerciseComplete;
import com.example.demo.dao.pojo.KnowledgePoint;
import com.example.demo.dao.pojo.TeachingGoal;
import com.example.demo.service.*;
import com.example.demo.vo.Result;
import com.example.demo.vo.param.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DegreeAnalysesServiceImpl implements DegreeAnalysesService {
  @Autowired
  private TeachingGoalService teachingGoalService;
  @Autowired
  private KnowledgePointService knowledgePointService;
  @Autowired
  private ExerciseService exerciseService;
  @Autowired
  private ExerciseCompleteService exerciseCompleteService;

  @Override
  public Result degreeAnalysesDetail(DegreeAnalysesParams degreeAnalysesParams) {
    List<ResultVo> resultVoList = new ArrayList<>();
    // 获取指定大纲下的教学目标
    TeachingGoalParams teachingGoalParams = new TeachingGoalParams(-1, 1, 1000, degreeAnalysesParams.getTeachingOutlineId());
    List<TeachingGoal> teachingGoalList = (List<TeachingGoal>) teachingGoalService.teachingGoalList(teachingGoalParams).getData();
    List<ResultVo> resultVos = new ArrayList<>();
    // 获取所有习题完成情况
    List<ExerciseComplete> exerciseCompleteList = (List<ExerciseComplete>) exerciseCompleteService.exerciseCompleteList(new ExerciseCompleteParams()).getData();

    // 获取教学目标下的知识点
    for (TeachingGoal teachingGoal : teachingGoalList) {
      KnowledgePointParams knowledgePointParams = new KnowledgePointParams(-1, 1, 10000, teachingGoal.getId());
      ResultVo resultVo = new ResultVo();
      resultVo.setTeachingGoalId(teachingGoal.getId());
      resultVo.setTitle(teachingGoal.getTitle());
      List<KnowledgePoint> knowledgePointList = (List<KnowledgePoint>) knowledgePointService.knowledgePointList(knowledgePointParams).getData();
      ArrayList workIdArr = new ArrayList();
      // 获取知识点下的题目
      for (KnowledgePoint knowledgePoint : knowledgePointList) {
        ExerciseParams exerciseParams = new ExerciseParams(1, 10000, -1, "", knowledgePoint.getId());
        List<Exercise> exerciseList = (List<Exercise>) exerciseService.exerciseList(exerciseParams).getData();
        for (Exercise exercise : exerciseList) {
          workIdArr.add(exercise.getId());
        }
      }

      int scoreTotal = 0;
      int scoreDoing = 0;
      for (Object workId : workIdArr) {
        for (ExerciseComplete exerciseComplete : exerciseCompleteList) {
          if (exerciseComplete.getExerciseId() == (int) workId) {
            scoreTotal += 10;
            scoreDoing += exerciseComplete.getScore();
          }
        }
      }

      resultVo.setRate((double) scoreDoing / (double) scoreTotal);
      resultVos.add(resultVo);
    }
    return Result.success(resultVos, null);
  }

  @Data
  private class ResultVo {
    private int teachingGoalId;
    private String title;
    private double rate;
  }
}
