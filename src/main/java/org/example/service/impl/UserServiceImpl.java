package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.Result;
import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.example.service.UserService;
import org.example.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 这里是对登录业务的实现具体流程

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public Result<String> login(String username, String password) {
        // 1. 校验参数
        if (username == null || password == null) {
            return Result.error("用户密码不能空");
        }

        // 2. 查询数据库
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("username", username);

        query.eq("password", password);

        User user = this.getOne(query);

        if (user == null) {
            return Result.error("未找到该角色");
        }

        // 3. 业务通过，生成 Token
        String token = jwtUtils.generateToken(user.getUsername(), user.getId(), user.getRole());

        // 4. 返回标准结果
        return Result.success(token);
    }
}