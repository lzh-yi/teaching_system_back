package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dao.mapper.TeachingOutlineMapper;
import com.example.demo.dao.pojo.TeachingOutline;
import com.example.demo.service.TeachingOutlineService;
import com.example.demo.vo.Result;
import com.example.demo.vo.TeachingOutlineVo;
import com.example.demo.vo.param.PageParams;
import com.example.demo.vo.param.TeachingOutlineParams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class TeachingOutlineServiceImpl implements TeachingOutlineService {

    @Autowired
    private TeachingOutlineMapper teachingOutlineMapper;

    @Override
    public Result teachingOutlineList(TeachingOutlineParams teachingOutlineParams) {
        // 获取分页对象
        PageParams pageParams = new PageParams(teachingOutlineParams.getPage(), teachingOutlineParams.getPageSize());
        Page<TeachingOutline> page = new Page<>(pageParams.getPage(),pageParams.getPageSize());
        LambdaQueryWrapper<TeachingOutline> queryWrapper = new LambdaQueryWrapper<>();
        // 获取查询参数
        if (teachingOutlineParams.getTitle() != "") {
            queryWrapper.eq(TeachingOutline::getTitle, teachingOutlineParams.getTitle());
        }
        if (teachingOutlineParams.getId() != -1) {
            queryWrapper.eq(TeachingOutline::getId, teachingOutlineParams.getId());
        }
        queryWrapper.orderByDesc(TeachingOutline::getId);
        Page<TeachingOutline> teachingOutlinePage = teachingOutlineMapper.selectPage(page,queryWrapper);
        List<TeachingOutline> list = teachingOutlinePage.getRecords();

        return Result.success(list, teachingOutlinePage.getTotal());
    }

    @Override
    public Result uploadTeachingOutline(TeachingOutlineVo teachingOutlineVo) throws ParseException {

        TeachingOutline teachingOutline = new TeachingOutline();
        BeanUtils.copyProperties(teachingOutlineVo, teachingOutline);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        teachingOutline.setUploadingTime(formatter.parse(teachingOutlineVo.getUploadingTime()));
        int count = teachingOutlineMapper.insert(teachingOutline);
        if (count  == 1) {
            return Result.success(null, null);
        } else {
            return Result.fail(300, "插入大纲失败");
        }
    }

    private List<TeachingOutlineVo> copyList(List<TeachingOutline> list) {
        return null;
    }

}
