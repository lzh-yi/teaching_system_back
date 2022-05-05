package com.example.demo.service;

import com.example.demo.vo.Result;
import com.example.demo.vo.TeachingScheduleVo;
import com.example.demo.vo.param.TeachingScheduleParams;

import java.text.ParseException;

public interface TeachingScheduleService {
    /**
     * 获取教学进度列表
     * @param teachingOutlineParams
     * @return
     */
    Result teachingScheduleList(TeachingScheduleParams teachingOutlineParams);

    /**
     * 上传教学进度
     * @param teachingScheduleVo
     * @return
     */
    Result uploadTeachingSchedule(TeachingScheduleVo teachingScheduleVo) throws ParseException;

    /**
     * 更新教学进度
     * @param teachingScheduleVo
     * @return
     */
    Result upldateTeachingSchedule(TeachingScheduleVo teachingScheduleVo) throws ParseException;
}
