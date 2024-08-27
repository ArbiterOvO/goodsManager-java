package com.arbiter.goodsmanager.controller.source;

import com.alibaba.fastjson.JSONObject;
import com.arbiter.goodsmanager.pojo.Source;
import com.arbiter.goodsmanager.result.Result;
import com.arbiter.goodsmanager.service.source.SourceService;
import com.arbiter.goodsmanager.util.JsonUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/source")
public class SourceController {

    private final SourceService sourceService;

    @GetMapping("/all")
    public Result<List<JSONObject>> getAllSources() {

        List<Source> list = sourceService.list();
        List<JSONObject> jsonObjects = JsonUtil.ListToJsonList(list);
        return Result.success(jsonObjects);
    }
}
