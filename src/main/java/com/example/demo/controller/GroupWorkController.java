package com.example.demo.controller;

import com.example.demo.service.GroupWorkService;
import com.example.demo.vo.GroupWorkVo;
import com.example.demo.vo.Result;
import com.example.demo.vo.param.GroupWorkParams;
import com.example.demo.vo.param.InsertCompleteListParams;
import com.example.demo.vo.param.KnowledgeAndOutlineParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

// json数据和前端进行交互
@RestController
@RequestMapping("api/group_work")
public class GroupWorkController {
  @Autowired
  private GroupWorkService groupWorkService;

  @PostMapping("/add")
  public Result addGroupWork(@RequestBody GroupWorkVo groupWorkVo) throws ParseException {

    return groupWorkService.addGroupWork(groupWorkVo);
  }

  @PostMapping("/list")
  public Result groupWorkList(@RequestBody GroupWorkParams groupWorkParams) {

    return groupWorkService.groupWorkList(groupWorkParams);
  }

  @PostMapping("/update")
  public Result groupWorkList(@RequestBody GroupWorkVo groupWorkVo) throws ParseException {

    return groupWorkService.updateGroupWork(groupWorkVo);
  }

  @PostMapping("/select_data")
  public Result knowledgePointSelectData(@RequestBody KnowledgeAndOutlineParams knowledgeAndOutlineParams) {

    return groupWorkService.knowledgePointSelectData(knowledgeAndOutlineParams);
  }

  @PostMapping("/insert_complete_list")
  public Result insertCompleteList(@RequestBody InsertCompleteListParams insertCompleteListParams) throws ParseException {

    return groupWorkService.insertCompleteList(insertCompleteListParams);
  }

  @PostMapping("/update_complete_list")
  public Result updateCompleteList(@RequestBody InsertCompleteListParams insertCompleteListParams) throws ParseException {

    return groupWorkService.updateCompleteList(insertCompleteListParams);
  }

}
