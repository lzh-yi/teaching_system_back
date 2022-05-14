package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dao.mapper.UserMapper;
import com.example.demo.dao.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.vo.Result;
import com.example.demo.vo.UserVo;
import com.example.demo.vo.param.PageParams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;

  @Override
  public Result addUser(UserVo userVo) {
    User user = new User();
    BeanUtils.copyProperties(userVo, user);

    UserVo userVo1 = new UserVo();
    userVo1.setUserName(userVo.getUserName());
    if (loginUser(userVo1).getTotal() != 0) {
      return Result.fail(300, "用户名已存在");
    }
    int count = userMapper.insert(user);
    if (count == 1) {
      // 注册成功之后继续登陆
      return loginUser(userVo);
    }
    return Result.fail(300, "用户名已存在");
  }

  @Override
  public Result loginUser(UserVo userVo) {
    // 获取分页对象
    PageParams pageParams = new PageParams(1, 1000);
    Page<User> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
    LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

    if (userVo.getUserName() != null) {
      queryWrapper.eq(User::getUserName, userVo.getUserName());
    }
    if (userVo.getPassword() != null) {
      queryWrapper.eq(User::getPassword, userVo.getPassword());
    }
    queryWrapper.orderByAsc(User::getId);
    Page<User> userPage = userMapper.selectPage(page, queryWrapper);
    List<User> list = userPage.getRecords();
    if (userPage.getTotal() == 0) {
      return Result.fail(300, "用户名或密码错误");
    }
    return Result.success(list, userPage.getTotal());
  }

  @Override
  public Result userList(UserVo userVo) {
    // 获取分页对象
    PageParams pageParams = new PageParams(1, 1000);
    Page<User> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
    LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

    if (userVo.getType() != null) {
      queryWrapper.eq(User::getType, userVo.getType());
    }

    queryWrapper.orderByAsc(User::getId);
    Page<User> userPage = userMapper.selectPage(page, queryWrapper);
    List<User> list = userPage.getRecords();

    return Result.success(list, userPage.getTotal());
  }
}
