package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dao.mapper.TeachingGoalMapper;
import com.example.demo.dao.pojo.TeachingGoal;
import com.example.demo.service.TeachingGoalService;
import com.example.demo.vo.Result;
import com.example.demo.vo.TeachingGoalVo;
import com.example.demo.vo.param.PageParams;
import com.example.demo.vo.param.TeachingGoalParams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeachingGoalServiceImpl implements TeachingGoalService {

    @Autowired
    private TeachingGoalMapper teachingGoalMapper;

    @Override
    public Result teachingGoalList(TeachingGoalParams teachingGoalParams) {
        // 获取分页对象
        PageParams pageParams = new PageParams(teachingGoalParams.getPage(), teachingGoalParams.getPageSize());
        Page<TeachingGoal> page = new Page<>(pageParams.getPage(),pageParams.getPageSize());
        LambdaQueryWrapper<TeachingGoal> queryWrapper = new LambdaQueryWrapper<>();
        // 获取查询参数
        if (teachingGoalParams.getTeachingGoalId() != -1) {
            queryWrapper.eq(TeachingGoal::getTeachingOutlineId, teachingGoalParams.getTeachingGoalId());
        }
        if (teachingGoalParams.getId() != -1) {
            queryWrapper.eq(TeachingGoal::getId, teachingGoalParams.getId());
        }
        queryWrapper.orderByDesc(TeachingGoal::getId);
        Page<TeachingGoal> teachingGoalPage = teachingGoalMapper.selectPage(page,queryWrapper);
        List<TeachingGoal> list = teachingGoalPage.getRecords();

        return Result.success(list, teachingGoalPage.getTotal());
    }

    @Override
    public Result addTeachingGoal(TeachingGoalVo teachingGoalVo) {
        TeachingGoal teachingGoal = new TeachingGoal();
        BeanUtils.copyProperties(teachingGoalVo, teachingGoal);
        int count = teachingGoalMapper.insert(teachingGoal);
        if (count  == 1) {
            return Result.success(null, null);
        } else {
            return Result.fail(300, "插入教学目标失败");
        }
    }

    @Override
    public Result updateTeachingGaol(TeachingGoalVo teachingGoalVo) {
        TeachingGoal teachingGoal = new TeachingGoal();
        BeanUtils.copyProperties(teachingGoalVo, teachingGoal);
        int count = teachingGoalMapper.updateById(teachingGoal);
        if (count  == 1) {
            return Result.success(null, null);
        } else {
            return Result.fail(300, "更新教学目标失败");
        }
    }
}
