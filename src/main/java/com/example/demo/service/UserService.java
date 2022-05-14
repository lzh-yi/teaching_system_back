package com.example.demo.service;

import com.example.demo.vo.Result;
import com.example.demo.vo.UserVo;

public interface UserService {
  /**
   * 添加用户
   *
   * @param userVo
   * @return
   */
  Result addUser(UserVo userVo);

  /**
   * 登陆
   *
   * @param userVo
   * @return
   */
  Result loginUser(UserVo userVo);

  /**
   * 获取用户列表
   *
   * @param userVo
   * @return
   */
  Result userList(UserVo userVo);


}
