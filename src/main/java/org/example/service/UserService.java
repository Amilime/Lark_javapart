package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.User;
import org.example.common.Result;

// 封装好的增删改查了
public interface UserService extends IService<User> {
    // 登录 and 注册
    Result<String> login(String username, String password);
    Result<User> register(User user);
}