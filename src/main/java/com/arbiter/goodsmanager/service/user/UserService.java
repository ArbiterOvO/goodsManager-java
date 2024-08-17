package com.arbiter.goodsmanager.service.user;

import com.arbiter.goodsmanager.DTO.UserLoginDTO;
import com.arbiter.goodsmanager.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface UserService extends IService<User> {
    void regist(User user);

    List<User> findAll();

    User login(UserLoginDTO loginDTO);
}
