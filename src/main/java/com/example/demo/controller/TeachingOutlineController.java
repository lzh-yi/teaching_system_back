package com.example.demo.controller;

import com.example.demo.service.TeachingOutlineService;
import com.example.demo.vo.Result;
import com.example.demo.vo.TeachingOutlineVo;
import com.example.demo.vo.param.TeachingOutlineParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

// json数据和前端进行交互
@RestController
@RequestMapping("api/teaching_outline")
public class TeachingOutlineController {

    @Autowired
    private TeachingOutlineService teachingOutlineService;

    @PostMapping("/list")
    public Result teachingOutlineList(@RequestBody TeachingOutlineParams teachingOutlineParams) {

        return teachingOutlineService.teachingOutlineList(teachingOutlineParams);
    }

    @PostMapping("/upload")
    public Result uploadTeachingOutline(@RequestBody TeachingOutlineVo teachingOutlineVo) throws ParseException {

        return teachingOutlineService.uploadTeachingOutline(teachingOutlineVo);
    }
}
