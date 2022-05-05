package com.example.demo.controller;

import com.example.demo.service.TeachingScheduleService;
import com.example.demo.vo.Result;
import com.example.demo.vo.TeachingScheduleVo;
import com.example.demo.vo.param.TeachingScheduleParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

// json数据和前端进行交互
@RestController
@RequestMapping("api/teaching_schedule")
public class TeachingScheduleController {

    @Autowired
    private TeachingScheduleService teachingScheduleService;

    @PostMapping("/list")
    public Result teachingOutlineList(@RequestBody TeachingScheduleParams teachingScheduleParams) {

        return teachingScheduleService.teachingScheduleList(teachingScheduleParams);
    }

    @PostMapping("/upload")
    public Result uploadTeachingSchedule(@RequestBody TeachingScheduleVo teachingScheduleVo) throws ParseException {

        return teachingScheduleService.uploadTeachingSchedule(teachingScheduleVo);
    }

    @PostMapping("/update")
    public Result updateTeachingOutline(@RequestBody TeachingScheduleVo teachingScheduleVo) throws ParseException {

        return teachingScheduleService.upldateTeachingSchedule(teachingScheduleVo);
    }

}
