package com.example.demo.controller;

import com.example.demo.service.WorkStatisticsService;
import com.example.demo.vo.Result;
import com.example.demo.vo.WorkStatisticsVo;
import com.example.demo.vo.param.WorkStatisticsParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

// json数据和前端进行交互
@RestController
@RequestMapping("api/work_statistics")
public class WorkStatisticsController {
  @Autowired
  private WorkStatisticsService workStatisticsService;

  @PostMapping("/list")
  public Result list(@RequestBody WorkStatisticsParams workStatisticsParams) {

    return workStatisticsService.completeList(workStatisticsParams);
  }

  @PostMapping("/update")
  public Result update(@RequestBody WorkStatisticsVo workStatisticsVo) throws ParseException {

    return workStatisticsService.update(workStatisticsVo);
  }

  @PostMapping("/add")
  public Result add(@RequestBody WorkStatisticsVo workStatisticsVo) throws ParseException {

    return workStatisticsService.insertComplete(workStatisticsVo);
  }

}
