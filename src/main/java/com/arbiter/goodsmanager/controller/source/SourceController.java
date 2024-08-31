package com.arbiter.goodsmanager.controller.source;

import com.alibaba.fastjson.JSONObject;
import com.arbiter.goodsmanager.mapper.SourceMapper;
import com.arbiter.goodsmanager.pojo.Good;
import com.arbiter.goodsmanager.pojo.Source;
import com.arbiter.goodsmanager.result.Result;
import com.arbiter.goodsmanager.service.category.CategoryService;
import com.arbiter.goodsmanager.service.good.GoodService;
import com.arbiter.goodsmanager.service.source.SourceService;
import com.arbiter.goodsmanager.util.JsonUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    private final GoodService goodService;

    @GetMapping("/all")
    public Result<List<JSONObject>> getAllSources() {

        List<Source> list = sourceService.list();
        List<JSONObject> jsonObjects = new ArrayList<>();
        for (Source source : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", source.getId());
            jsonObject.put("name", source.getName());
            jsonObject.put("goodNumber",goodService.count(
                    new QueryWrapper<Good>()
                            .eq("source_id",source.getId())
            ));
            jsonObject.put("soldNumber",goodService.getMap(
                    new QueryWrapper<Good>()
                            .eq("source_id",source.getId())
                            .select("ifnull(sum(sold_number),0) as sold_number")
            ).get("sold_number"));
            jsonObject.put("description",source.getDescription());
            jsonObjects.add(jsonObject);
        }
        System.out.println(jsonObjects);
        return Result.success(jsonObjects);
    }
}
