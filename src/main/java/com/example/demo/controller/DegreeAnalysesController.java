package com.example.demo.controller;

import com.example.demo.service.DegreeAnalysesService;
import com.example.demo.vo.Result;
import com.example.demo.vo.param.DegreeAnalysesParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// json数据和前端进行交互
@RestController
@RequestMapping("/api/degree_analyses")
public class DegreeAnalysesController {
  @Autowired
  private DegreeAnalysesService degreeAnalysesService;

  @PostMapping("/detail")
  public Result degreeAnalysesDetail(@RequestBody DegreeAnalysesParams degreeAnalysesParams) {

    return degreeAnalysesService.degreeAnalysesDetail(degreeAnalysesParams);
  }
}
