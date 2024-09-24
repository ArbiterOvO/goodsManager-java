package com.arbiter.goodsmanager.service.good.impl;

import ch.qos.logback.core.util.StringUtil;
import com.arbiter.goodsmanager.DTO.SearchGoodDTO;
import com.arbiter.goodsmanager.mapper.GoodMapper;
import com.arbiter.goodsmanager.pojo.Good;
import com.arbiter.goodsmanager.service.good.GoodService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class GoodServiceImpl extends ServiceImpl<GoodMapper,Good> implements GoodService {

    public final GoodMapper goodMapper;

    @Override
    public List<Good> getAllGoods() {

        return goodMapper.selectList(null);
    }

    @Override
    public List<Good> serchGood(SearchGoodDTO searchGoodDTO) {

        return goodMapper.selectList(
                new LambdaQueryWrapper<Good>()
                        .eq(searchGoodDTO.getId() != null, Good::getId, searchGoodDTO.getId())
                        .like(!StringUtil.isNullOrEmpty(searchGoodDTO.getSearchContent()), Good::getName, searchGoodDTO.getSearchContent())
                        .eq(searchGoodDTO.getStatus() != null, Good::getStatus, searchGoodDTO.getStatus())
                        .eq(searchGoodDTO.getCategoryId() != null, Good::getCategoryId, searchGoodDTO.getCategoryId())
        );

    }
}
