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
import org.springframework.web.bind.annotation.*;

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
        List<JSONObject> jsonObjects = poListToJsonList(list);
        return Result.success(jsonObjects);
    }

    @GetMapping("/{id}")
    public Result<JSONObject> getSourceById(@PathVariable int id) {
        Source byId = sourceService.getById(id);
        return Result.success(JsonUtil.toJson(byId));
    }

    @PostMapping("/add")
    public Result<String> addSource(@RequestBody Source source) {
        sourceService.save(source);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result<String> deleteSource(@PathVariable int id) {
        sourceService.removeById(id);
        return Result.success();
    }

    @PostMapping("/edit")
    public Result<String> updateSource(@RequestBody Source source) {
        sourceService.updateById(source);
        return Result.success();
    }

    @PostMapping("/search")
    public Result<List<JSONObject>> searchSource(@RequestParam String name) {
        List<Source> list = sourceService.list(new QueryWrapper<Source>().like("name", name));
        List<JSONObject> jsonObjects = poListToJsonList(list);
        return Result.success(jsonObjects);
    }

    private List<JSONObject> poListToJsonList(List<Source> sources) {

        List<JSONObject> jsonObjects = new ArrayList<>();
        for (Source source : sources) {
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
        return jsonObjects;
    }
}
