package com.example.demo.service;

import com.example.demo.vo.Result;
import com.example.demo.vo.TeachingOutlineVo;
import com.example.demo.vo.param.TeachingOutlineParams;

import java.text.ParseException;

public interface TeachingOutlineService {

    /**
     * 获取大纲列表
     * @param teachingOutlineParams
     * @return
     */
    Result teachingOutlineList(TeachingOutlineParams teachingOutlineParams);

    /**
     * 上传教学大纲
     * @param teachingOutlineVo
     * @return
     */
    Result uploadTeachingOutline(TeachingOutlineVo teachingOutlineVo) throws ParseException;
}
