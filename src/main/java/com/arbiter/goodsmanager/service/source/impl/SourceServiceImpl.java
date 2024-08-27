package com.arbiter.goodsmanager.service.source.impl;

import com.arbiter.goodsmanager.mapper.SourceMapper;
import com.arbiter.goodsmanager.pojo.Source;
import com.arbiter.goodsmanager.service.source.SourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SourceServiceImpl extends ServiceImpl<SourceMapper, Source> implements SourceService {
}
