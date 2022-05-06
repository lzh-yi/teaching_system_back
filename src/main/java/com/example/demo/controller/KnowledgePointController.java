package com.example.demo.controller;

import com.example.demo.service.KnowledgePointService;
import com.example.demo.vo.KnowledgePointVo;
import com.example.demo.vo.Result;
import com.example.demo.vo.param.KnowledgePointParams;
import com.example.demo.vo.param.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// json数据和前端进行交互
@RestController
@RequestMapping("api/knowledge_point")
public class KnowledgePointController {
  @Autowired
  private KnowledgePointService knowledgePointService;

  @PostMapping("/select_data")
  public Result knowledgePointSelectData(@RequestBody PageParams pageParams) {

    return knowledgePointService.knowledgePointSelectData(pageParams);
  }

  @PostMapping("/add")
  public Result addKnowledgePoint(@RequestBody KnowledgePointVo knowledgePointVo) {

    return knowledgePointService.addKnowledgePoint(knowledgePointVo);
  }

  @PostMapping("/list")
  public Result knowledgePointList(@RequestBody KnowledgePointParams knowledgePointParams) {

    return knowledgePointService.knowledgePointList(knowledgePointParams);
  }

  @PostMapping("/update")
  public Result knowledgePointList(@RequestBody KnowledgePointVo knowledgePointVo) {

    return knowledgePointService.updateKnowledgePoint(knowledgePointVo);
  }
}
