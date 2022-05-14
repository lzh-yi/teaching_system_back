package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.vo.Result;
import com.example.demo.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// json数据和前端进行交互
@RestController
@RequestMapping("api/user")
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public Result registerUser(@RequestBody UserVo userVo) {

    return userService.addUser(userVo);
  }

  @PostMapping("/login")
  public Result loginrUser(@RequestBody UserVo userVo) {

    return userService.loginUser(userVo);
  }

  @PostMapping("/list")
  public Result userList(@RequestBody UserVo userVo) {

    return userService.userList(userVo);
  }

}
