package com.arbiter.goodsmanager.service.news;

import com.arbiter.goodsmanager.pojo.News;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface NewsService extends IService<News> {
    List<News> get10News();
}
