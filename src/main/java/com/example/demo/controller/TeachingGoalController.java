package com.example.demo.controller;

import com.example.demo.service.TeachingGoalService;
import com.example.demo.vo.Result;
import com.example.demo.vo.TeachingGoalVo;
import com.example.demo.vo.param.TeachingGoalParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

// json数据和前端进行交互
@RestController
@RequestMapping("api/teaching_goal")
public class TeachingGoalController {

    @Autowired
    private TeachingGoalService teachingGoalService;

    @PostMapping("/list")
    public Result teachingGaolList(@RequestBody TeachingGoalParams teachingGoalParams) {

        return teachingGoalService.teachingGoalList(teachingGoalParams);
    }

    @PostMapping("/add")
    public Result uploadTeachingGaol(@RequestBody TeachingGoalVo teachingGoalVo) throws ParseException {

        return teachingGoalService.addTeachingGoal(teachingGoalVo);
    }

    @PostMapping("/update")
    public Result updateTeachingGaol(@RequestBody TeachingGoalVo teachingGoalVo) throws ParseException {

        return teachingGoalService.updateTeachingGaol(teachingGoalVo);
    }
}
