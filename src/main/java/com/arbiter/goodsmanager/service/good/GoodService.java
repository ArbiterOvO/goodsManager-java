package com.arbiter.goodsmanager.service.good;

import com.arbiter.goodsmanager.pojo.Good;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface GoodService extends IService<Good> {

    List<Good> getAllGoods();
}
