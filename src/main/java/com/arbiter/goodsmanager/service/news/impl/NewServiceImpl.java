package com.arbiter.goodsmanager.service.news.impl;

import com.arbiter.goodsmanager.mapper.NewsMapper;
import com.arbiter.goodsmanager.pojo.News;
import com.arbiter.goodsmanager.service.news.NewsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class NewServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

    final NewsMapper newsMapper;

    @Override
    public List<News> get10News() {
        List<News> news = newsMapper.selectList(
                new QueryWrapper<News>()
                        .orderByAsc("create_time")
                        .last("limit 10")
        );

        return news;
    }
}
