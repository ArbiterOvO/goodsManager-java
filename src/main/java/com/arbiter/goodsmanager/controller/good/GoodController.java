package com.arbiter.goodsmanager.controller.good;

import com.alibaba.fastjson.JSONObject;
import com.arbiter.goodsmanager.VO.GoodVO;
import com.arbiter.goodsmanager.pojo.Good;
import com.arbiter.goodsmanager.result.Result;
import com.arbiter.goodsmanager.service.brand.BrandService;
import com.arbiter.goodsmanager.service.category.CategoryService;
import com.arbiter.goodsmanager.service.good.GoodService;
import com.arbiter.goodsmanager.service.source.SourceService;
import com.arbiter.goodsmanager.util.JsonUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/good")
public class GoodController {

    private final GoodService goodService;
    private final BrandService brandService;
    private final CategoryService categoryService;
    private final SourceService sourceService;
    @GetMapping("/all")
    public Result<List<JSONObject>> getAllGoods()
    {
        List<Good> allGoods = goodService.getAllGoods();
        List<JSONObject> jsonObjects = new ArrayList<>();
        for (Good good : allGoods) {
            GoodVO goodVO = new GoodVO();
            goodVO.setId(good.getId());
            goodVO.setGoodId(good.getGoodId());
            goodVO.setName(good.getName());
            goodVO.setCategory(categoryService.getById(good.getCategoryId()).getName());
            goodVO.setNumber(good.getNumber());
            goodVO.setSoldNumber(good.getSoldNumber());
            goodVO.setPrice(good.getPrice());
            goodVO.setStatus(good.getStatus()==1?"在售":"下架");
            goodVO.setSource(sourceService.getById(good.getSourceId()).getName());
            goodVO.setBrand(brandService.getById(good.getBrandId()).getName());
            jsonObjects.add(JsonUtil.toJson(goodVO));
        }

        return Result.success(jsonObjects);
    }

    @GetMapping("/{id}")
    public Result<JSONObject> getGoodById(@PathVariable int id)
    {
        Good byId = goodService.getById(id);
        return Result.success(JsonUtil.toJson(byId));
    }

    @PostMapping("/add")
    public Result<String> addNewGoods(@RequestBody Good good)
    {
        goodService.save(good);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result<String> deleteOneGoods(@PathVariable int id)
    {
        goodService.removeById(id);
        return Result.success();
    }

    @PostMapping("/edit")
    public Result<String> updateOneGoods(@RequestBody Good good)
    {
        goodService.updateById(good);
        return Result.success();
    }

}
