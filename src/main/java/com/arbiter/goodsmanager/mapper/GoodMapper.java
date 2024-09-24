package com.arbiter.goodsmanager.mapper;

import com.arbiter.goodsmanager.DTO.SearchGoodDTO;
import com.arbiter.goodsmanager.pojo.Good;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodMapper extends BaseMapper<Good> {


}
