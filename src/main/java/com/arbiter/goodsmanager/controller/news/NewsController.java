package com.arbiter.goodsmanager.controller.news;

import com.alibaba.fastjson.JSONObject;
import com.arbiter.goodsmanager.pojo.News;
import com.arbiter.goodsmanager.result.Result;
import com.arbiter.goodsmanager.service.news.NewsService;
import com.arbiter.goodsmanager.util.JsonUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/news")
public class NewsController {

    final NewsService newsService;

    @GetMapping("/all")
    public Result<List<JSONObject>> getNews()
    {
        List<News> newsService10News = newsService.list();
        return Result.success(JsonUtil.ListToJsonList(newsService10News));
    }

    @GetMapping("/{id}")
    public Result<JSONObject> getNewsById(@PathVariable int id)
    {
        System.out.println("id = " + id);
        News byId = newsService.getById(id);
        return Result.success(JsonUtil.toJson(byId));
    }
}
