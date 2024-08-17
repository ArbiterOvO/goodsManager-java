package com.arbiter.goodsmanager.service.user.impl;

import com.arbiter.goodsmanager.DTO.UserLoginDTO;
import com.arbiter.goodsmanager.mapper.UserMapper;
import com.arbiter.goodsmanager.pojo.User;
import com.arbiter.goodsmanager.service.user.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    private final UserMapper userMapper;
    @Override
    public void regist(User user) {
        Integer insert = userMapper.insert(user);
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectList(null);
    }

    @Override
    public User login(UserLoginDTO loginDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", loginDTO.getUsername());
        queryWrapper.eq("password", loginDTO.getPassword());
        return userMapper.selectOne(queryWrapper);
    }
}
