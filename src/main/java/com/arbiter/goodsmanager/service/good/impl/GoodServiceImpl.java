package com.arbiter.goodsmanager.service.good.impl;

import com.arbiter.goodsmanager.mapper.GoodMapper;
import com.arbiter.goodsmanager.pojo.Good;
import com.arbiter.goodsmanager.service.good.GoodService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class GoodServiceImpl extends ServiceImpl<GoodMapper,Good> implements GoodService {

    public final GoodMapper goodMapper;

    @Override
    public List<Good> getAllGoods() {

        return goodMapper.selectList(null);
    }
}
