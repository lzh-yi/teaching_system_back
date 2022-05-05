package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dao.mapper.TeachingScheduleMapper;
import com.example.demo.dao.pojo.TeachingSchedule;
import com.example.demo.service.TeachingScheduleService;
import com.example.demo.vo.Result;
import com.example.demo.vo.TeachingScheduleVo;
import com.example.demo.vo.param.PageParams;
import com.example.demo.vo.param.TeachingScheduleParams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class teachingScheduleServiceImpl implements TeachingScheduleService {
    @Autowired
    private TeachingScheduleMapper teachingScheduleMapper;

    @Override
    public Result teachingScheduleList(TeachingScheduleParams teachingScheduleParams) {
        // 获取分页对象
        PageParams pageParams = new PageParams(teachingScheduleParams.getPage(), teachingScheduleParams.getPageSize());
        Page<TeachingSchedule> page = new Page<>(pageParams.getPage(),pageParams.getPageSize());
        LambdaQueryWrapper<TeachingSchedule> queryWrapper = new LambdaQueryWrapper<>();
        // 获取查询参数
        if (teachingScheduleParams.getTitle() != "") {
            queryWrapper.eq(TeachingSchedule::getTitle, teachingScheduleParams.getTitle());
        }
        if (teachingScheduleParams.getId() != -1) {
            queryWrapper.eq(TeachingSchedule::getId, teachingScheduleParams.getId());
        }
        queryWrapper.orderByDesc(TeachingSchedule::getId);
        Page<TeachingSchedule> teachingSchedulePage = teachingScheduleMapper.selectPage(page,queryWrapper);
        List<TeachingSchedule> list = teachingSchedulePage.getRecords();

        return Result.success(list, teachingSchedulePage.getTotal());
    }

    @Override
    public Result uploadTeachingSchedule(TeachingScheduleVo teachingScheduleVo) throws ParseException {
        TeachingSchedule teachingSchedule = new TeachingSchedule();
        BeanUtils.copyProperties(teachingScheduleVo, teachingSchedule);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        teachingSchedule.setUploadingTime(formatter.parse(teachingScheduleVo.getUploadingTime()));
        int count = teachingScheduleMapper.insert(teachingSchedule);
        if (count  == 1) {
            return Result.success(null, null);
        } else {
            return Result.fail(300, "插入教学进度失败失败");
        }

    }

    @Override
    public Result upldateTeachingSchedule(TeachingScheduleVo teachingScheduleVo) throws ParseException {
        TeachingSchedule teachingSchedule = new TeachingSchedule();
        BeanUtils.copyProperties(teachingScheduleVo, teachingSchedule);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        teachingSchedule.setUploadingTime(formatter.parse(teachingScheduleVo.getUploadingTime()));
        int count = teachingScheduleMapper.updateById(teachingSchedule);
        if (count  == 1) {
            return Result.success(null, null);
        } else {
            return Result.fail(300, "更新教学进度失败");
        }
    }
}
