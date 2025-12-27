package org.example.controller;

import org.example.common.Result;
import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")  // 类级别的路径，下面是犯法级别路径
@CrossOrigin(originPatterns = "*") // 允许任何网页跨域访问这个接口
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<String> login(@RequestBody User loginReq) {
        // Controller 只有一行代码调用 Service 起接口作用
        return userService.login(loginReq.getUsername(), loginReq.getPassword());
    }
    @PostMapping("/register")
    public Result<User> register(@RequestBody User user) {
        return userService.register(user);
    }
}