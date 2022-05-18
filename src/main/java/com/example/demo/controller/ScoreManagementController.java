package com.example.demo.controller;

import com.example.demo.service.ScoreService;
import com.example.demo.vo.Result;
import com.example.demo.vo.param.ScoreParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// json数据和前端进行交互
@RestController
@RequestMapping("api/score_management")
public class ScoreManagementController {
  @Autowired
  private ScoreService scoreService;

  @PostMapping("/score_detail")
  public Result scoreList(@RequestBody ScoreParams scoreParams) {

    return scoreService.getScoreDetail(scoreParams);
  }

  @PostMapping("/score_detail_byId")
  public Result scoreListByWorkId(@RequestBody ScoreParams scoreParams) {

    return scoreService.getScoreDetailById(scoreParams);
  }

  @PostMapping("/final_score")
  public Result finalScoreList() {

    return scoreService.getFinalScoreList();
  }
}
